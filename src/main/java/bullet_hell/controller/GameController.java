package bullet_hell.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bullet_hell.model.*;
import bullet_hell.controller.EndGameController;

/**
 */
public class GameController {

	@FXML private Pane basePane;
	@FXML private Canvas gameCanvas;
    @FXML private Label livesLabel;
    @FXML private Label scoreLabel;
    @FXML private Label stageLabel;
	
	private AnimationTimer timer;
	private Game gameEngine;
	private boolean readyToRender;
	private HashMap<Button, Boolean> buttonsPressed;
    
    private void update() {
		ArrayList<Button> buttons = new ArrayList<>();;
		if(this.buttonsPressed.get(Button.SPACE))
			buttons.add(Button.SPACE);
		if(this.buttonsPressed.get(Button.LEFT))
			buttons.add(Button.LEFT);
		if(this.buttonsPressed.get(Button.RIGHT))
			buttons.add(Button.RIGHT);
		if(this.buttonsPressed.get(Button.UP))
			buttons.add(Button.UP);
		if(this.buttonsPressed.get(Button.DOWN))
			buttons.add(Button.DOWN);

		this.gameEngine.update(buttons);

		GraphicsContext gc = this.gameCanvas.getGraphicsContext2D();
		paintGame(gc);
		updateText();
		
		this.readyToRender = true;
		if(this.gameEngine.getPlayer().getLives() <= 0)
			gameOver();
    }

	public void updateText(){
		this.livesLabel.setText("Lives: " + Integer.toString( this.gameEngine.getPlayer().getLives() ) );
		this.scoreLabel.setText("Score: " + Integer.toString( this.gameEngine.getScore() ));
		this.stageLabel.setText("Stage: " + Integer.toString( this.gameEngine.getStage() ));
	}

	public void paintGame(GraphicsContext gc){
		// Clear Screen
		gc.clearRect(0, 0, this.gameCanvas.getWidth(), this.gameCanvas.getHeight());

		// Paint red line
		gc.setStroke(Color.RED);
		gc.setLineWidth(3);
		gc.strokeLine(this.gameEngine.getBarrierX(),0,this.gameEngine.getBarrierX(), this.gameCanvas.getHeight());

		// Paint player

		gc.setLineWidth(2);
		gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);

		Player player = this.gameEngine.getPlayer();
				
        gc.fillPolygon(player.computeXPoints(), player.computeYPoints(), player.getShape().numSides);
        gc.strokePolygon(player.computeXPoints(), player.computeYPoints(), player.getShape().numSides);

        /*gc.fillPolygon(new double[]
				{x + size * (xOffset[0] * Math.cos(0) - yOffset[0] * Math.sin(0)),
				x + size * (xOffset[1] * Math.cos(120) - yOffset[1] * Math.sin(120)), 
				x + size * (xOffset[2] * Math.cos(240) - yOffset[2] * Math.sin(240))},
            new double[]
				{y + size * (xOffset[0] * Math.sin(0) + yOffset[0] * Math.cos(0)), 
				y + size * (xOffset[1] * Math.sin(120) + yOffset[1] * Math.cos(120)), 
				y + size * (xOffset[2] * Math.sin(240) + yOffset[2] * Math.cos(240))}, 
			3);*/

		// Paint enemies
		for(Enemy e : this.gameEngine.getEnemies()){
			gc.setFill(Color.RED);
			gc.setStroke(Color.WHITE);
			
			gc.fillPolygon(e.computeXPoints(), e.computeYPoints(), e.getShape().numSides);
			gc.strokePolygon(e.computeXPoints(), e.computeYPoints(), e.getShape().numSides);
		}

		// Paint bullets
		for(Bullet b : this.gameEngine.getBullets()){
			if(b.isFriendly()){
				gc.setFill(Color.BLUE);
				gc.setStroke(Color.GREEN);
			}else{
				gc.setFill(Color.WHITE);
				gc.setStroke(Color.RED);
			}

			double radius = b.getSize();
			double xCorner = b.getX() - radius;
			double yCorner = b.getY() - radius;

			gc.fillOval(xCorner, yCorner, 2 * radius, 2 * radius);
			gc.strokeOval(xCorner, yCorner, 2 * radius, 2 * radius);

		}
	}

	public void gameOver(){
		this.timer.stop();

		try{
            Stage stage = (Stage) this.gameCanvas.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bullet_hell/view/EndGameMenu.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root);

			EndGameController controller = loader.getController();
			controller.setStats(this.gameEngine.getScore(), this.gameEngine.getStage(), this.gameEngine.getBulletsFired(), this.gameEngine.getTimePlayed());

            stage.setTitle("Space Shooter");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch( IOException e ){
            e.printStackTrace();
        }	
	}

	public void initialize(){
		// Initialize Game
		this.gameEngine = new Game();

		this.buttonsPressed = new HashMap<>();

		// Initialize button presses
		this.buttonsPressed.put(Button.SPACE, false);
		this.buttonsPressed.put(Button.LEFT, false);
		this.buttonsPressed.put(Button.RIGHT, false);
		this.buttonsPressed.put(Button.UP, false);
		this.buttonsPressed.put(Button.DOWN, false);
		
		// Add key press and key release listeners to scene once scene is set
		this.gameCanvas.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
			if (oldScene == null && newScene != null) {
				// scene is set for the first time. Now its the time to listen stage changes.
				newScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {
						if (event.getCode() == KeyCode.SPACE) {
							buttonsPressed.put(Button.SPACE, true);
						}
						if (event.getCode() == KeyCode.LEFT) {
							buttonsPressed.put(Button.LEFT, true);
						}
						if (event.getCode() == KeyCode.RIGHT) {
							buttonsPressed.put(Button.RIGHT, true);
						}
						if (event.getCode() == KeyCode.UP) {
							buttonsPressed.put(Button.UP, true);
						}
						if (event.getCode() == KeyCode.DOWN) {
							buttonsPressed.put(Button.DOWN, true);
						}
					}
				});
				newScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {
						if (event.getCode() == KeyCode.SPACE) {
							buttonsPressed.put(Button.SPACE, false);
						}
						if (event.getCode() == KeyCode.LEFT) {
							buttonsPressed.put(Button.LEFT, false);
						}
						if (event.getCode() == KeyCode.RIGHT) {
							buttonsPressed.put(Button.RIGHT, false);
						}
						if (event.getCode() == KeyCode.UP) {
							buttonsPressed.put(Button.UP, false);
						}
						if (event.getCode() == KeyCode.DOWN) {
							buttonsPressed.put(Button.DOWN, false);
						}
					}
				});
			}
		});

				
		// Start animation timer
		this.timer = new AnimationTimer() {
			private long lastUpdate = 0;
            @Override
            public void handle(long now) {
				if(readyToRender && now - lastUpdate >= 3.33e+7){
					readyToRender = false;
					update();
					lastUpdate = now;
				}
            }
        };
        this.timer.start();
		this.readyToRender = true;
	}
}

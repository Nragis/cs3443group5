package bullet_hell.controller;

import javafx.fxml.FXML;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bullet_hell.model.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class GameController {

	@FXML Pane basePane;
	@FXML Canvas gameCanvas;
	
	private Game gameEngine;
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
		

		if(this.buttonsPressed.get(Button.SPACE)){
			drawShapes(gc);		
		}else{
			gc.clearRect(0, 0, this.gameCanvas.getWidth(), this.gameCanvas.getHeight());
		}

    }

	public void paintGame(gc){
		
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
		AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
	}
}

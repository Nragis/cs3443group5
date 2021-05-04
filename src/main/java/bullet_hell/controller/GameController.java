package bullet_hell.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bullet_hell.model.GameObject;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameController extends Application {

    @FXML private Canvas gameCanvass;
    private GameObject player;
	private List<GameObject> enemies = new ArrayList<>();
	private List<GameObject> bullets = new ArrayList<>();
	private Pane root;

   
       

    // This creates a game controller with a game already in progress. Aka loads a game
    /*
    public GameController(Game game){
        this.game = game;
    }*/

	public GameObject getPlayer() {
		return player;
	}

	public void setPlayer(GameObject player) {
		this.player = player;
	}
	
	private void addBullet(GameObject bullet, double x, double y) {
		bullets.add(bullet);
		addGameObject(bullet,x,y);
	}
	
	private void addEnemy(GameObject enemy, double x, double y) {
		bullets.add(enemy);
		addGameObject(enemy,x,y);
	}
	
	private void addGameObject(GameObject object, double x, double y) {
		object.getView().setTranslateX(x);
		object.getView().setTranslateY(y);
		root.getChildren().add(object.getView());
	}
	
	private static class Player extends GameObject {
		Player() {
			super(new Rectangle(40, 20, Color.BLUE));
		}
	}
	
	private static class Enemy extends GameObject {
		Enemy() {
			super(new Circle(15, 15, 15, Color.RED));
		}
	}
	
	private static class Bullet extends GameObject {
		Bullet() {
			super(new Circle(5, 5, 5, Color.BLACK));
		}
	}
	
	public void onUpdate() {
		for (GameObject bullet : bullets) {
			for (GameObject enemy : enemies) {
				if (bullet.isColliding(enemy)) {
					bullet.setAlive(false);
					enemy.setAlive(false);
					
					root.getChildren().removeAll(bullet.getView(), enemy.getView());
					
				}
			}
		}
	}
	
	 /*
     * Creates stage for game
     * 
     * @param Stage stage
     */	
    @Override 
    public void start(Stage stage) {
    	
    	try{
            
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/bullet_hell/view/GameCanvass.fxml"));
            Pane root = loader.load();
            
            Scene scene = new Scene(root);
            stage.setTitle("Space Shooter");
            stage.setScene(scene);
            stage.setResizable(false);
            
            player = new Player();
            player.setVelocity(new Point2D(1,0));
            addGameObject(player,300,300);
            
            AnimationTimer timer = new AnimationTimer() {
    			@Override
    			public void handle(long now) {
    				onUpdate();
    			}
    		};
    		
    		timer.start();
            
            stage.show();
        } catch( IOException e ){
            e.printStackTrace();
        }
    }

    
}


package bullet_hell.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import bullet_hell.model.*;


/**
 * Creates entities to put on screen
 * Gets inputs from user
 */
public class GameController {

	private Pane root;
    private Player player;
    private AnimationTimer timer;
	private List<Bullet> bullets = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private boolean pause;
    private Game game;

    /*
     * Updates all objects on the screen
     * Checks for game over
     */
    public void onUpdate() {
        for (Bullet bullet : bullets) {
            for (Enemy enemy : enemies) {
                if (bullet.isColliding(enemy)) {
                    if (game.enemykilled(enemy)) {
                    	bullet.setAlive(false);
                    	enemy.setAlive(false);
                    	game.enemyKilled(player);

                    	root.getChildren().removeAll(bullet.getView(), enemy.getView());
                    } else {
                    	game.enemyHit(enemy);
                    }
                    
                    if (player.isColliding(enemy)) {
                    	game.playerHit(player);
                    	if (player.getLife() == 0) {
                        	
                        	GameOver();
                        }   
                    }   
                }   
            }
        }
        
        bullets.removeIf(GameObject::isDead);
        enemies.removeIf(GameObject::isDead);
        bullets.forEach(GameObject::update);
        enemies.forEach(GameObject::update);

        player.update();
        	
        /*
        if (Math.random() < 0.02) {
            addEnemy(new Enemy(), Math.random() * root.getPrefWidth(), Math.random() * root.getPrefHeight());
            
        }*/
    }
    
	/*
	 * Creates an enemy to put on screen
	 * 
	 *  @param
	 *  GameObject enemy
	 *  double x
	 *  double y
	 */
	public void addBullet(Bullet bullet, double x, double y) {
        bullets.add(bullet);
        addGameObject(bullet, x, y);
    }
	
	/*
	 * Creates an enemy to put on screen
	 * 
	 *  @param
	 *  GameObject enemy
	 *  double x
	 *  double y
	 */
    public void addEnemy(Enemy enemy, double x, double y) {
        enemies.add(enemy);
        addGameObject(enemy, x, y);
    }
    
	/*
	 * Creates a object to put on screen
	 * 
	 *  @param
	 *  GameObject enemy
	 *  double x
	 *  double y
	 */
    public void addGameObject(GameObject object, double x, double y) {
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
    }
    
    private Parent createContent() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/bullet_hell/view/GameCanvass.fxml"));
    	root = loader.load();
        
    	game = new Game();
        player = new Player();
        player.setVelocity(new Point2D(0, 0));
        addGameObject(player, 150, 300);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();
        return root;
    }

    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.getScene().setOnKeyPressed(e -> {
           	
            //Bullet bullet = new Bullet();
            //bullet.setVelocity(new Point2D(5, 0));
            //addBullet(bullet, player.getView().getTranslateX() + 20, player.getView().getTranslateY());
        }); 
                
        stage.show();
    }
    
    public void pause() {
    	if(pause) { 
    		timer.stop();
    	} else {
    		timer.start();
    	}
    }
    
    public void GameOver() {
    	timer.stop();
    	
    }
}
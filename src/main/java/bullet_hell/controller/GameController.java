package bullet_hell.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;
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
    private HashMap<Button, Boolean> buttonsPressed;
    private Timer bulletTimer;

    /*
     * Updates all objects on the screen
     * Checks for game over
     */
    public void onUpdate() {
        
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
    	
		update(buttons);

		
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
                    	bullet.setAlive(false);
                    	root.getChildren().removeAll(bullet.getView());
                    }
                }   
            }
        }
        
        for (Enemy enemy : enemies) {
        	if (player.isColliding(enemy)) {
            	game.playerHit(player);
            	enemy.setAlive(false);
            	root.getChildren().removeAll(enemy.getView());
            	if (player.getLife() == 0) {
            		
                	GameOver();
                }   
            } 
        }
        
        bullets.removeIf(GameObject::isDead);
        enemies.removeIf(GameObject::isDead);
        bullets.forEach(GameObject::update);
        enemies.forEach(GameObject::update);
        player.update();
        
        if (Math.random() < 0.02) {
        	Enemy enemy = new Enemy();
        	enemy.setVelocity(new Point2D(-1, 0));
            addEnemy(enemy, root.getPrefWidth(), Math.random() * root.getPrefHeight());
        }
        
        shot();
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
    public void shot() {
    	game.playershots(player);
    	Bullet bullet = new Bullet();
    	bullet.setVelocity(new Point2D(5, 0));
    	addBullet(bullet, player.getView().getTranslateX() + 20, player.getView().getTranslateY());
    }
    
    private Parent createContent() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/bullet_hell/view/GameCanvass.fxml"));
    	root = loader.load();
    	this.buttonsPressed = new HashMap<>();
    	this.buttonsPressed.put(Button.SPACE, false);
		this.buttonsPressed.put(Button.LEFT, false);
		this.buttonsPressed.put(Button.RIGHT, false);
		this.buttonsPressed.put(Button.UP, false);
		this.buttonsPressed.put(Button.DOWN, false);
    	game = new Game();
        player = new Player();
        player.setVelocity(new Point2D(0, 0));
        addGameObject(player, 150, 300);
        bulletTimer = new Timer();
        
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
    		if (e.getCode() == KeyCode.SPACE) {
				buttonsPressed.put(Button.SPACE, true);
			}
			if (e.getCode() == KeyCode.LEFT) {
				buttonsPressed.put(Button.LEFT, true);
			}
			if (e.getCode() == KeyCode.RIGHT) {
				buttonsPressed.put(Button.RIGHT, true);
			}
			if (e.getCode() == KeyCode.UP) {
				buttonsPressed.put(Button.UP, true);
			}
			if (e.getCode() == KeyCode.DOWN) {
				buttonsPressed.put(Button.DOWN, true);
			}
        }); 
        
        stage.getScene().setOnKeyReleased(e -> {
           	
        	if (e.getCode() == KeyCode.SPACE) {
				buttonsPressed.put(Button.SPACE, false);
			}
			if (e.getCode() == KeyCode.LEFT) {
				buttonsPressed.put(Button.LEFT, false);
			}
			if (e.getCode() == KeyCode.RIGHT) {
				buttonsPressed.put(Button.RIGHT, false);
			}
			if (e.getCode() == KeyCode.UP) {
				buttonsPressed.put(Button.UP, false);
			}
			if (e.getCode() == KeyCode.DOWN) {
				buttonsPressed.put(Button.DOWN, false);
			}
			if (e.getCode() == KeyCode.ESCAPE) {
				pause();
			}
        });
          
        stage.show();
    }
    
    public void pause() {
    	if(!pause) { 
    		timer.stop();
    		pause = true;
    	} else {
    		pause = false;
    		timer.start();
    	}
    }
    
    public void GameOver() {
    	
    	timer.stop();
    }
    
	public void update(ArrayList<Button> buttons) {
		double wall = root.getPrefWidth();
		double floor = root.getPrefHeight();
		if( buttons.contains(Button.LEFT) ){
			game.playerLeft(player);
		}
		if( buttons.contains(Button.RIGHT) ){
			game.playerRight(player, wall);
		}
		if( buttons.contains(Button.UP) ){
			game.playerUp(player);
		}
		if( buttons.contains(Button.DOWN) ){
			game.playerDown(player, floor);
			
		}
	}
}
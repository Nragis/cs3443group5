package bullet_hell.controller;

import bullet_hell.model.*;
import javafx.fxml.*;
import javafx.scene.canvas.*;

/*
 * Controll for the main game
 */
public class GameController {

    @FXML private Canvas gameCanvass;
    
    private Player playerInfo;
    private Game game;
    private Enemy enemy;
    private Projectile bullets;
    private Entity entity;
    
    /*
     * Constructor
     * Initializes all elements of the game
     */
    public GameController() {
    	
    	playerInfo = new Player();
    	game = new Game();
    	enemy = new Enemy();
    	bullets = new Projectile();
    	entity = new Entity();
    	
    	System.out.println("Hello");
    }        
}
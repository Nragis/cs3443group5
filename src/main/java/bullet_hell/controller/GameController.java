package bullet_hell.controller;

import java.io.IOException;

import bullet_hell.model.*;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.application.*;
import javafx.stage.*;

/*
 * Controll for the main game
 */
public class GameController extends Application {

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
    }   
    
    /*
     * Creates stage for game
     * 
     * @param Stage stage
     */	
    @Override public void start(Stage stage) {
    	
    	try{
            
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/bullet_hell/view/GameCanvass.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Space Shooter");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch( IOException e ){
            e.printStackTrace();
        }
    }
}
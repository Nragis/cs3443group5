package bullet_hell.controller;


import bullet_hell.model.Player;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

import bullet_hell.model.Game;

public class GameController {

    @FXML private Canvas gameCanvass;
    
    private Player player;
    private Game game;
    
    /*
     * Constructor
     * Initializes all elements of the game
     */
    public GameController() {
    	
    	player = new Player();
    	System.out.println(player.getPlayerLifes());
    }
       
    //Getters and setters
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

    
}


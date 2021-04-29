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
     *     
     */
    public GameController() {
    	setPlayer(new Player());
    }
       

    // This creates a game controller with a game already in progress. Aka loads a game
    /*
    public GameController(Game game){
        this.game = game;
    }*/

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

    
}


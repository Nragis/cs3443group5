package bullet_hell.controller;


import bullet_hell.model.Player;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class GameController {

    @FXML private Canvas gameCanvass;
    private Player player;
    
    /*
     *     
     */
    public GameController() {
    	setPlayer(new Player());
    }

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}


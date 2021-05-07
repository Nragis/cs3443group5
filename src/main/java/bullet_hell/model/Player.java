package bullet_hell.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Stores Players current game info
 */
public class Player extends GameObject {
	
	private String playerName;
	private int playerScore;
	private int playerShots;
	private int playerLifes;
	private int enemiesKilled;
	
	
	/*
	 * Constructor
	 * Initializes player info
	 */
	public Player() {
		super(new Rectangle(40, 20, Color.BLUE));
		playerScore = 0;
		playerShots = 0;
		playerLifes = 5;
		enemiesKilled = 0;
	}

	
	//Getters and Setters
	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public int getPlayerScore() {
		return playerScore;
	}


	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}


	public int getPlayerShots() {
		return playerShots;
	}


	public void setPlayerShots(int playerShots) {
		this.playerShots = playerShots;
	}


	public int getPlayerLifes() {
		return playerLifes;
	}


	public void setPlayerLifes(int playerLifes) {
		this.playerLifes = playerLifes;
	}

	public int getEnemiesKilled() {
		return enemiesKilled;
	}


	public void setEnemiesKilled(int enemiesKilled) {
		this.enemiesKilled = enemiesKilled;
	}
	
	
}
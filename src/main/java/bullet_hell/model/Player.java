package bullet_hell.model;

public class Player{
	
	private String playerName;
	private int playerScore;
	private int playerShots;
	//private int player
	
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
}
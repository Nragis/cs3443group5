package bullet_hell.model;

/*
 * Stores Players current game info
 */
public class Player{
	
	private String playerName;
	private int playerScore;
	private int playerShots;
	private int playerLifes;
	private int upgrade;
	
	
	/*
	 * Constructor
	 * Initializes player info
	 */
	public Player() {
		
		playerScore = 0;
		playerShots = 0;
		playerLifes = 5;
		upgrade = 0;
	}
	
	public void shot() {
		++playerShots;
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

	public int getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(int upgrade) {
		this.upgrade = upgrade;
	}
}
package bullet_hell.model;

public class Game{
    
	private Player player;
	
	public Game() {
		player = new Player();
	}
	
	public void playerHit() {
		player.setPlayerLifes(player.getPlayerLifes() - 1);;
	}
	
	public void playershots() {
		player.setPlayerShots(1 + player.getPlayerShots());
	}
	
	public void enemyshot() {
		player.setEnemiesKilled(1 + player.getEnemiesKilled());
		player.setPlayerScore(1000 + player.getPlayerScore());
	}
	
    public void gameOver(){
    	
    }
}

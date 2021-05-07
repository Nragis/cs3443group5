package bullet_hell.model;
/**
 * Keeps track of all info for player and enemies
 *
 */
public class Game{
    		
	public void playershots(Player player) {
		player.setPlayerShots(1 + player.getPlayerShots());
	}
	
	public void enemyKilled(Player player) {
		player.setEnemiesKilled(1 + player.getEnemiesKilled());
		player.setPlayerScore(1000 + player.getPlayerScore());
	}

	public boolean enemykilled(Enemy enemy) {
		if (enemy.getLife() == 0) {
			return true;
		}
		return false;
	}
	
	public boolean playerkilled(Enemy player) {
		if (player.getLife() == 0) {
			return true;
		}
		return false;
	}

	public void enemyHit(Enemy enemy) {
		enemy.setLife(enemy.getLife() - 1);		
	}

	public void playerHit(Player player) {
		player.setLife(player.getLife()-1);
	}
}

package bullet_hell.model;

import java.util.ArrayList;

/**
 * Keeps track of all info for player and enemies
 *
 */
public class Game{
    
	//Counts the players shot
	public void playershots(Player player) {
		player.setPlayerShots(1 + player.getPlayerShots());
	}
	
	//Adds to score if enemy is killed
	public void enemyKilled(Player player) {
		player.setEnemiesKilled(1 + player.getEnemiesKilled());
		player.setPlayerScore(1000 + player.getPlayerScore());
	}
	
	//Checks if enemy is dead
	public boolean enemykilled(Enemy enemy) {
		
		if (enemy.getLife() == 0) {
			return true;
		}
		return false;
	}
	
	//Checks if player is dead
	public boolean playerkilled(Enemy player) {
		if (player.getLife() == 0) {
			return true;
		}
		return false;
	}
	
	//ticks 1 down for players life
	public void enemyHit(Enemy enemy) {
		enemy.setLife(enemy.getLife() - 1);		
	}
	
	//Ticks 1 down for players life
	public void playerHit(Player player) {
		player.setLife(player.getLife()-1);
	}
	
	//Moves player down 
	public void playerDown(Player player, double floor) {
		double buff = player.getView().getTranslateY();
		
		if (buff + 2 < floor - 20) {
		
			player.getView().setTranslateY(buff + 2);
		}
		
	}
	
	//Moves player up
	public void playerUp(Player player) {
		
		double buff = player.getView().getTranslateY();
		
		if (buff - 2 > 0 ) {
		
			player.getView().setTranslateY(buff - 2);
		}
		
	}
	
	//Moves player right
	public void playerRight(Player player, double wall) {
		
		double buff = player.getView().getTranslateX();
		
		if (buff + 2 < wall-40) {
		
			player.getView().setTranslateX(buff + 2);
		}
		
	}
	
	//Moves player left
	public void playerLeft(Player player) {
		
		double buff = player.getView().getTranslateX();
		
		if (buff - 2 > 0 ) {
		
			player.getView().setTranslateX(buff - 2);
		}
	}
}

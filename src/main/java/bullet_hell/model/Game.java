package bullet_hell.model;

import java.util.ArrayList;

public class Game{

	Player player;
	ArrayList<Bullet> bullets;
	ArrayList<Enemy> enemies;
    
	public Game(){
    	this.player = new Player();
		this.bullets = new ArrayList<>();
		this.enemies = new ArrayList<>();
    }
	
	

	public void update(ArrayList<Button> buttons){
		// Move player
		if( buttons.contains(Button.SPACE) ){
			// Shoot
		}
		if( buttons.contains(Button.LEFT) ){
			this.player.moveLeft();
		}
		if( buttons.contains(Button.RIGHT) ){
			this.player.moveRight();
		}
		if( buttons.contains(Button.UP) ){
			this.player.moveUp();
		}
		if( buttons.contains(Button.DOWN) ){
			this.player.moveDown();
		}
		
		// Move bullets
		for(Bullet b : this.bullets){
			b.move();
		}

		// Move enemies
		for(Enemy e : this.enemies){
			e.move();
		}

		// Calculate collisions
	}

	public Player getPlayer(){
		return this.player;
	}

	public void setPlayer(Player player){
		this.player = player;
	}

	public ArrayList<Bullet> getBullets(){
		return this.bullets;
	}

	public void setBullets(ArrayList<Bullet> bullets){
		this.bullets = bullets;
	}

	public void addBullet(Bullet bullet){
		this.bullets.add(bullet);	
	}

	public void removeBullet(Bullet bullet){
		this.bullets.remove(bullet);
	}

	public ArrayList<Enemy> getEnemies(){
		return this.enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies){
		this.enemies = enemies;
	}

	public void addEnemy(Enemy enemy){
		this.enemies.add(enemy);	
	}

	public void removeEnemy(Enemy enemy){
		this.enemies.remove(enemy);
	}
}

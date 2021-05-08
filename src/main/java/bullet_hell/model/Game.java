package bullet_hell.model;

import java.util.ArrayList;
import java.util.Random;

public class Game{
	
	private final int BARRIER_X = 350;
	private final int GAME_SIZE_X = 800;
	private final int GAME_SIZE_Y = 600;
	private final Random random = new Random(System.nanoTime());

	private Player player;
	private ArrayList<Bullet> bullets;
	private ArrayList<Enemy> enemies;

	private int score;
	private int stage;
	private int timePlayed;
	private int bulletsFired;
    
	public Game(){
    	this.player = new Player();
		this.bullets = new ArrayList<>();
		this.enemies = new ArrayList<>();
		this.score = 0;
		this.stage = 1;
		this.timePlayed = 0;
		this.bulletsFired = 0;
    }

	public void update(ArrayList<Button> buttons){
		// PLAYER
		
		this.player.decreaseShootTimer(); // Decrease shoot cooldown
		if( buttons.contains(Button.SPACE) ){ //SPACE
			if(this.player.canShoot())
				shoot(this.player);
		}
		if( buttons.contains(Button.LEFT) ){ //LEFT
			if(this.player.getX() + this.player.getSize() * this.player.getShape().minXOffset - this.player.getMoveSpeed() >= 0)
			this.player.moveLeft();
		}
		if( buttons.contains(Button.RIGHT) ){ //RIGHT
			if(this.player.getX() + this.player.getSize() * this.player.getShape().maxXOffset + this.player.getMoveSpeed() <= BARRIER_X)
				this.player.moveRight();
		}
		if( buttons.contains(Button.UP) ){ //UP
			if(this.player.getY() + this.player.getSize() * this.player.getShape().minYOffset - this.player.getMoveSpeed() >= 0)
				this.player.moveUp();
		}
		if( buttons.contains(Button.DOWN) ){ //DOWN
			if(this.player.getY() + this.player.getSize() * this.player.getShape().maxYOffset + this.player.getMoveSpeed() <= GAME_SIZE_Y)
				this.player.moveDown();
		}
		
		// ENEMIES	
		
		// Create new enemies
		if( random.nextInt() % (150 - this.stage*5) == 0 ){
			Enemy newEnemy = Enemy.randomEnemy(this.stage);
			double newX = (random.nextDouble() * (GAME_SIZE_X - BARRIER_X - newEnemy.getSize() * (newEnemy.getShape().maxXOffset - newEnemy.getShape().minXOffset)) 
					+ BARRIER_X - newEnemy.getShape().maxXOffset);
			double newY = (random.nextDouble() * (GAME_SIZE_Y - newEnemy.getSize() * (newEnemy.getShape().maxYOffset - newEnemy.getShape().minYOffset)) 
					+ newEnemy.getShape().minYOffset);
			newEnemy.setX(newX);
			newEnemy.setY(newY);
			this.enemies.add(newEnemy);
		}

		// Make enimes move and shoot
		for(Enemy e : this.enemies){
			// Make enemies bounce when they hit a wall
			if( e.getX() + e.getSize() * e.getShape().minXOffset + e.getXVelocity() <= BARRIER_X )
				e.setXVelocity( -1 * e.getXVelocity() );
			if( e.getX() + e.getSize() * e.getShape().maxXOffset + e.getXVelocity() >= GAME_SIZE_X )
				e.setXVelocity( -1 * e.getXVelocity() );
			if( e.getY() + e.getSize() * e.getShape().minYOffset + e.getYVelocity() <= 0 )
				e.setYVelocity( -1 * e.getYVelocity() );
			if( e.getY() + e.getSize() * e.getShape().maxYOffset + e.getXVelocity() >= BARRIER_X )
				e.setYVelocity( -1 * e.getYVelocity() );

			// Move enemies
			e.move();
			e.decreaseShootTimer();

			// Enemies shoot
			if( e.canShoot() )
				shoot(e);
				
		}

		// BULLETS

		// Remove bullets when they hit a back wall. Bounce when they hit a top wall
		// Move and collide bullets
		for(Bullet b : this.bullets){
			b.move(); // Move
			// Collide
			if( b.isFriendly() ){
				for(Enemy e : this.enemies){
					if(b.isCollidingWith(e)){
						e.removeLife();
						b.remove();
						addScore(stage*100);
					}	
				}
			}else{
				if(b.isCollidingWith(this.player)){
					this.player.removeLife();
					b.remove();
				}
			}
		}

		// Wall collision detection
		for(Bullet b : this.bullets){
			if( ! (b.getX() >= 0 && b.getX() <= GAME_SIZE_X))
			   b.remove();
			if( b.getY() < 0 || b.getY() > GAME_SIZE_Y){
				b.setYVelocity(-1 * b.getYVelocity());
			}
		}
		// Replace bullets with newBullets

		ArrayList<Bullet> newBullets = new ArrayList<>();
		for(Bullet b : this.bullets){
			if(! b.isRemove())
				newBullets.add(b);
		}
		this.bullets = newBullets;

				//Remove dead enemies
		ArrayList<Enemy> newEnemies = new ArrayList<>();
		for(Enemy e : this.enemies){
			if(e.getLives() > 0)
				newEnemies.add(e);
		}

		this.enemies = newEnemies;

		this.timePlayed++;

		if(timePlayed % 900 == 0){
			this.player.setMoveSpeed(this.player.getMoveSpeed() + stage*2);
			this.stage++;
			addScore(stage*200);
		}
	}

	public void addScore(int score){
		this.score += score;
	}

	public void shoot(Player player){
		Bullet bullet = new Bullet();
		bullet.setX(player.getX() + player.getSize());
		bullet.setY(player.getY());
		bullet.setVelocity(player.getBullet().getVelocity());
		bullet.setFriendly(true);
		this.bullets.add(bullet);

		player.resetShootTimer();
		this.bulletsFired++;
	}

	public void shoot(Enemy enemy){
		Bullet bullet = new Bullet();
		bullet.setX(enemy.getX() - enemy.getSize());
		bullet.setY(enemy.getY());
		bullet.setVelocity(enemy.getBullet().getVelocity());
		bullet.setFriendly(false);
		this.bullets.add(bullet);

		enemy.resetShootTimer();

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

	public int getScore(){
		return this.score;
	}

	public void setScore(int score){
		this.score = score;
	}

	public int getStage(){
		return this.stage;
	}

	public void setStage(int stage){
		this.stage = stage;
	}

	public int getBulletsFired(){
		return this.bulletsFired;
	}

	public int getTimePlayed(){
		return this.timePlayed;
	}

	public int getBarrierX(){
		return this.BARRIER_X;
	}
}

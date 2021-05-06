package bullet_hell.model;

public class Game{

	Player player;
	ArrayList<Bullet> bullets;
	ArrayList<Enemy> enemies;
    
	public Game(){
    	this.player = new Player();
		this.bullets = new ArrayList<>();
		this.enemies = new ArrayList<>();
    }
	
	public enum Button{
		SPACE,
		LEFT,
		RIGHT,
		UP,
		DOWN;
	}

	public void update(ArrayList<Button> buttons){
		// Move player
		if( buttons.contains(SPACE) ){
			// Shoot
		}
		if( buttons.contains(LEFT) ){
			this.player.moveLeft();
		}
		if( buttons.contains(RIGHT) ){
			this.player.moveRight();
		}
		if( buttons.contains(UP) ){
			this.player.moveUp();
		}
		if( buttons.contains(DOWN) ){
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

	public void setEnemies(ArrayList<Enemie> enemies){
		this.enemies = enemies;
	}

	public void addEnemy(Enemy enemy){
		this.enemies.add(enemy);	
	}

	public void removeEnemy(Enemy enemy){
		this.enemies.remove(enemy);
	}
}

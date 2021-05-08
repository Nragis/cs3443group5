package bullet_hell.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * Stores Players current game info
 */
public class Player extends GameObject {
	
	private static double moveSpeed = 7.0;
	private static final double PLAYER_SIZE = 30.0;
	private static final double[] BULLET_SPEED = {20.0, 0.0};
	
	private int firerate;
	private int lives;
	private int shootTimer;
	private Bullet bullet;
	
	/*
	 * Constructor
	 * Initializes player info
	 */
	public Player() {
		super(Shape.TRIANGLE, PLAYER_SIZE, 50, 280);
		this.lives = 5;
		this.firerate = 5;
		this.shootTimer = 0;

		this.bullet = new Bullet();
		this.bullet.setVelocity(BULLET_SPEED);
		this.bullet.setFriendly(true);
	}

	public int getLives(){
		return this.lives;
	}
		
	public void removeLife() {
		--this.lives; 
	}

	public void removeLives(int ammount) {
		this.lives -= ammount;
	}

	public void move(){ }

	public void moveLeft() {
		this.addX(- moveSpeed);
    }

    public void moveRight() {
		this.addX(moveSpeed);
    }

    public void moveUp() {
		this.addY(- moveSpeed);
    }

    public void moveDown() {
		this.addY(moveSpeed);
    }

	public Bullet getBullet(){
		return this.bullet;
	}

	public void setBullet(Bullet bullet){
		this.bullet = bullet;
	}

	public int getFirerate(){
		return this.firerate;
	}

	public void setFirerate(int firerate){
		this.firerate = firerate;
	}

	public void resetShootTimer(){
		this.shootTimer = this.firerate;
	}

	public boolean canShoot(){
		return (this.shootTimer == 0);
	}

	public void decreaseShootTimer(){
		if(this.shootTimer > 0) this.shootTimer--;
	}
	
	public void setMoveSpeed(double moveSpeed){
		this.moveSpeed = moveSpeed;
	}

	public double getMoveSpeed(){
		return this.moveSpeed;
	}
}

package bullet_hell.model;

import java.util.Random;
import java.lang.Math;

public class Enemy extends GameObject{
	private int lives;
	private Bullet bullet;
	private int firerate;
	private int shootTimer;

	public Enemy(Shape shape, double size) {
		super(shape, size);
		this.bullet = new Bullet();
		this.lives = 1;
		this.firerate = 0;
		this.shootTimer = 0;
	}

	public Enemy(Shape shape, double size, double x, double y){
		super(shape, size, x, y);
		this.bullet = new Bullet();
		this.lives = 1;
		this.firerate = 0;
		this.shootTimer = 0;
	}

	public Enemy(Shape shape, double size, double x, double y, double dx, double dy){
		super(shape, size, x, y, dx, dy);
		this.bullet = new Bullet();
		this.lives = 1;
		this.firerate = 0;
		this.shootTimer = 0;
	}

	public Enemy(Shape shape, double size, double x, double y, double dx, double dy, double rotation){
		super(shape, size, x, y, dx, dy, rotation);
		this.bullet = new Bullet();
		this.lives = 1;
		this.firerate = 0;
		this.shootTimer = 0;
	}

	public static Enemy randomEnemy(int stage){
		Random random = new Random(System.nanoTime());
		Enemy enemy;
		if(stage < 5)
			enemy = new Enemy(Shape.TRIANGLE, 140.0 / (4 + stage));
		else if (stage < 10)
			enemy = new Enemy(Shape.SQUARE, 140.0 / (4 + stage));
		else if (stage < 15)
			enemy = new Enemy(Shape.PENTAGON, 140.0 / (4 + stage));
		else if (stage < 25)
			enemy = new Enemy(Shape.HEXAGON, 140.0 / (4 + stage));
		else
			enemy = new Enemy(Shape.CIRCLE, 140.0 / (4 + stage));
		
		enemy.setRotation(random.nextDouble() * 360);
		enemy.setSize(140.0 / (4 + stage));
		enemy.setVelocity(new double[] {random.nextDouble() * (2*stage) - stage, random.nextDouble() * (2*stage) - stage});
		enemy.getBullet().setVelocity(new double[] { - 3 * (random.nextDouble() * (stage)) + stage, 3 * ( random.nextDouble() * (2*stage) - stage ) + stage});
		enemy.setFirerate(60-stage);
		enemy.setLife(Math.max(1, (int) (0.25 * stage)));
		return enemy;
	}

	public int getLives(){
		return this.lives;
	}

	public void setLife(int lives){
		this.lives = lives;
	}

	public void removeLife() {
		--this.lives; 
	}

	public void removeLife(int ammount) {
		this.lives -= ammount;
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
}

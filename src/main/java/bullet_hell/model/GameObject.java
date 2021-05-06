package bullet_hell.model;

public class GameObject {
	
	public enum Shape{
		CIRCLE,
		TRIANGLE,
		SQUARE,
		PENTAGON,
		HEXAGON;
	}
	public double size;
	
	private double x;
	private double y;
	private double dx;
	private double dy;

	private boolean alive = true;
	
	public GameObject(Shape shape, double size) {
		this.shape = shape;
		this.size = size;
		this.x = 0;
		this.y = 0;
		this.dx = 0;
		this.dy = 0;
	}

	public GameObject(Shape shape, double size, double x, double y){
		this.x = x;
		this.y = y;
		this.dx = 0;
		this.dy = 0;
	}

	public GameObject(Shape shape, double size, double x, double y, double dx, double dy){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
	
	//Updates players position	
	public void move() {
		this.x += this.dx;
		this.y += this.dy;
	}

	//Setters and getters
	public void setVelocity(double dx, double dy) {  
		this.dx = dx;
		this.dy = dy;
	}

		   
	public double[] getVelocity() {
		double[] velocity = new double[2];
		velocity[0] = this.dx;
		velocity[1] = this.dy;
		return velocity;	
	}
	
	public boolean isAlive() {     
		return alive;	    
	}
	
		    
	public boolean isDead() {       
		return !alive;    
	}
	
		    
	public void setAlive(boolean alive) {     
		this.alive = alive;  
	}
}

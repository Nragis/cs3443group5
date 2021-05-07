package bullet_hell.model;

public class GameObject {
	
	private double size;
	private Shape shape;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	private double rotation;

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
		this.shape = shape;
		this.size = size;
		this.x = x;
		this.y = y;
		this.dx = 0;
		this.dy = 0;
	}

	public GameObject(Shape shape, double size, double x, double y, double dx, double dy){
		this.shape = shape;
		this.size = size;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}

	public GameObject(Shape shape, double size, double x, double y, double dx, double dy, double rotation){
		this.shape = shape;
		this.size = size;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.rotation = rotation % 360;
	}
	
	//Updates players position	
	public void move() {
		this.x += this.dx;
		this.y += this.dy;
	}

	public double getX(){
		return this.x;
	}

	public void setX(double x){
		this.x = x;
	}

	public void addX(double x){
		this.x += x;
	}

	public double getY(){
		return this.y;
	}

	public void setY(double y){
		this.y = y;
	}

	public void addY(double y){
		this.y += y;
	}

	public double getRotation(){
		return this.rotation;
	}

	public void setRotation(double rotation){
		this.rotation = rotation % 360;
	}

	public void addRotation(double add){
		this.rotation += add;
		this.rotation = this.rotation % 360;
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

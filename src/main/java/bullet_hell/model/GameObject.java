package bullet_hell.model;

import javafx.scene.shape.Rectangle;

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

	public double[] computeXPoints(){
		int numSides = this.getShape().numSides;
		double[] xPoints = new double[numSides];
		double x = this.getX();
		double y = this.getY();
		double rotation = this.getRotation();
		double size = this.getSize();
		double[] xOffset = this.getShape().xOffset;
		double[] yOffset = this.getShape().yOffset;

		for(int i = 0; i < numSides; i++){
			xPoints[i] = x + size * (xOffset[i] * Math.cos(2*Math.PI/360 * rotation) - yOffset[i] * Math.sin(2*Math.PI/360 * rotation));
		}
		return xPoints;	
	}

	public double[] computeYPoints(){
		int numSides = this.getShape().numSides;
		double[] yPoints = new double[numSides];
		double x = this.getX();
		double y = this.getY();
		double rotation = this.getRotation();
		double size = this.getSize();
		double[] xOffset = this.getShape().xOffset;
		double[] yOffset = this.getShape().yOffset;

		for(int i = 0; i < numSides; i++){
			yPoints[i] = y + size * (xOffset[i] * Math.sin(2*Math.PI/360 * rotation) + yOffset[i] * Math.cos(2*Math.PI/360 * rotation));
		}
		return yPoints;
	}

	private double vectorDotProduct(double x1, double y1, double x2, double y2){
			      return (x1 * x2) + (y1 * y2);
	}

	public boolean isCollidingWith(GameObject object){
		int thisSides = this.getShape().numSides;
		int objectSides = object.getShape().numSides;
		
		if(thisSides == 0 && objectSides == 0){
			double distance =  Math.pow((this.getX() - object.getX()), 2) + Math.pow((this.getY() - object.getY()), 2);
			if(distance <= this.getSize() + object.getSize())
				return true;
			else 
				return false;
		}else if(thisSides == 0 && objectSides != 0){
			double[] objectXPoints = object.computeXPoints();
			double[] objectYPoints = object.computeYPoints();
			
			double[] projXPoints = new double[1 + objectSides];
			double[] projYPoints = new double[1 + objectSides];
			// Compile proj axis
			double min = Integer.MAX_VALUE;
			for(int i = 0; i < objectSides; i++){ // Find closes vert to circle
				double distance =  Math.pow((this.getX() - objectXPoints[i]), 2) + Math.pow((this.getY() - objectYPoints[i]), 2);
				if(distance < min){
					min = distance;
					projXPoints[0] = (this.getX() - objectXPoints[i]) / distance;
					projYPoints[0] = (this.getY() - objectYPoints[i]) / distance;
				}
			}

			for(int i = 0; i < objectSides; i++){ // find perpendicular projections from polygon sides
				double projX = - (objectYPoints[(i + 1) % objectSides] - objectYPoints[i]);
				double projY = objectXPoints[(i + 1) % objectSides] - objectXPoints[i];
				double magnitude = Math.sqrt(Math.pow(projX, 2) + Math.pow(projY, 2));
				projXPoints[1 + i] = projX / magnitude;
				projYPoints[1 + i] = projY / magnitude;
			}

			for(int i = 0; i < 1 + objectSides; i++){ // Check this's proj axies
				double thisMax = vectorDotProduct(projXPoints[i], projYPoints[i], this.getX(), this.getY()) + this.getSize();
				double thisMin = vectorDotProduct(projXPoints[i], projYPoints[i], this.getX(), this.getY()) - this.getSize();
				double objectMax = Integer.MIN_VALUE;
				double objectMin = Integer.MAX_VALUE;

				for(int vert = 0; vert < objectSides; vert++){ // find objectMax and min
					double dotProduct = vectorDotProduct(projXPoints[i], projYPoints[i], objectXPoints[vert], objectYPoints[vert]);
					if(dotProduct > objectMax) objectMax = dotProduct;
					if(dotProduct < objectMin) objectMin = dotProduct;
				}
				// Check if they overlap
				if((thisMax - objectMin < 0) || (objectMax - thisMin) < 0){
					return false;
				}
			}

		}else if(thisSides != 0 && objectSides == 0){
			double[] thisXPoints = this.computeXPoints();
			double[] thisYPoints = this.computeYPoints();
			
			double[] projXPoints = new double[1 + thisSides];
			double[] projYPoints = new double[1 + thisSides];
			// Compile proj axis
			double min = Integer.MAX_VALUE;
			for(int i = 0; i < thisSides; i++){ // Find closes vert to circle
				double distance =  Math.pow((object.getX() - thisXPoints[i]), 2) + Math.pow((object.getY() - thisYPoints[i]), 2);
				if(distance < min){
					min = distance;
					projXPoints[0] = (object.getX() - thisXPoints[i]) / distance;
					projYPoints[0] = (object.getY() - thisYPoints[i]) / distance;
				}
			}

			for(int i = 0; i < thisSides; i++){ // find perpendicular projections from polygon sides
				double projX = - (thisYPoints[(i + 1) % thisSides] - thisYPoints[i]);
				double projY = thisXPoints[(i + 1) % thisSides] - thisXPoints[i];
				double magnitude = Math.sqrt(Math.pow(projX, 2) + Math.pow(projY, 2));
				projXPoints[1 + i] = projX / magnitude;
				projYPoints[1 + i] = projY / magnitude;
			}

			for(int i = 0; i < 1 + thisSides; i++){ // Check object's proj axies
				double objectMax = vectorDotProduct(projXPoints[i], projYPoints[i], object.getX(), object.getY()) + object.getSize();
				double objectMin = vectorDotProduct(projXPoints[i], projYPoints[i], object.getX(), object.getY()) - object.getSize();
				double thisMax = Integer.MIN_VALUE;
				double thisMin = Integer.MAX_VALUE;

				for(int vert = 0; vert < thisSides; vert++){ // find thisMax and min
					double dotProduct = vectorDotProduct(projXPoints[i], projYPoints[i], thisXPoints[vert], thisYPoints[vert]);
					if(dotProduct > thisMax) thisMax = dotProduct;
					if(dotProduct < thisMin) thisMin = dotProduct;
				}
				// Check if they overlap
				if((objectMax - thisMin < 0) || (thisMax - objectMin) < 0){
					return false;
				}
			}
		}else{
			double[] thisXPoints = this.computeXPoints();
			double[] thisYPoints = this.computeYPoints();
			double[] objectXPoints = object.computeXPoints();
			double[] objectYPoints = object.computeYPoints();
			
			double[] projXPoints = new double[thisSides + objectSides];
			double[] projYPoints = new double[thisSides + objectSides];
			// Compile proj axis
			for(int i = 0; i < thisSides; i++){
				double projX = - (thisYPoints[(i + 1) % thisSides] - thisYPoints[i]);
				double projY = thisXPoints[(i + 1) % thisSides] - thisXPoints[i];
				double magnitude = Math.sqrt(Math.pow(projX, 2) + Math.pow(projY, 2));
				projXPoints[i] = projX / magnitude;
				projYPoints[i] = projY / magnitude;
			}

			for(int i = 0; i < objectSides; i++){
				double projX = - (objectYPoints[(i + 1) % objectSides] - objectYPoints[i]);
				double projY = objectXPoints[(i + 1) % objectSides] - objectXPoints[i];
				double magnitude = Math.sqrt(Math.pow(projX, 2) + Math.pow(projY, 2));
				projXPoints[thisSides + i] = projX / magnitude;
				projYPoints[thisSides + i] = projY / magnitude;
			}

			for(int i = 0; i < thisSides + objectSides; i++){ // Check this's proj axies
				double thisMax = Integer.MIN_VALUE;
				double thisMin = Integer.MAX_VALUE;
				double objectMax = Integer.MIN_VALUE;
				double objectMin = Integer.MAX_VALUE;
				for(int vert = 0; vert < thisSides; vert++){ // find thisMax and min
					double dotProduct = vectorDotProduct(projXPoints[i], projYPoints[i], thisXPoints[vert], thisYPoints[vert]);
					if(dotProduct > thisMax) thisMax = dotProduct;
					if(dotProduct < thisMin) thisMin = dotProduct;
				}

				for(int vert = 0; vert < objectSides; vert++){ // find objectMax and min
					double dotProduct = vectorDotProduct(projXPoints[i], projYPoints[i], objectXPoints[vert], objectYPoints[vert]);
					if(dotProduct > objectMax) objectMax = dotProduct;
					if(dotProduct < objectMin) objectMin = dotProduct;
				}

				// Check if they overlap
				if((thisMax - objectMin < 0) || (objectMax - thisMin) < 0){
					return false;
				}
			}
		}
		return true;
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

	public void setVelocity(double [] vel) {  
		this.dx = vel[0];
		this.dy = vel[1];
	}
		   
	public void setXVelocity(double dx) {  
		this.dx = dx;
	}

	public void setYVelocity(double dy) {  
		this.dy = dy;
	}

	public double[] getVelocity() {
		return new double[] {this.dx, this.dy};
	}

	public double getXVelocity(){
		return this.dx;
	}
	
	public double getYVelocity(){
		return this.dy;
	}
	
	public double getSize(){
		return this.size;
	}

	public void setSize(double size){
		this.size = size;
	}

	public Shape getShape(){
		return this.shape;
	}

	public void setShape(Shape shape){
		this.shape = shape;
	}
}

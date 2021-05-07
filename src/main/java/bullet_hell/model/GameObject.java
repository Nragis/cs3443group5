package bullet_hell.model;

import javafx.geometry.*;
import javafx.scene.*;


public class GameObject {
	
	private Node view;
	private Point2D vec = new Point2D(0,0);//Velocity
	private boolean alive = true;
	
	public GameObject(Node v) {
		this.view =v;
	}
	
	//Updates players position	
	public void update() {
		view.setTranslateX(view.getTranslateX() + vec.getX());
   
		view.setTranslateY(view.getTranslateY() + vec.getY());
	    
	}

	//Setters and getters
	public void setVelocity(Point2D velocity) {  
		this.vec = velocity;
	}

		   
	public Point2D getVelocity() {
		return vec;	
	}
	
		    
	public Node getView() {        
		return view;	   
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
	
	/*
	 * Checks if object has been hit
	 * 
	 * @param
	 * GameObject hit
	 * 
	 * @return
	 * getView().getBoundsInParent().intersects(hit.getView().getBoundsInParent());
	 */
	public boolean isColliding(GameObject hit) {
		return getView().getBoundsInParent().intersects(hit.getView().getBoundsInParent());	    
	}	
}
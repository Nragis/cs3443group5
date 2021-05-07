package bullet_hell.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Enemy extends GameObject {
	
	private int life;
	
    public Enemy() {
          
    	super(new Circle(15, 15, 15, Color.RED));
    	life = 5;
    }
    
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
}

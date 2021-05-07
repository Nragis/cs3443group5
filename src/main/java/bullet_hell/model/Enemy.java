package bullet_hell.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Enemy extends GameObject {
    
	private int ennmiesLife;
	
    public Enemy() {
          
    	super(new Circle(15, 15, 15, Color.RED));
    	ennmiesLife = 5;
    }
}

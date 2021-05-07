package bullet_hell.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * Stores Players current game info
 */
public class Player extends GameObject {
	
	private static final double MOVE_SPEED = 4.0;
	private static final double PLAYER_SIZE = 4.0;

	private int lives;
	
	
	/*
	 * Constructor
	 * Initializes player info
	 */
	public Player() {
		super(Shape.TRIANGLE, PLAYER_SIZE);
		this.lives = 5;
	}
		
	public void removeLife() {
		--this.lives; 
	}

	public void removeLife(int ammount) {
		this.lives -= ammount;
	}

	public void move(){ }

	public void moveLeft() {
		this.addX(- MOVE_SPEED);
    }

    public void moveRight() {
		this.addX(MOVE_SPEED);
    }

    public void moveUp() {
		this.addY(- MOVE_SPEED);
    }

    public void moveDown() {
		this.addY(MOVE_SPEED);
    }
}

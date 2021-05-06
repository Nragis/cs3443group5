package bullet_hell.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * Stores Players current game info
 */
public class Player extends GameObject {
	
	private String playerName;
	private int playerScore;
	private int playerShots;
	private int playerLifes;
	private int upgrade;
	
	
	/*
	 * Constructor
	 * Initializes player info
	 */
	public Player() {
		super(new Rectangle(40, 20, Color.BLUE));
		playerScore = 0;
		playerShots = 0;
		playerLifes = 5;
		upgrade = 0;
		//setTranslateX(300);
	}
		
	public void shot() {
		++playerShots;
	}
	
	public void playerHit() {
		--playerLifes; 
	}
	
	public void enemyKilled(){
		playerScore += 1000;
	}
	 
	/*void moveLeft() {
        setTranslateX(getTranslateX() - 5);
    }

    void moveRight() {
        setTranslateX(getTranslateX() + 5);
    }

    void moveUp() {
        setTranslateY(getTranslateY() - 5);
    }

    void moveDown() {
        setTranslateY(getTranslateY() + 5);
    }*/
}
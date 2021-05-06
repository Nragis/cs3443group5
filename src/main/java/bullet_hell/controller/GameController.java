package bullet_hell.controller;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import bullet_hell.model.Button;
import bullet_hell.model.Game;
import bullet_hell.model.GameObject;
import bullet_hell.model.Player;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class GameController {

	@FXML Canvass gameCanvass;

	private Pane root;
	
	private Game gameEngine;
	private HashMap<Button, boolean> buttonPresses;

    private void onUpdate() {
		ArrayList<Button> buttons = new ArrayList<>();;
		if(this.buttonsPressed(Button.SPACE))
			buttons.add(Button.SPACE);
		if(this.buttonsPressed(Button.LEFT))
			buttons.add(Button.LEFT);
		if(this.buttonsPressed(Button.RIGHT))
			buttons.add(Button.RIGHT);
		if(this.buttonsPressed(Button.UP))
			buttons.add(Button.UP);
		if(this.buttonsPressed(Button.DOWN))
			buttons.add(Button.DOWN);

		this.gameEngine.update(buttons);

		paintGame();

		this.buttonPresses.put(Button.SPACE, false);
		this.buttonPresses.put(Button.LEFT, false);
		this.buttonPresses.put(Button.RIGHT, false);
		this.buttonPresses.put(Button.UP, false);
		this.buttonPresses.put(Button.DOWN, false);
    }

	public void paintGame(){
		
	}

	public void initialize(){
		// Initialize Game
		this.gameEngine = new Game();

		// Initialize button presses
		this.buttonPresses.put(Button.SPACE, false);
		this.buttonPresses.put(Button.LEFT, false);
		this.buttonPresses.put(Button.RIGHT, false);
		this.buttonPresses.put(Button.UP, false);
		this.buttonPresses.put(Button.DOWN, false);

		this.root.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.SPACE) {
				this.buttonPresses.put(BUTTON.SPACE, true);
			}
			if (e.getCode() == KeyCode.LEFT) {
				this.buttonPresses.put(BUTTON.LEFT, true);
			}
			if (e.getCode() == KeyCode.RIGHT) {
				this.buttonPresses.put(BUTTON.RIGHT, true);
			}
			if (e.getCode() == KeyCode.UP) {
				this.buttonPresses.put(BUTTON.UP, true);
			}
			if (e.getCode() == KeyCode.DOWN) {
				this.buttonPresses.put(BUTTON.DOWN, true);
			}
		});
		
		// Start animation timer
		AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();
	}
}

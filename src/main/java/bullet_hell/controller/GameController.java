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

import bullet_hell.model.GameObject;
import bullet_hell.model.Player;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class GameController extends Application {

    //private static Stage null;

	private Pane root;

    private List<GameObject> bullets = new ArrayList<>();
    private List<GameObject> enemies = new ArrayList<>();

    private GameObject player;

    private Parent createContent() {
        root = new Pane();
        root.setPrefSize(600, 600);

        player = new Player();
        player.setVelocity(new Point2D(1, 0));
        addGameObject(player, 300, 300);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

    private void addBullet(GameObject bullet, double x, double y) {
        bullets.add(bullet);
        addGameObject(bullet, x, y);
    }

    private void addEnemy(GameObject enemy, double x, double y) {
        enemies.add(enemy);
        addGameObject(enemy, x, y);
    }

    private void addGameObject(GameObject object, double x, double y) {
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
    }

    private void onUpdate() {
        for (GameObject bullet : bullets) {
            for (GameObject enemy : enemies) {
                if (bullet.isColliding(enemy)) {
                    bullet.setAlive(false);
                    enemy.setAlive(false);

                    root.getChildren().removeAll(bullet.getView(), enemy.getView());
                }
            }
        }

        bullets.removeIf(GameObject::isDead);
        enemies.removeIf(GameObject::isDead);

        bullets.forEach(GameObject::update);
        enemies.forEach(GameObject::update);

        player.update();

        if (Math.random() < 0.02) {
            addEnemy(new Enemy(), Math.random() * root.getPrefWidth(), Math.random() * root.getPrefHeight());
        }
    }


    private static class Enemy extends GameObject {
        Enemy() {
            super(new Circle(15, 15, 15, Color.RED));
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.getScene().setOnKeyPressed(e -> {
            
        });
        stage.show();
    }
    
    public void starting() {
    	launch();
    }
}

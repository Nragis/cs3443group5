package bullet_hell.controller;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import bullet_hell.controller.GameController;

public class MainMenuController {

    @FXML
    private Button startGameButton;

    @FXML
    private Button statisticsButton;

    @FXML
    private Button leaderboardButton;

    @FXML
    private Button quitButton;

    @FXML
    void startGameButtonPressed(ActionEvent event) {
        try{
            Stage stage = (Stage) startGameButton.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bullet_hell/view/GameCanvas.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root);

            stage.setTitle("Space Shooter");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch( IOException e ){
            e.printStackTrace();
        }
    }

    @FXML void statisticsButtonPressed(ActionEvent event) {
        try{
            Stage stage = (Stage) startGameButton.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bullet_hell/view/StatisticsMenu.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Space Shooter Statistics");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch( IOException e ){
            e.printStackTrace();
        }
    }

  @FXML
    void leaderboardButtonPressed(ActionEvent event) {

        try{
            Stage stage = (Stage) startGameButton.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bullet_hell/view/LeaderboardMenu.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Space Shooter Leaderboard");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch( IOException e ){
            e.printStackTrace();
        }
    }

    @FXML void quitButtonPressed(ActionEvent event) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
}

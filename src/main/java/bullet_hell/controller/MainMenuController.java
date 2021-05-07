package bullet_hell.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class MainMenuController {

    @FXML
    private Button startGameButton;

    @FXML
    private Button statisticsButton;

    @FXML
    private Button leaderboardButton;

    @FXML
    private Button quitButton;
    
    private GameController Game;
    private Stage stage;

    @FXML 
    void startGameButtonPressed(ActionEvent event) throws Exception {
        
    	Game = new GameController();
    	Stage stage = (Stage) startGameButton.getScene().getWindow(); 
    	
    	Game.start(stage);
    	
    }
    
    @FXML 
    void statisticsButtonPressed(ActionEvent event) {
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
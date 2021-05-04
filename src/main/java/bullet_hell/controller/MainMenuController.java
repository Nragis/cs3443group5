package bullet_hell.controller;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.*;

public class MainMenuController {
	
    @FXML private Button startGameButton;

    @FXML private Button statisticsButton;

    @FXML private Button optionsButton;

    @FXML private Button quitButton;
    
    private GameController Game;
        
    /*
     * Loads game canvas once new game button is pressed
     * Initializes and object of gameController
     */
    @FXML void startGameButtonPressed(ActionEvent event) {
             	
    	Game = new GameController();		
        
    	Stage stage = (Stage) startGameButton.getScene().getWindow();
      
        Game.start(stage);

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

    @FXML void optionsButtonPressed(ActionEvent event) {
        try{
            Stage stage = (Stage) startGameButton.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bullet_hell/view/OptionsMenu.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Space Shooter Options");
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
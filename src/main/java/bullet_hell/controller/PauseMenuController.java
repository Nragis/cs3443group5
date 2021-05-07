package bullet_hell.controller;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.*;

public class PauseMenuController {

    @FXML private Button resumeButton;

    @FXML private Button returnToMenuButton;

    @FXML private Button quitButton;

    //private Game game;

    @FXML void resumeButtonPressed(ActionEvent event) {
        try{
            Stage stage = (Stage) resumeButton.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bullet_hell/view/GameCanvass.fxml"));

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

    @FXML void returnToMenuButtonPressed(ActionEvent event) {
        try{
            Stage stage = (Stage) returnToMenuButton.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bullet_hell/view/MainMenu.fxml"));

            // Save current game to statistics

            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Space Shooter Main Menu");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch( IOException e ){
            e.printStackTrace();
        }
    }

    @FXML void quitButtonPressed(ActionEvent event) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
    
        // Save current game to statistics
        
        stage.close();
    }
    

    public void initialize(){

    }
}
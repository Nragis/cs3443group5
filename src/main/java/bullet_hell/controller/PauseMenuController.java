package bullet_hell.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class PauseMenuController {

    @FXML
    private Button resumeButton;

    @FXML
    private Button returnToMenuButton;

    @FXML
    private Button quitButton;

    //private Game game;

    @FXML
    void resumeButtonPressed(ActionEvent event) {
        try{
            Stage stage = (Stage) resumeButton.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bullet_hell/view/GameCanvass.fxml"));
    
            // Resumes the game. Requires Game class to be functional though
            /*GameController gc = new GameController(this.game);
            loader.setController(gc); */

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

    @FXML
    void returnToMenuButtonPressed(ActionEvent event) {
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

    @FXML
    void quitButtonPressed(ActionEvent event) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
    
        // Save current game to statistics

        stage.close();
    }
    
    // Allows us to save the game
    /*public PauseMenuController(Game game){
        this.game = game;
    }*/

    public void initialize(){

    }
}

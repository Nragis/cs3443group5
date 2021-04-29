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
	
    @FXML private Button startGameButton;

    @FXML private Button statisticsButton;

    @FXML private Button optionsButton;

    @FXML private Button quitButton;
    
    private GameController GameController;
    
    /*
     * Loads game canvas once new game button is pressed
     */
    @FXML void startGameButtonPressed(ActionEvent event) {
            	
    	try{
            Stage stage = (Stage) startGameButton.getScene().getWindow();
            
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

    public void initialize(){

    }

	public GameController getGameController() {
		return GameController;
	}

	public void setGameController(GameController gameController) {
		GameController = gameController;
	}

}

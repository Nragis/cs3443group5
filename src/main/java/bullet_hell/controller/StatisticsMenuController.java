package bullet_hell.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class StatisticsMenuController {

    @FXML
    private Button backButton;

    @FXML
    private Label highScoreName1;

    @FXML
    private Label highScoreNumber1;

    @FXML
    private Label highScoreName2;

    @FXML
    private Label highScoreName3;

    @FXML
    private Label highScoreName4;

    @FXML
    private Label highScoreName5;

    @FXML
    private Label highScoreNumber2;

    @FXML
    private Label highScoreNumber3;

    @FXML
    private Label highScoreNumber4;

    @FXML
    private Label highScoreNumber5;

    @FXML
    private Label attemptsLabel;

    @FXML
    private Label timePlayedLabel;

    @FXML
    private Label bulletsFiredLabel;

    @FXML
    private Label stagesCompletedLabel;

    @FXML
    void backButtonPressed(ActionEvent event) {
        try{
            Stage stage = (Stage) backButton.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bullet_hell/view/MainMenu.fxml"));

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
    
    public void initialize(){

    }
}

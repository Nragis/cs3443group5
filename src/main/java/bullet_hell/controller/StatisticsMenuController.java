package bullet_hell.controller;

import java.io.File;
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

import bullet_hell.model.Statistics;

public class StatisticsMenuController {

    @FXML private Button backButton;

    @FXML private Label attemptsLabel;

    @FXML private Label timePlayedLabel;

    @FXML private Label bulletsFiredLabel;

    @FXML private Label stagesCompletedLabel;

	private Statistics stats;

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
		File f = new File("statistics.json");
		if( f.exists() ){
			this.stats = Statistics.fromJson("statistics.json");
		} else {
			this.stats = new Statistics();
		}

		this.attemptsLabel.setText("Attempts: " + Integer.toString(this.stats.getAttempts()));
		this.timePlayedLabel.setText("Time Played: " + Integer.toString(this.stats.getTimePlayed()));
		this.bulletsFiredLabel.setText("Bullets Fired: " + Integer.toString(this.stats.getBulletsFired()));
		this.stagesCompletedLabel.setText("Stages Completed: " + Integer.toString(this.stats.getStagesCompleted()));
    }
}

package bullet_hell.controller;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import bullet_hell.remote.client.RemoteLeaderboard;
import bullet_hell.model.Leaderboard;
import bullet_hell.model.Statistics;

public class EndGameController {

    @FXML
    private TextField nameTextField;

    @FXML
    private Button proceedButton;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label stageLabel;

    @FXML
    private Label timePlayedLabel;

    @FXML
    private Label bulletsFiredLabel;

	private int score;
	private int stage;
	private int bulletsFired;
	private int timePlayed;

    @FXML
    void proceedButtonPressed(ActionEvent event) {
		Statistics statistics;
		File f = new File("statistics.json");
		if( f.exists() ){
			statistics = Statistics.fromJson("statistics.json");
		} else {
			statistics = new Statistics();
		}

		statistics.setAttempts(statistics.getAttempts() + 1);
		statistics.setStagesCompleted(statistics.getStagesCompleted() + this.stage);
		statistics.setBulletsFired(statistics.getBulletsFired() + this.bulletsFired);
		statistics.setTimePlayed(statistics.getTimePlayed() + this.timePlayed);
		statistics.toJson("statistics.json");
		
		Leaderboard localLeaderboard;
		f = new File("leaderboard.json");
		if( f.exists() ){
			localLeaderboard = Leaderboard.fromJson("leaderboard.json");
		} else {
			localLeaderboard = new Leaderboard();
		}
		localLeaderboard.addScore(this.nameTextField.getText(), this.score);
		localLeaderboard.toJson("leaderboard.json");

		try{
			RemoteLeaderboard remoteLeaderboard = new RemoteLeaderboard("nraigs.com");
			remoteLeaderboard.addScore(this.nameTextField.getText(), this.score);
		}catch (Exception e){
			System.out.println("Remote leaderboard connection failed");
		}

		try{
            Stage stage = (Stage) this.proceedButton.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bullet_hell/view/MainMenu.fxml"));

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

	public void setStats(int score, int stage, int bulletsFired, int timePlayed){
		this.score = score;
		this.scoreLabel.setText("Score: " + Integer.toString(this.score));
		this.stage = stage;
		this.stageLabel.setText("Stage: " + Integer.toString(this.stage));
		this.bulletsFired = bulletsFired;
		this.bulletsFiredLabel.setText("Bullets Fired: " + Integer.toString(this.bulletsFired));
		this.timePlayed = timePlayed;
		this.timePlayedLabel.setText("Time Played: " + Integer.toString((this.timePlayed / 30) / 60) + " minutes " 
				+ Integer.toString((this.timePlayed)/30 - ((this.timePlayed/30) /60) * 60 ) + " seconds");
	}
}


package bullet_hell.controller;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import bullet_hell.model.Leaderboard;
import bullet_hell.remote.client.RemoteLeaderboard;

public class LeaderboardController{

    @FXML private Button backButton;

    @FXML private GridPane localLeaderboardGridPane;

    @FXML private GridPane globalLeaderboardGridPane;

	private Leaderboard localLeaderboard;
	private Leaderboard globalLeaderboard;
	private RowConstraints newRow = new RowConstraints();

    @FXML void backButtonPressed(ActionEvent event) {
		try{
            Stage stage = (Stage) backButton.getScene().getWindow();
            
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

	public void initialize(){
		File local = new File("leaderboard.json");
		if( local.exists() ){
			this.localLeaderboard= Leaderboard.fromJson("leaderboard.json");
		} else {
			this.localLeaderboard= new Leaderboard();
		}
		
		RemoteLeaderboard remoteLeaderboard = new RemoteLeaderboard("nragis.com");
		System.out.println("Remote leaderboard opened");

		//this.globalLeaderboard = remoteLeaderboard.getLeaderboard();
		System.out.println("Remote leaderboard recieved");
		
		this.newRow.setPrefHeight(50);
		
		String[] names = this.localLeaderboard.getNames();
		int[] scores = this.localLeaderboard.getScores();
		for(int i = 0; i < names.length; i++){
			Label name = new Label(names[i] + " - ");
			Label score = new Label(Integer.toString(scores[i]));
			name.setFont(new Font(16));
			score.setFont(new Font(16));
			this.localLeaderboardGridPane.getRowConstraints().add(newRow);
			this.localLeaderboardGridPane.add(name, 0, i);
			this.localLeaderboardGridPane.add(score, 1, i);
		}

		names = remoteLeaderboard.getLeaderboard().getNames();
		scores = this.globalLeaderboard.getScores();
		for(int i = 0; i < names.length; i++){
			Label name = new Label(names[i] + " - ");
			Label score = new Label(Integer.toString(scores[i]));
			name.setFont(new Font(16));
			score.setFont(new Font(16));
			this.globalLeaderboardGridPane.getRowConstraints().add(newRow);
			this.globalLeaderboardGridPane.add(name, 0, i);
			this.globalLeaderboardGridPane.add(score, 1, i);
		}
	}
}


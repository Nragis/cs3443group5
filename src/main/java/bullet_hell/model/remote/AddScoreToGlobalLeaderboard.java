package bullet_hell.model.remote;

import bullet_hell.model.remote.Task;
import bullet_hell.model.Leaderboard;

import java.io.Serializable;

public class AddScoreToGlobalLeaderboard implements Task<Void>, Serializable {
	
	private String name;
	private int score;

    /**
     */
    public AddScoreToGlobalLeaderboard (String name, int score) {
		this.name = name;
		this.score = score;
    }

    /**
     * Get Remote Leaderboard
     */
    public Void execute() {
        Leaderboard leaderboard = Leaderboard.fromJson("leaderboard.json");
		leaderboard.addScore(this.name, this.score);
		leaderboard.toJson("leaderboard.json");
		return null;
    }
}

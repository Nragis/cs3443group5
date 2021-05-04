package bullet_hell.remote.client;

import bullet_hell.remote.both.Task;
import bullet_hell.model.Leaderboard;

import java.io.Serializable;

public class GetGlobalLeaderboard implements Task<Leaderboard>, Serializable {

    /**
     */
    public GetGlobalLeaderboard() {

    }

    /**
     * Get Remote Leaderboard
     */
    public Leaderboard execute() {
        return Leaderboard.fromJson("leaderboard.json");
    }

}

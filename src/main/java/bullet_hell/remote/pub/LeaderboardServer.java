package bullet_hell.remote.pub;

import java.rmi.Remote;
import java.rmi.RemoteException;

import bullet_hell.model.Leaderboard;

public interface LeaderboardServer extends Remote{
	public Leaderboard getLeaderboard() throws RemoteException;

	public void addScore(String name, int score) throws RemoteException;
}

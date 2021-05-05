package bullet_hell;

import bullet_hell.remote.server.LeaderboardServerImpl;

public class Launcher{
    public static void main(String[] args){
		if(args.length > 0 && args[0].equals("server") ){
			LeaderboardServerImpl.main(args);
		} else{
			Main.main(args);
		}
    }
}

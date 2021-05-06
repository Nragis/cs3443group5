package bullet_hell.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.IOException;
import java.io.Serializable;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Leaderboard implements Serializable{
	private String[] names;
	private int[] scores;
	
	/**
	 * Empty constructor
	 */
	public Leaderboard(){
		this.names = new String[100];
		this.scores = new int[100];

		for( int i = 0; i < 100; i++){
			this.names[i] = "abcde";
			this.scores[i] = 0;
		}
	}

	/**
	 * Constructor that takes in a preinitialized leaderboard 
	 * @param String[] names
	 * @param int[] scores
	 */
	public Leaderboard(String[] names, int[] scores){
		this.names = names;
		this.scores = scores;
	}

	/**
	 * Loads a leaderboard object from a json file
	 * @param String fileName
	 */
	public static Leaderboard fromJson(String fileName){
		File file = new File(fileName);
		Gson gson = new Gson();
		try(Reader reader = new FileReader(file)){
			return gson.fromJson(reader, Leaderboard.class);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Saves the leaderboard object as a json file
	 * @param String fileName
	 */
	public void toJson(String fileName){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            File file = new File(fileName);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            gson.toJson(this, writer);
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }	
	}
	
	/**
	 * Getter for this.names
	 * @return String[] this.names
	 */
	public String[] getNames(){
		return this.names;
	}
	
	/**
	 * Setter for this.names
	 * @param String[] names
	 */
	public void setNames(String[] names){
		this.names = names;
	}

	/**
	 * Getter for this.scores
	 * @return int[] scores
	 */
	public int[] getScores(){
		return this.scores;
	}
	
	/**
	 * Setter for this.scores
	 * @param int[] scores
	 */
	public void setScores(int[] scores){
		this.scores= scores;
	}
	
	/**
	 * Adds a score + name to the correct spot on the leaderboard
	 * @param String name
	 * @param int score
	 */
	public void addScore(String name, int score){
		int i;
		for(i = 0; i < scores.length; i++){
			if(this.scores[i] < score)
				break;
		}

		for(int j = scores.length - 2; j >= i; j++){
			this.scores[j + 1] = this.scores[j];
			this.names[j + 1] = this.names[j];
		}

		this.scores[i] = score;
		this.names[i] = name;
	}
	
	/** Removes a score from the leaderboard based on index
	 * @param int index
	 */
	public void removeScore(int index){
		// TODO
	}
}

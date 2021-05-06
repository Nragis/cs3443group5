package bullet_hell.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Statistics{
	private int attempts;
	private int timePlayed;
	private int bulletsFired;
	private int stagesCompleted;

	/**
	 * Empty constructor
	 */
	public Statistics(){
		this.attempts = 0;
		this.timePlayed = 0;
		this.bulletsFired = 0;
		this.stagesCompleted = 0;
	}

	/**
	 * Constructor that takes in preinitialized statistics 
	 * @param int attempts
	 * @param int timePlayed
	 * @param int bulletsFired
	 * @param int stagesCompleted
	 */
	public Statistics(int attempts, int timePlayed, int bulletsFired, int stagesCompleted){
		this.attempts = attempts;
		this.timePlayed = timePlayed;
		this.bulletsFired = bulletsFired;
		this.stagesCompleted = stagesCompleted;
	}

	/**
	 * Loads a leaderboard object from a json file
	 * @param String fileName
	 */
	public static Statistics fromJson(String fileName){
		File file = new File(fileName);
		Gson gson = new Gson();
			try(Reader reader = new FileReader(file)){
				return gson.fromJson(reader, Statistics.class);
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
	
	/** Getter for this.attempts
	 * @return int this.attempts
	 */
	public int getAttempts(){
		return this.attempts;
	}

	/**
	 * Setter for this.attempts
	 * @param int attempts
	 */
	public void setAttempts(int attempts){
		this.attempts = attempts;
	}

	/**
	 * Getter for this.timePlayed
	 * @return int this.timePlayed
	 */
	public int getTimePlayed(){
		return this.timePlayed;
	}

	/**
	 * Setter for this.timePlayed
	 * @param int timePlayed
	 */
	public void setTimePlayed(int timePlayed){
		this.timePlayed = timePlayed;
	}

	/**
	 * Getter for this.bulletsFired
	 * @return int bulletsFired
	 */
	public int getBulletsFired(){
		return this.bulletsFired;
	}

	/**
	 * Setter for this.bulletsFired
	 * @param int bulletsFired
	 */
	public void setBulletsFired(int bulletsFired){
		this.bulletsFired = bulletsFired;
	}

	/** 
	 * Getter for this.stagesCompleted
	 * @return int this.stagesCompleted
	 */
	public int getStagesCompleted(){
		return this.stagesCompleted;
	}

	/**
	 * Setter for this.stagesCompleted
	 * @param int stagesCompleted
	 */
	public void setStagesCompleted(int stagesCompleted){
		this.stagesCompleted = stagesCompleted;
	}
}

package com.jcf.spaceshooter.engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.util.Log;

public class Config {
	private static final String configFilename = "spaceshooter.cfg";
	FileIO fileIO;
	
	// config data
	public boolean soundOn = true;
	public int controlMethod = ShuttleController.CONTROL_TOUCH;
	public int[] highscores = new int[5];
	
	public Config(FileIO fileIO) {
		this.fileIO = fileIO;
		
		// delete all highscores
		for(int i = 0; i < highscores.length; ++i)
			highscores[i] = -1;
	}
	
	public boolean saveSettings() {
		BufferedWriter bw = null;
		
		try {
			OutputStream outputStream = fileIO.writeFile(configFilename);
			bw = new BufferedWriter(new OutputStreamWriter(outputStream));
			
			// save sound setting
			bw.write(Boolean.toString(soundOn));
			bw.newLine();
			Log.d("Config", "Saving sound setting: " + soundOn);
			
			// save control method
			bw.write(Integer.toString(controlMethod));
			bw.newLine();
			Log.d("Config", "Saving control method setting: " + controlMethod);
			
			// save highscores
			for(int i = 0; i < highscores.length; ++i) {
				bw.write(Integer.toString(highscores[i]));
				bw.newLine();
			}
			
			Log.d("Config", "Succesfully saved settings to file: " + configFilename);
			
			return true;
			
		} catch (Exception e) {
			Log.d("Config", "Failed to save settings to file: " + configFilename);
			Log.d("Config", e.toString());
			return false;
		} finally {
			if(bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public boolean loadSettings() {
		// open the settings file
		try {
			InputStream inputStream = fileIO.readFile(configFilename);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			
			// read sound settings
			soundOn = Boolean.parseBoolean(br.readLine());
			Log.d("Config", "Loaded sound setting: " + soundOn);
			
			// read control method
			controlMethod = Integer.parseInt(br.readLine());
			Log.d("Config", "Loaded control method setting: " + controlMethod);
			
			// read highscores
			for(int i = 0; i < 5; ++i)
				highscores[i] = Integer.parseInt(br.readLine());
			
			Log.d("Config", "Config loaded succesfully");
			return true;
		} catch (Exception e) {
			Log.d("Config", "Failed to load settings file: " + configFilename);
			Log.d("Config", e.toString());
			return false;
		}
	}
	
	public void addHighScore(int score) {
		
	}
}

package com.jcf.spaceshooter.engine;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

/*
 * Class that handles audio in our game.
 * We'll use SoundPool for small sounds - loaded fully in memory from 
 * assets and MediaPlayer class for streaming large music from assets.
 */
public class Audio {
	private static final int MAX_STREAMS = 30; 	// maximum concurrent sounds playing
	AssetManager assets;
	SoundPool soundPool;
	
	/*
	 * Creates new Audio class handling
	 * representing the audio subsystem
	 */
	public Audio(Activity activity) {
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
		
		// audio buttons control our sounds
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
	}
	
	/*
	 * Creates a new sound asset (stored in memory)
	 */
	public Sound newSound(String filename) {
		try {
			AssetFileDescriptor assetDescriptor = assets.openFd(filename);
			int soundId = soundPool.load(assetDescriptor, 0);
			return new Sound(soundPool, soundId);
		} catch (Exception e) {
			Log.d("Audio", "Could not create new sound: " + filename);
		}
		
		return null;
	}
	
	/*
	 * Creates a new music asset (streamed from storage)
	 */
	public Music newMusic(String filename) {
		try {
			AssetFileDescriptor assetDescriptor;
			assetDescriptor = assets.openFd(filename);
			Log.d("Audio", "Creating new music object: " + filename);
			return new Music(assetDescriptor);
		} catch (Exception e) {
			Log.d("Audio", "Could not create new music: " + filename);
		}
		return null;
		
	}
	
}

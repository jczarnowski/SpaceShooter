package com.jcf.spaceshooter.engine;

import java.sql.PreparedStatement;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;

public class Music implements OnCompletionListener, OnPreparedListener {
	MediaPlayer mediaPlayer;
	private boolean isPrepared;
	
	/* Creates new music object
	 * representing a file
	 */
	public Music(AssetFileDescriptor assetDescriptor) {
		mediaPlayer = new MediaPlayer();
		try {
			mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
					assetDescriptor.getStartOffset(), assetDescriptor.getLength());
			mediaPlayer.prepareAsync();
			mediaPlayer.setOnCompletionListener(this);
			mediaPlayer.setOnPreparedListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.d("Music", "Succesfully loaded music file");
	}
	
	/*
	 * starts the music playback
	 */
	public void play() {
		if(!isPrepared) {
			//mediaPlayer.prepareAsync();
			return;
		}
			
		//Log.d("asd", "Playing music!");
		
		// if the music is already playing... return
		if(mediaPlayer.isPlaying())
			return;
		
		//Log.d("asd", "Not playing atm - Playing music!");
		mediaPlayer.start();
			
	}
	
	/*
	 * pauses the music playback
	 */
	public void pause() {
		if(!isPrepared)
			return;
		
		if(mediaPlayer.isPlaying())
			mediaPlayer.pause();
	}
	
	/* 
	 * stops the music playback
	 */
	public void stop() {
		if(!isPrepared)
			return;
		
		mediaPlayer.stop();
		synchronized(this) {
			isPrepared = false;
			mediaPlayer.prepareAsync();
		}
	}
	
	public void rewind() {
		if(!isPrepared)
			return;
		
		mediaPlayer.seekTo(0);
	}
	
	public void dispose() {
		if(!isPrepared)
			return;
		
		if (mediaPlayer.isPlaying())
			mediaPlayer.stop();
		mediaPlayer.release();
	}
	
	public boolean isLooping() {
		return mediaPlayer.isLooping();
	}
	
	public void setLooping(Boolean looping) {
		mediaPlayer.setLooping(looping);
	}
	
	public void setVolume(float volume) {
		mediaPlayer.setVolume(volume, volume);
	}
	
	public void onCompletion(MediaPlayer arg0) {
		// after completion mediaplayer needs to be prepared again
		// set a variable stating so...
		synchronized(this) {
			isPrepared = false;
		}
	}

	@Override
	public void onPrepared(MediaPlayer arg0) {
		synchronized(this) {
			isPrepared = true;
		}
		//Log.d("asd", "Media player is prepared!");
	}
}

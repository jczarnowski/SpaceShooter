package com.jcf.spaceshooter.engine;

import android.media.SoundPool;
import android.util.Log;

/*
 * Class to hold simple sounds played
 * with soundPool that is contained in Audio.
 */
public class Sound {
	int soundId;			// id of this sound
	SoundPool soundPool;	// soundpool this sound is in
	
	public Sound(SoundPool soundPool, int soundId) {
		this.soundId = soundId;
		this.soundPool = soundPool;
	}
	
	public void play(float volume) {
		soundPool.play(soundId, volume, volume, 0, 0, 1);
		Log.d("asd", "Playing sound: " + soundId);
	}
	
	public void dispose() {
		soundPool.unload(soundId);
	}
}

package com.gpl.rpg.GotandaRPG.BgmPlay;

import com.gpl.rpg.GotandaRPG.R;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;

public class GameMusic extends Activity {

	public static MediaPlayer mBgm;
	public int title;
	public Context con;
	public String que;

	// public static GameMusic music;

	public GameMusic(Context con, int title) {
		super();
		this.con = con;
		this.title = title;
		this.que = que;

		this.mBgm = MediaPlayer.create(con, title);
	}

	public void GameMusic(String que) {
		if (que == "Stop")
			mBgm.stop();

		// public void GameMusicPlay() {
		// this.mBgm = MediaPlayer.create(this, R.raw.opening);
		// mBgm = MediaPlayer.create(con, title);
		mBgm.setLooping(true);
		// this.mBgm.setVolume(1.0f, 1.0f);
		mBgm.seekTo(5000);

		if (que == "Start")
			mBgm.start();
	}

	public void GameMusicStop() {
		mBgm.stop();
	}

}

package com.gpl.rpg.GotandaRPG.BgmPlay;



import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;


public class GameMusic extends Activity{
	
	public MediaPlayer mBgm;
	public int title;
	public Context con;
	
	public GameMusic(Context con, int title) {
		super();
		this.con = con;
		this.title = title;
	}	
	
	
	public void GameMusicPlay() {
//	this.mBgm = MediaPlayer.create(con, R.raw.opening);
	this.mBgm = MediaPlayer.create(con, title);
	this.mBgm.setLooping(true);
//	this.mBgm.setVolume(1.0f, 1.0f);
//	mBgm.seekTo(0);
	mBgm.start();
	}

}

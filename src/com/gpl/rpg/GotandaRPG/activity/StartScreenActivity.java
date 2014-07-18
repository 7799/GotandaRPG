package com.gpl.rpg.GotandaRPG.activity;

import android.widget.*;

import com.gpl.rpg.GotandaRPG.GotandaRPGApplication;
import com.gpl.rpg.GotandaRPG.GotandaRPGPreferences;
import com.gpl.rpg.GotandaRPG.Dialogs;
import com.gpl.rpg.GotandaRPG.R;
import com.gpl.rpg.GotandaRPG.savegames.Savegames;
import com.gpl.rpg.GotandaRPG.WorldSetup;
import com.gpl.rpg.GotandaRPG.savegames.Savegames.FileHeader;
import com.gpl.rpg.GotandaRPG.controller.Constants;
import com.gpl.rpg.GotandaRPG.resource.tiles.TileManager;
import com.gpl.rpg.GotandaRPG.BgmPlay.GameMusic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import example.midiappli.*;

public final class StartScreenActivity extends Activity {

	public static final int INTENTREQUEST_PREFERENCES = 7;
	public static final int INTENTREQUEST_LOADGAME = 9;

	private boolean hasExistingGame = false;
	private Button startscreen_continue;
	private Button startscreen_newgame;
	private Button startscreen_load;
	private TextView startscreen_currenthero;
	private EditText startscreen_enterheroname;

//	GameMusic music;	//keep1
		example.midiappli.PlayMidi emP = new PlayMidi("");

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final GotandaRPGApplication app = GotandaRPGApplication
				.getApplicationFromActivity(this);
		app.setWindowParameters(this);

		setContentView(R.layout.startscreen);

		TextView tv = (TextView) findViewById(R.id.startscreen_version);
		tv.setText('v' + GotandaRPGApplication.CURRENT_VERSION_DISPLAY);

		startscreen_currenthero = (TextView) findViewById(R.id.startscreen_currenthero);
		startscreen_enterheroname = (EditText) findViewById(R.id.startscreen_enterheroname);
		// startscreen_enterheroname.setImeOptions(EditorInfo.IME_ACTION_DONE);

		startscreen_continue = (Button) findViewById(R.id.startscreen_continue);
		startscreen_continue.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				continueGame(false, Savegames.SLOT_QUICKSAVE, null);
			}
		});

		startscreen_newgame = (Button) findViewById(R.id.startscreen_newgame);
		startscreen_newgame.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (hasExistingGame) {
					comfirmNewGame();
				} else {
					createNewGame();
				}
			}
		});

		Button b = (Button) findViewById(R.id.startscreen_about);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(StartScreenActivity.this,
						AboutActivity.class));
			}
		});

		b = (Button) findViewById(R.id.startscreen_preferences);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(StartScreenActivity.this,
						Preferences.class);
				startActivityForResult(intent, INTENTREQUEST_PREFERENCES);
			}
		});

		startscreen_load = (Button) findViewById(R.id.startscreen_load);
		startscreen_load.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Dialogs.showLoad(StartScreenActivity.this);
			}
		});

		TextView development_version = (TextView) findViewById(R.id.startscreen_dev_version);
		if (GotandaRPGApplication.DEVELOPMENT_INCOMPATIBLE_SAVEGAMES) {
			development_version
					.setText(R.string.startscreen_incompatible_savegames);
			development_version.setVisibility(View.VISIBLE);
		} else if (!GotandaRPGApplication.IS_RELEASE_VERSION) {
			development_version
					.setText(R.string.startscreen_non_release_version);
			development_version.setVisibility(View.VISIBLE);
		}

		final Resources res = getResources();
		TileManager tileManager = app.getWorld().tileManager;
		tileManager.setDensity(res);
		updatePreferences();
		app.getWorldSetup().startResourceLoader(res);

		if (GotandaRPGApplication.DEVELOPMENT_FORCE_STARTNEWGAME) {
			if (GotandaRPGApplication.DEVELOPMENT_DEBUGRESOURCES) {
				continueGame(true, 0, "Debug player");
			} else {
				continueGame(true, 0, "Player");
			}
		} else if (GotandaRPGApplication.DEVELOPMENT_FORCE_CONTINUEGAME) {
			continueGame(false, Savegames.SLOT_QUICKSAVE, null);
		}
		emP.BgmPlay("");	//keep2
	}

	private void updatePreferences() {
		GotandaRPGApplication app = GotandaRPGApplication
				.getApplicationFromActivity(this);
		GotandaRPGPreferences preferences = app.getPreferences();
		preferences.read(this);
		app.getWorld().tileManager.updatePreferences(preferences);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// taki
		// // MediaPlayer mBgm;
		// mBgm = MediaPlayer.create(this, R.raw.opening);
		// // mBgm.setLooping(true);
		// // mBgm.setVolume(1.0f, 1.0f);
		// mBgm.seekTo(4000);
		// mBgm.start();

		// Context ct = getBaseContext();
		// music = new GameMusic(ct , R.raw.opening);
		
		//keep1
//		music = new GameMusic(getBaseContext(), R.raw.opening);
//		music.GameMusic("Start");
		
		emP.BgmPlay("");
		

		String playerName;
		String displayInfo = null;

		FileHeader header = Savegames.quickload(this, Savegames.SLOT_QUICKSAVE);
		if (header != null && header.playerName != null) {
			playerName = header.playerName;
			displayInfo = header.displayInfo;
		} else {
			// Before fileversion 14 (v0.6.7), quicksave was stored in Shared
			// preferences
			SharedPreferences p = getSharedPreferences("quicksave",
					MODE_PRIVATE);
			playerName = p.getString("playername", null);
			if (playerName != null) {
				displayInfo = "level " + p.getInt("level", -1);
			}
		}
		hasExistingGame = (playerName != null);
		setButtonState(playerName, displayInfo);

		if (isNewVersion()) {
			Dialogs.showNewVersion(this);
		}

		boolean hasSavegames = !Savegames.getUsedSavegameSlots(this).isEmpty();
		startscreen_load.setEnabled(hasSavegames);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case INTENTREQUEST_LOADGAME:
			if (resultCode != Activity.RESULT_OK)
				break;
			final int slot = data.getIntExtra("slot", 1);
			continueGame(false, slot, null);
			break;
		case INTENTREQUEST_PREFERENCES:
			updatePreferences();
			break;
		}
	}

	private boolean isNewVersion() {
		final String v = "lastversion";
		SharedPreferences s = getSharedPreferences(
				Constants.PREFERENCE_MODEL_LASTRUNVERSION, MODE_PRIVATE);
		int lastversion = s.getInt(v, 0);
		if (lastversion >= GotandaRPGApplication.CURRENT_VERSION)
			return false;
		Editor e = s.edit();
		e.putInt(v, GotandaRPGApplication.CURRENT_VERSION);
		e.commit();
		return true;
	}

	private void setButtonState(final String playerName,
			final String displayInfo) {
		startscreen_continue.setEnabled(hasExistingGame);
		startscreen_newgame.setEnabled(true);
		if (hasExistingGame) {
			startscreen_currenthero.setText(playerName + ", " + displayInfo);
			startscreen_enterheroname.setText(playerName);
			startscreen_enterheroname.setVisibility(View.GONE);
		} else {
			startscreen_currenthero.setText(R.string.startscreen_enterheroname);
			startscreen_enterheroname.setVisibility(View.VISIBLE);
		}
	}

	private void continueGame(boolean createNewCharacter, int loadFromSlot,
			String name) {
		final WorldSetup setup = GotandaRPGApplication
				.getApplicationFromActivity(this).getWorldSetup();
		setup.createNewCharacter = createNewCharacter;
		setup.loadFromSlot = loadFromSlot;
		setup.newHeroName = name;
		startActivity(new Intent(this, LoadingActivity.class));
	}

	private void createNewGame() {
		String name = startscreen_enterheroname.getText().toString().trim();
		if (name == null || name.length() <= 0) {
			Toast.makeText(this, R.string.startscreen_enterheroname,
					Toast.LENGTH_SHORT).show();
			return;
		}
		continueGame(true, 0, name);
	}

	private void comfirmNewGame() {
		new AlertDialog.Builder(this)
				.setTitle(R.string.startscreen_newgame)
				.setMessage(R.string.startscreen_newgame_confirm)
				.setIcon(android.R.drawable.ic_delete)
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// continueGame(true);
								hasExistingGame = false;
								setButtonState(null, null);
							}
						}).setNegativeButton(android.R.string.cancel, null)
				.create().show();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
		emP.BgmPlay("");	//keep2
	}

	@Override
	protected void onStart() {
		super.onStart();
		Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
		emP.BgmPlay("");	//keep2
	}

	@Override
	protected void onPause() {
		super.onPause();

		// taki
		// if (mBgm!=null)
		// mBgm.stop();
		// music.GameMusic("Stop");
		
//		music.GameMusicStop();	//keep1
			emP.BgmStop();
	}

	@Override
	protected void onStop() {
		super.onStop();
		Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
		// taki
		// if (mBgm!=null)
		// mBgm.stop();
		// music.GameMusic("Stop");
		
			emP.BgmStop();
//		music.GameMusicStop();	//keep1
	}

}

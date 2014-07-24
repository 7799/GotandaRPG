package example.midiappli;

//MIDIファイル形式で再生

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.gpl.rpg.GotandaRPG.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.widget.EditText;
import example.midiappli.*;

@SuppressLint("SdCardPath")
public class PlayMidi {

	private MediaPlayer mediaPlayer = null;
	private EditText temppo_text;

	private String que;
	private String music_file;
	private Context mContext;

	// *************************************************************************
	// コンストラクタ
	// *************************************************************************
	public PlayMidi(String que) {
		// context = contxt;
		this.que = que;
	}

	public PlayMidi() {
		this.que = que;
	}

	public void BgmStop() {
		mediaPlayer.stop();
		mediaPlayer.release();
	}

	public void BgmPause() {
		mediaPlayer.pause();
	}

	public void MidiFilePlay(String MIDI_NAME) {
		music_file = Environment.getExternalStorageDirectory() + "/"
				+ MIDI_NAME + ".mid";
		File f = new File(music_file);
		if (!f.exists()) {
			// example.midiappli.ConCon cON = new ConCon();
			music_file = "android.resource://com.gpl.rpg.GotandaRPG/raw/opening.mid";
			// "/android_asset/gotanda_theme.mid";
			// que="android.resource://com.gpl.rpg.GotandaRPG/raw/" + "opening"
			// ;

		}

		// InputStream is = new FileInputStream("path_to_your_file");
		// FileOutputStream fos = openFileOutput("your_file",
		// Context.MODE_PRIVATE);
		// read from InputStream and write ot OutputStream

		// MediaPlayer _player;

		// mediaPlayer = MediaPlayer.create(mContext ,R.raw.temp);//Create
		// MediaPlayer object with MP3 file under res/raw folder
		// mediaPlayer.start();//Start playing the music
		// _player = new MediaPlayer();
		// que = "android.resource://" + "com.gpl.rpg.GotandaRPG" + "/" +
		// R.raw.gotanda_theme;
		// _player.setDataSource(fileName);

		// que = Environment.getExternalStorageDirectory() + "/"
		// + MIDI_NAME + ".mid";
		// que="@raw/"+MIDI_NAME;

		// f = new File("res/raw/"+ MIDI_NAME);
		// que = f.toString();
		// que = R.raw.gotanda_theme;
		// MediaPlayer mp = MediaPlayer.create(PlayMidi, R.raw.gotanda_theme);
		// que="android.resource://com.gpl.rpg.GotandaRPG/R.raw." +MIDI_NAME;
		// que="/storage/emulated/legacy/temp.mid";

		if (mediaPlayer == null) {
			// midファイルの作成
			// mm.createMidiFile();
			// メディアプレイヤーの作成
			FileInputStream fis = null;
			mediaPlayer = new MediaPlayer();
			try {
				fis = new FileInputStream(music_file);

				if (fis != null) {
					mediaPlayer.setDataSource(fis.getFD());
					// mediaPlayer.setDataSource("/android_asset/gotanda_theme");
					// mediaPlayer.setDataSource("android.resource://com.gpl.rpg.GotandaRPG/raw/opening.mid");
				}
				mediaPlayer.prepare();
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			// ループ再生の設定
			mediaPlayer.setLooping(true);
		}

		if (!mediaPlayer.isPlaying()) {
			// MediaPlayerの再生
			mediaPlayer.setLooping(true);
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.seekTo(5000);
			mediaPlayer.start();
		}
	}

	public void BgmPlay(String que) {
		// mediaPlayer.stop();
		if (que == "")
			MidiFilePlay("nokia");
		if (que == "opening")
			MidiFilePlay("opening2");
		if (que == "field")
			MidiFilePlay("temp");
		// MidiFilePlay("david_guetta-play_hard_ft_ne_yo_akon");
		// que = Environment.getExternalStorageDirectory() + "/"
		// + "david_guetta-play_hard_ft_ne_yo_akon.mid";
		if (que == "monster")
			MidiFilePlay("will_i_am_scream_and_shout_ft_britney_spears");
		if (que.equals(null))
			MidiFilePlay("opening2");

		// if (mediaPlayer == null) {
		// // midファイルの作成
		// // mm.createMidiFile();
		// // メディアプレイヤーの作成
		// FileInputStream fis = null;
		// mediaPlayer = new MediaPlayer();
		// try {
		// fis = new FileInputStream(que);
		//
		// if (fis != null) {
		// mediaPlayer.setDataSource(fis.getFD());
		// }
		// mediaPlayer.prepare();
		// } catch (Exception e) {
		// // TODO 自動生成された catch ブロック
		// e.printStackTrace();
		// }
		// // ループ再生の設定
		// mediaPlayer.setLooping(true);
		// }
		//
		// if (!mediaPlayer.isPlaying()) {
		// // MediaPlayerの再生
		// mediaPlayer.setLooping(true);
		// mediaPlayer.setVolume(1.0f, 1.0f);
		// mediaPlayer.seekTo(5000);
		// mediaPlayer.start();
		// }
	}

}

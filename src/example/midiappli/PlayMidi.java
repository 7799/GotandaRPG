package example.midiappli;

import java.io.FileInputStream;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Environment;
import android.widget.EditText;

@SuppressLint("SdCardPath")
public class PlayMidi {

	private MediaPlayer mediaPlayer = null;
	private EditText temppo_text;

	MakeMidiFile mm;
	
	private String que;
	// *************************************************************************
	// コンストラクタ
	// *************************************************************************
	 public PlayMidi(String que) {
	 // context = contxt;
	 this.que = que;
	 }

	public void BgmStop(){
		mediaPlayer.pause();
//			mediaPlayer.stop();
//			mediaPlayer.release();
	}
	public void BgmPlay(String que) {
//		mediaPlayer.stop();
		if(que=="")
//			que="/storage/emulated/legacy/temp.mid";
			que=Environment.getExternalStorageDirectory()+"/"+"temp.mid";
		if(que=="field")
//			que="/storage/emulated/legacy/opening.mid";
			que=Environment.getExternalStorageDirectory()+"/"+"opening.mid";
		
		if (mediaPlayer == null) {
			// midファイルの作成
//			mm.createMidiFile();
			// メディアプレイヤーの作成
			FileInputStream fis = null;
			mediaPlayer = new MediaPlayer();
			try {
				fis = new FileInputStream(
//						"/data/data/example.midiappli/files/temp.mid");
//						"/data/data/com.gpl.rpg.GotandaRPG/files/temp.mid");
//						Environment.getExternalStorageDirectory()+"temp.mid");
//						"/storage/emulated/legacy/temp.mid");
						que);
				
				if (fis != null) {
					mediaPlayer.setDataSource(fis.getFD());
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

}

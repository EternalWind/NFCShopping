package scut.bgooo.updater.ui;

import java.io.IOException;
import java.util.Vector;

import scut.bgooo.updater.camera.CameraManager;
import scut.bgooo.updater.decoding.CaptureActivityHandler;
import scut.bgooo.updater.decoding.InactivityTimer;
import scut.bgooo.updater.view.ViewfinderView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

public class CaptureActivity extends Activity implements Callback {

	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private TextView txtResult;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.decoder);
		// 初始化 CameraManager
		CameraManager.init(getApplication());

		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		txtResult = (TextView) findViewById(R.id.txtResult);
		txtResult.setText(null);
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		txtResult.setText(null);
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.d("keydown", ".................." + keyCode);
		if (keyCode == KeyEvent.KEYCODE_BACK)// 如果用户按下了返回键
		{
			if (txtResult.getText().equals(null)
					|| txtResult.getText().equals("")) {
				// 提示对话框
				final AlertDialog.Builder builder = new AlertDialog.Builder(
						this);
				builder.setMessage(R.string.msg_sure);
				builder.setCancelable(true);
				builder.setPositiveButton(R.string.btOK,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
								finish();
							}
						});
				builder.setNegativeButton(R.string.btCancel, null);
				builder.show();
			} else {
				restartPreviewAfterDelay(0L);
			}
		}
		return true;
	}

	private void restartPreviewAfterDelay(long delayMS) {
		// TODO Auto-generated method stub
		if (handler != null) {
			handler.sendEmptyMessageDelayed(R.id.restart_preview, delayMS);
		}
		resetStatusView();
	}

	private void resetStatusView() {
		// TODO Auto-generated method stub
		viewfinderView.drawResultBitmap(null);
		viewfinderView.setVisibility(View.VISIBLE);
		txtResult.setText(null);
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats,
					characterSet);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	public void handleDecode(Result obj, Bitmap barcode) {
		inactivityTimer.onActivity();
		viewfinderView.setVisibility(View.VISIBLE);
		viewfinderView.drawResultBitmap(barcode);
		playBeepSoundAndVibrate();
		txtResult.setText(obj.getBarcodeFormat().toString() + ":"
				+ obj.getText());
		Intent intent = new Intent(CaptureActivity.this,
				NFCWriterActivity.class);
		intent.putExtra("Barcode", obj.getText());
		intent.putExtra("Type",obj.getBarcodeFormat().toString());
		Toast.makeText(getApplication(), "开始写标签", 3000).show();
		startActivity(intent);
	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			// The volume on STREAM_SYSTEM is not adjustable, and users found it
			// too loud,
			// so we now play on the music stream.
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

}
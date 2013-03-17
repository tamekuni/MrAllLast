package jp.gr.java_conf.tamekuni.mr_all_last;

import jp.gr.java_conf.tamekuni.condition_to_win.GameManager;
import jp.gr.java_conf.tamekuni.condition_to_win.Wind;
import jp.tamekuni.MrAllLast.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class PointDispActivity extends Activity {

	private static final double WIDTH_RATE = 0.4;

	private GameManager mGm;
	private PointDispActivityManager mPDAm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_point_disp);

		showToast("逆転したい相手プレイヤーを選んで下さい", Toast.LENGTH_SHORT);

		mGm = new GameManager();

		Intent intent = getIntent();
		mGm.setKyotaku(intent.getIntExtra("Kyotaku", 0));
		mGm.setTsumibo(intent.getIntExtra("Tsumibo", 0));
		mGm.setPlayerPoint(Wind.TON, intent.getIntExtra("TonPoint", 0));
		mGm.setPlayerPoint(Wind.NAN, intent.getIntExtra("NanPoint", 0));
		mGm.setPlayerPoint(Wind.SYA, intent.getIntExtra("SyaPoint", 0));
		mGm.setPlayerPoint(Wind.PEI, intent.getIntExtra("PeiPoint", 0));
		mGm.setMe(Wind.toWind(intent.getIntExtra("Me", 0)));
		// mGm.disp();// for debug

		mPDAm = new PointDispActivityManager((Activity) this, mGm);

		// 画面に合わせてEditTextの幅調整
		mPDAm.adjustDisplayWidth(WIDTH_RATE);

		// コールバック処理
		ImageButton rotateBtn = mPDAm.getRotetBtId();
		Button baseBtn = mPDAm.getSeatBtnId(Seat.BASE);
		Button shimotyaBtn = mPDAm.getSeatBtnId(Seat.SHIMOTYA);
		Button toimenBtn = mPDAm.getSeatBtnId(Seat.TOIMEN);
		Button kamityaBtn = mPDAm.getSeatBtnId(Seat.KAMITYA);

		// 回転ボタンを押した時の操作
		rotateBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mPDAm.rotate();
			}
		});

		// 各プレイヤーのボタンを押した時の動作

		baseBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPDAm.nextActivity(Seat.BASE);
			}
		});

		shimotyaBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPDAm.nextActivity(Seat.SHIMOTYA);
			}
		});

		toimenBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPDAm.nextActivity(Seat.TOIMEN);
			}
		});

		kamityaBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPDAm.nextActivity(Seat.KAMITYA);
			}
		});
	}

	private void showToast(String aText, int aLength) {
		Toast.makeText(this, aText, aLength).show();
	}
}

package jp.gr.java_conf.tamekuni.mr_all_last;

import jp.gr.java_conf.tamekuni.condition_to_win.GameManager;
import jp.tamekuni.MrAllLast.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class PointInputActivity extends Activity {

	private static final double WIDTH_RATE = 0.2;

	private GameManager mGm;
	private PointInputActivityManager mPIAm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_point_input);

		mGm = new GameManager();
		mPIAm = new PointInputActivityManager((Activity) this, mGm);

		showToast("ポイントを入力して自家を押して下さい！！", Toast.LENGTH_SHORT);

		// 画面に合わせてEditTextの幅調整
		mPIAm.adjustDisplayWidth(WIDTH_RATE);

		// コールバック処理
		ImageButton rotateBtn = mPIAm.getRotetBtId();
		Button baseBtn = mPIAm.getSeatBtnId(Seat.BASE);
		Button shimotyaBtn = mPIAm.getSeatBtnId(Seat.SHIMOTYA);
		Button toimenBtn = mPIAm.getSeatBtnId(Seat.TOIMEN);
		Button kamityaBtn = mPIAm.getSeatBtnId(Seat.KAMITYA);

		// 回転ボタンを押した時の操作
		rotateBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPIAm.rotate();
			}
		});

		// 東南西北ボタンを押した時の動作
		baseBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPIAm.nextActivity(Seat.BASE);
			}
		});

		shimotyaBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPIAm.nextActivity(Seat.SHIMOTYA);
			}
		});

		toimenBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPIAm.nextActivity(Seat.TOIMEN);
			}
		});

		kamityaBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPIAm.nextActivity(Seat.KAMITYA);
			}
		});
	}

	private void showToast(String aText, int aLength) {
		Toast toast = Toast.makeText(this, aText, aLength);
		toast.setGravity(Gravity.CENTER, 0, 20);
		toast.show();
	}
}

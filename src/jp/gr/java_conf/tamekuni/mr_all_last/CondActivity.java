package jp.gr.java_conf.tamekuni.mr_all_last;

import jp.gr.java_conf.tamekuni.condition_to_win.GameManager;
import jp.gr.java_conf.tamekuni.condition_to_win.Wind;
import jp.tamekuni.MrAllLast.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

public class CondActivity extends Activity {

	private GameManager mGm;
	private CondAcrivityManager mCAm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cond);

		Intent intent = getIntent();

		mGm = new GameManager();
		mGm.setKyotaku(intent.getIntExtra("Kyotaku", 0));
		mGm.setTsumibo(intent.getIntExtra("Tsumibo", 0));
		mGm.setPlayerPoint(Wind.TON, intent.getIntExtra("TonPoint", 0));
		mGm.setPlayerPoint(Wind.NAN, intent.getIntExtra("NanPoint", 0));
		mGm.setPlayerPoint(Wind.SYA, intent.getIntExtra("SyaPoint", 0));
		mGm.setPlayerPoint(Wind.PEI, intent.getIntExtra("PeiPoint", 0));
		mGm.setMe(Wind.toWind(intent.getIntExtra("Me", 0)));

		Wind target = Wind.toWind(intent.getIntExtra("Target", 0));
		mCAm = new CondAcrivityManager((Activity) this, mGm, target);

		showToast(mGm.getPlayerRank(target).toValue() + "位を逆転する条件です!!",
				Toast.LENGTH_SHORT);

		mCAm.setText();

	}

	private void showToast(String aText, int aLength) {
		Toast toast = Toast.makeText(this, aText, aLength);
		toast.setGravity(Gravity.CENTER | Gravity.TOP, 0, 30);
		toast.show();
	}
}

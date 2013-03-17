package jp.gr.java_conf.tamekuni.mr_all_last;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.view.Display;
import android.view.WindowManager;
import jp.gr.java_conf.tamekuni.condition_to_win.GameManager;
import jp.gr.java_conf.tamekuni.condition_to_win.Wind;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

class PointInputActivityManager implements PointInputActivityManagerIF {

	private static final int TOTAL_POINT = 100000;

	private final PointInputActivityInfo mPIAInfo;
	private final GameManager mGm;
	private final Activity mActivity;

	PointInputActivityManager(final Activity aActivity, final GameManager aGm) {
		mActivity = aActivity;
		mPIAInfo = new PointInputActivityInfo(mActivity);
		mGm = aGm;
	}

	@Override
	public void nextActivity(Seat aSeat) {
		setPIAmToGm(aSeat);
		complementPoint();
		int totalPoint = mGm.getTotalPoint();
		// mGm.disp();// for debug
		if (totalPoint != TOTAL_POINT) {
			AlertTotalPoint(totalPoint);
			return;
		}

		Intent intent = new Intent(mActivity.getApplicationContext(),
				PointDispActivity.class);
		intent.putExtra("Kyotaku", mGm.getKyotaku());
		intent.putExtra("Tsumibo", mGm.getTsumibo());
		intent.putExtra("TonPoint", mGm.getPlayerPoint(Wind.TON));
		intent.putExtra("NanPoint", mGm.getPlayerPoint(Wind.NAN));
		intent.putExtra("SyaPoint", mGm.getPlayerPoint(Wind.SYA));
		intent.putExtra("PeiPoint", mGm.getPlayerPoint(Wind.PEI));
		intent.putExtra("Me", mGm.getMe().toValue());
		mActivity.startActivity(intent);
	}

	@Override
	public void adjustDisplayWidth(double aRate) {
		WindowManager wm = (WindowManager) mActivity
				.getSystemService(Activity.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		for (final InputSeatInfo seatInfo : mPIAInfo.mSeatInfo) {
			seatInfo.getEtId().setWidth((int) (disp.getWidth() * aRate));
		}
	}

	@Override
	public void rotate() {
		for (final InputSeatInfo seatInfo : mPIAInfo.mSeatInfo) {
			seatInfo.setWind(seatInfo.getWind().getKamitya());
			seatInfo.getBtnId().setText(seatInfo.getWind().toString());
		}
	}

	@Override
	public Button getSeatBtnId(Seat aSeat) {
		return mPIAInfo.mSeatInfo[aSeat.toValue()].getBtnId();
	}

	@Override
	public EditText getSeatEtId(Seat aSeat) {
		return mPIAInfo.mSeatInfo[aSeat.toValue()].getEtId();
	}

	@Override
	public ImageButton getRotetBtId() {
		return mPIAInfo.getRotateBtnId();
	}

	private void setPIAmToGm(Seat aSeat) {
		int point = 0;
		for (final InputSeatInfo seatInfo : mPIAInfo.mSeatInfo) {
			point = convTextToInt((SpannableStringBuilder) seatInfo.getEtId()
					.getText());

			mGm.setPlayerPoint(seatInfo.getWind(), point * 100);

		}
		point = convTextToInt((SpannableStringBuilder) mPIAInfo.getKyotakuId()
				.getText());
		mGm.setKyotaku(point);

		point = convTextToInt((SpannableStringBuilder) mPIAInfo.getTsumiBoId()
				.getText());
		mGm.setTsumibo(point);

		mGm.setMe(mPIAInfo.mSeatInfo[aSeat.toValue()].getWind());
	}

	private void AlertTotalPoint(int aPoint) {
		new AlertDialog.Builder(mActivity)
				.setTitle("合計点は" + TOTAL_POINT + "点にしてください。！！")
				.setMessage(
						"合計点が" + aPoint + "点になっています。\n"
								+ "ヒント：1箇所空欄にすると自動入力します。")
				.setPositiveButton("OK", null).create().show();
	}

	private int convTextToInt(final SpannableStringBuilder aSp) {
		String str = aSp.toString();
		int ret = 0;

		if (str.equals("") || str.equals("-")) {
			ret = 0;
		} else {
			ret = Integer.valueOf(str).intValue();
		}
		return ret;
	}

	private void complementPoint() {
		List<Wind> blankList = new ArrayList<Wind>(Wind.size());
		SpannableStringBuilder sp;

		for (final InputSeatInfo seatInfo : mPIAInfo.mSeatInfo) {
			sp = (SpannableStringBuilder) seatInfo.getEtId().getText();
			if (sp.toString().equals("")) {
				blankList.add(seatInfo.getWind());
			}
		}
		if (blankList.size() == 1) {
			Wind blankWind = blankList.get(0);
			int point = (TOTAL_POINT - mGm.getTotalPoint()) / 100;
			for (final InputSeatInfo seatInfo : mPIAInfo.mSeatInfo) {
				if (blankWind == seatInfo.getWind()) {
					seatInfo.getEtId().setText(Integer.toString(point));
					break;
				}
			}
			mGm.setPlayerPoint(blankWind, point * 100);
		}
	}
}

package jp.gr.java_conf.tamekuni.mr_all_last;

import jp.gr.java_conf.tamekuni.condition_to_win.GameManager;
import jp.gr.java_conf.tamekuni.condition_to_win.Wind;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

class PointDispActivityManager implements PointDispActivityManagerIF {

	private final PointDispActivityInfo mPDAInfo;
	private final GameManager mGm;
	private final Activity mActivity;

	public PointDispActivityManager(final Activity aActivity,
			final GameManager aGm) {
		mGm = aGm;
		mActivity = aActivity;
		mPDAInfo = new PointDispActivityInfo(mActivity, mGm.getMe());

		setSeatInfoText();
		setKyotakuText();
		setTsumiboText();
	}

	@Override
	public void nextActivity(Seat aSeat) {

		Wind seatWind = mPDAInfo.mSeatInfo[aSeat.toValue()].getWind();

		if (aSeat == Seat.BASE) {
			alertSelectMyself();
			return;
		}

		if (mGm.getPlayerRank(mGm.getMe()).toValue() <= mGm.getPlayerRank(
				seatWind).toValue()) {
			alertTargetRank();
			return;
		}

		Intent intent = new Intent(mActivity.getApplicationContext(),
				CondActivity.class);
		intent.putExtra("Kyotaku", mGm.getKyotaku());
		intent.putExtra("Tsumibo", mGm.getTsumibo());
		intent.putExtra("TonPoint", mGm.getPlayerPoint(Wind.TON));
		intent.putExtra("NanPoint", mGm.getPlayerPoint(Wind.NAN));
		intent.putExtra("SyaPoint", mGm.getPlayerPoint(Wind.SYA));
		intent.putExtra("PeiPoint", mGm.getPlayerPoint(Wind.PEI));
		intent.putExtra("Me", mGm.getMe().toValue());
		intent.putExtra("Target", seatWind.toValue());
		mActivity.startActivity(intent);

	}

	@Override
	public void adjustDisplayWidth(double aRate) {
		WindowManager wm = (WindowManager) mActivity
				.getSystemService(Activity.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		for (final DispSeatInfo seatInfo : mPDAInfo.mSeatInfo) {
			seatInfo.getBtnId().setWidth((int) (disp.getWidth() * aRate));
		}
	}

	@Override
	public void rotate() {
		mGm.setMe(mGm.getMe().getKamitya());
		for (final DispSeatInfo seatInfo : mPDAInfo.mSeatInfo) {
			seatInfo.setWind(seatInfo.getWind().getKamitya());
		}
		setSeatInfoText();
	}

	@Override
	public void setSeatInfoText() {
		for (final DispSeatInfo seatInfo : mPDAInfo.mSeatInfo) {
			makeSeatInfoText(seatInfo);
		}
	}

	private void makeSeatInfoText(DispSeatInfo seatInfo) {
		StringBuilder sb = new StringBuilder();
		Button seatBtn = seatInfo.getBtnId();
		Wind wind = seatInfo.getWind();

		sb.append(wind.toString() + "    ");
		sb.append(mGm.getPlayerRank(wind).toValue() + "位\n");
		sb.append(mGm.getPlayerPoint(wind) + "点\n");

		int diffPoint = mGm.getDiffPoint(wind);
		sb.append("(");
		if (diffPoint >= 0) {
			sb.append("+");
		}
		sb.append(diffPoint + ")");

		seatBtn.setText(sb.toString());
	}

	@Override
	public void setKyotakuText() {
		mPDAInfo.getKyotakuId().setText(String.valueOf(mGm.getKyotaku()));
	}

	@Override
	public void setTsumiboText() {
		mPDAInfo.getTsumiBoId().setText(String.valueOf(mGm.getTsumibo()));
	}

	@Override
	public Button getSeatBtnId(Seat aSeat) {
		return mPDAInfo.mSeatInfo[aSeat.toValue()].getBtnId();
	}

	@Override
	public ImageButton getRotetBtId() {
		return mPDAInfo.getRotateBtnId();
	}

	private void alertTargetRank() {
		new AlertDialog.Builder(mActivity).setTitle("条件を満たしています！！")
				.setMessage("自分より上位のプレイヤーを選択して下さい！！")
				.setPositiveButton("OK", null).create().show();
	}

	private void alertSelectMyself() {
		new AlertDialog.Builder(mActivity).setTitle("自分を選んでいます！！")
				.setMessage("逆転したい相手プレイヤーを選んで下さい！！")
				.setPositiveButton("OK", null).create().show();
	}

}

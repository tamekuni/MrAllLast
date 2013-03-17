package jp.gr.java_conf.tamekuni.mr_all_last;

import jp.gr.java_conf.tamekuni.condition_to_win.Wind;
import jp.tamekuni.MrAllLast.R;
import android.app.Activity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

class PointDispActivityInfo {
	DispSeatInfo[] mSeatInfo;
	private TextView mKyotakuId;
	private TextView mTsumiBoId;
	private ImageButton mRotateBtnId;

	PointDispActivityInfo(final Activity aActivity, final Wind aMe) {
		mSeatInfo = new DispSeatInfo[Seat.size()];
		for (int i = 0; i < Seat.size(); i++) {
			mSeatInfo[i] = new DispSeatInfo();
		}
		initSetId(aActivity, aMe);
	}

	TextView getKyotakuId() {
		return mKyotakuId;
	}

	TextView getTsumiBoId() {
		return mTsumiBoId;
	}

	ImageButton getRotateBtnId() {
		return mRotateBtnId;
	}

	private void initSetId(Activity aActivity, Wind aMe) {
		mKyotakuId = (TextView) aActivity.findViewById(R.id.KyotakuDisp);
		mTsumiBoId = (TextView) aActivity.findViewById(R.id.TsumiboDisp);
		mRotateBtnId = (ImageButton) aActivity.findViewById(R.id.RotateBtn);

		mSeatInfo[Seat.BASE.toValue()].setBtnId((Button) aActivity
				.findViewById(R.id.BaseBtn));
		mSeatInfo[Seat.BASE.toValue()].setWind(aMe);

		mSeatInfo[Seat.SHIMOTYA.toValue()].setBtnId((Button) aActivity
				.findViewById(R.id.ShimotyaBtn));
		mSeatInfo[Seat.SHIMOTYA.toValue()].setWind(aMe.getShimotya());

		mSeatInfo[Seat.TOIMEN.toValue()].setBtnId((Button) aActivity
				.findViewById(R.id.ToimenBtn));
		mSeatInfo[Seat.TOIMEN.toValue()].setWind(aMe.getToimen());

		mSeatInfo[Seat.KAMITYA.toValue()].setBtnId((Button) aActivity
				.findViewById(R.id.KamityaBtn));
		mSeatInfo[Seat.KAMITYA.toValue()].setWind(aMe.getKamitya());
	}
}

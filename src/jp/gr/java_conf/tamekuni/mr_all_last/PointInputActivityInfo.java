package jp.gr.java_conf.tamekuni.mr_all_last;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import jp.gr.java_conf.tamekuni.mr_all_last.InputSeatInfo;
import jp.gr.java_conf.tamekuni.condition_to_win.Wind;
import jp.tamekuni.MrAllLast.R;

class PointInputActivityInfo {

	InputSeatInfo[] mSeatInfo;
	private EditText mKyotakuId;
	private EditText mTsumiBoId;
	private ImageButton mRotateBtnId;

	PointInputActivityInfo(final Activity aActivity) {
		mSeatInfo = new InputSeatInfo[Seat.size()];
		for (int i = 0; i < Seat.size(); i++) {
			mSeatInfo[i] = new InputSeatInfo();
		}
		initSetId(aActivity);
		for (InputSeatInfo seatInfo : mSeatInfo) {
			seatInfo.getBtnId().setText(seatInfo.getWind().toString());
		}
	}

	EditText getKyotakuId() {
		return mKyotakuId;
	}

	EditText getTsumiBoId() {
		return mTsumiBoId;
	}

	ImageButton getRotateBtnId() {
		return mRotateBtnId;
	}

	private void initSetId(Activity aActivity) {
		mKyotakuId = (EditText) aActivity.findViewById(R.id.KyotakuInput);
		mTsumiBoId = (EditText) aActivity.findViewById(R.id.TsumiboInput);
		mRotateBtnId = (ImageButton) aActivity.findViewById(R.id.RotateBtn);

		mSeatInfo[Seat.BASE.toValue()].setBtnId((Button) aActivity
				.findViewById(R.id.BaseBtn));
		mSeatInfo[Seat.BASE.toValue()].setEtId((EditText) aActivity
				.findViewById(R.id.BaseInput));
		mSeatInfo[Seat.BASE.toValue()].setWind(Wind.TON);

		mSeatInfo[Seat.SHIMOTYA.toValue()].setBtnId((Button) aActivity
				.findViewById(R.id.ShimotyaBtn));
		mSeatInfo[Seat.SHIMOTYA.toValue()].setEtId((EditText) aActivity
				.findViewById(R.id.ShimotyaInput));
		mSeatInfo[Seat.SHIMOTYA.toValue()].setWind(Wind.NAN);

		mSeatInfo[Seat.TOIMEN.toValue()].setBtnId((Button) aActivity
				.findViewById(R.id.ToimenBtn));
		mSeatInfo[Seat.TOIMEN.toValue()].setEtId((EditText) aActivity
				.findViewById(R.id.ToimenInput));
		mSeatInfo[Seat.TOIMEN.toValue()].setWind(Wind.SYA);

		mSeatInfo[Seat.KAMITYA.toValue()].setBtnId((Button) aActivity
				.findViewById(R.id.KamityaBtn));
		mSeatInfo[Seat.KAMITYA.toValue()].setEtId((EditText) aActivity
				.findViewById(R.id.KamityaInput));
		mSeatInfo[Seat.KAMITYA.toValue()].setWind(Wind.PEI);
	}
}

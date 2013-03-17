package jp.gr.java_conf.tamekuni.mr_all_last;

import jp.gr.java_conf.tamekuni.condition_to_win.Wind;
import android.widget.Button;

class DispSeatInfo {

	private Button mBtnId;
	private Wind mWind;

	DispSeatInfo() {
		mBtnId = null;
		mWind = Wind.TON;
	}

	Button getBtnId() {
		return mBtnId;
	}

	void setBtnId(Button aBtn) {
		mBtnId = aBtn;
	}

	Wind getWind() {
		return mWind;
	}

	void setWind(Wind aWind) {
		mWind = aWind;
	}
}

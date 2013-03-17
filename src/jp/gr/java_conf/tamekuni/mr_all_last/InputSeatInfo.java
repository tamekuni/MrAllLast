package jp.gr.java_conf.tamekuni.mr_all_last;

import jp.gr.java_conf.tamekuni.condition_to_win.Wind;
import android.widget.Button;
import android.widget.EditText;

class InputSeatInfo {
	private Button mBtnId;
	private EditText mEtId;
	private Wind mWind;

	InputSeatInfo() {
		mBtnId = null;
		mWind = Wind.TON;
	}

	Button getBtnId() {
		return mBtnId;
	}

	void setBtnId(Button aBtn) {
		mBtnId = aBtn;
	}

	EditText getEtId() {
		return mEtId;
	}

	void setEtId(EditText aEtId) {
		mEtId = aEtId;
	}

	Wind getWind() {
		return mWind;
	}

	void setWind(Wind aWind) {
		mWind = aWind;
	}
}

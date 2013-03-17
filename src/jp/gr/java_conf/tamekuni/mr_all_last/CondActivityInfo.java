package jp.gr.java_conf.tamekuni.mr_all_last;

import jp.tamekuni.MrAllLast.R;
import android.app.Activity;
import android.widget.TextView;

class CondActivityInfo {

	private TextView mDirectRonId;
	private TextView mRonId;
	private TextView mTsumoId;

	CondActivityInfo(final Activity aActivity) {
		initSetId(aActivity);
	}

	TextView getDirectRonId() {
		return mDirectRonId;
	}

	TextView getRonId() {
		return mRonId;
	}

	TextView getTsumoId() {
		return mTsumoId;
	}

	private void initSetId(Activity aActivity) {
		mDirectRonId = (TextView) aActivity.findViewById(R.id.DirectRonMsg);
		mRonId = (TextView) aActivity.findViewById(R.id.RonMsg);
		mTsumoId = (TextView) aActivity.findViewById(R.id.TsumoMsg);

	}
}

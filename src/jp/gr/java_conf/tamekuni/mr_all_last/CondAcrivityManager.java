package jp.gr.java_conf.tamekuni.mr_all_last;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import jp.gr.java_conf.tamekuni.condition_to_win.AgariType;
import jp.gr.java_conf.tamekuni.condition_to_win.CondManagerForMjTable;
import jp.gr.java_conf.tamekuni.condition_to_win.Fu;
import jp.gr.java_conf.tamekuni.condition_to_win.GameManager;
import jp.gr.java_conf.tamekuni.condition_to_win.Han;
import jp.gr.java_conf.tamekuni.condition_to_win.ParentChild;
import jp.gr.java_conf.tamekuni.condition_to_win.Wind;

class CondAcrivityManager implements CondAcrivityManagerIF {

	private final GameManager mGm;
	private final CondActivityInfo mCAinfo;
	private final Wind mTarget;
	private final Activity mAcrivity;

	public CondAcrivityManager(final Activity aActivity, final GameManager aGm,
			final Wind aTarget) {
		mGm = aGm;
		mTarget = aTarget;
		mAcrivity = aActivity;
		mCAinfo = new CondActivityInfo(mAcrivity);
	}

	@Override
	public void setText() {

		ParentChild parentChild = mGm.getParentChild(mGm.getMe());

		List<CondManagerForMjTable> directRonCondList = makeCondList(
				parentChild, AgariType.DIRECT_RON);
		List<CondManagerForMjTable> ronCondList = makeCondList(parentChild,
				AgariType.RON);
		List<CondManagerForMjTable> tsumoCondList = makeCondList(parentChild,
				AgariType.TSUMO);

		String directRonStr = makeCondStr(directRonCondList,
				AgariType.DIRECT_RON);
		String ronStr = makeCondStr(ronCondList, AgariType.RON);
		String tsumoStr = makeCondStr(tsumoCondList, AgariType.TSUMO);

		mCAinfo.getDirectRonId().setText(directRonStr);
		mCAinfo.getRonId().setText(ronStr);
		mCAinfo.getTsumoId().setText(tsumoStr);
	}

	private List<CondManagerForMjTable> makeCondList(ParentChild aParentChild,
			AgariType aType) {

		List<CondManagerForMjTable> list = new ArrayList<CondManagerForMjTable>();

		double condPoint = 0;

		switch (aType) {
		case DIRECT_RON:
			condPoint = mGm.getCondPoint(AgariType.DIRECT_RON, mTarget);
			list.add(new CondManagerForMjTable(Fu.FU25, condPoint, aParentChild));
			list.add(new CondManagerForMjTable(Fu.FU30, condPoint, aParentChild));
			list.add(new CondManagerForMjTable(Fu.FU40, condPoint, aParentChild));
			break;
		case RON:
			condPoint = mGm.getCondPoint(AgariType.RON, mTarget);
			list.add(new CondManagerForMjTable(Fu.FU25, condPoint, aParentChild));
			list.add(new CondManagerForMjTable(Fu.FU30, condPoint, aParentChild));
			list.add(new CondManagerForMjTable(Fu.FU40, condPoint, aParentChild));
			break;
		case TSUMO:
			condPoint = mGm.getCondPoint(AgariType.TSUMO, mTarget);
			list.add(new CondManagerForMjTable(Fu.FU25, condPoint, aParentChild));
			list.add(new CondManagerForMjTable(Fu.FU20, condPoint, aParentChild));
			list.add(new CondManagerForMjTable(Fu.FU30, condPoint, aParentChild));
			break;
		default:
			break;
		}

		return list;
	}

	private String makeCondStr(List<CondManagerForMjTable> aList,
			AgariType aType) {
		StringBuilder strb = new StringBuilder();
		double condPoint = mGm.getCondPoint(aType, mTarget);

		strb.append("最低" + (int) Math.ceil(condPoint) + "点必要。\n");

		for (final CondManagerForMjTable cond : aList) {

			Fu fu = cond.getFu();

			switch (aType) {
			case DIRECT_RON:
			case RON:

				if (fu == Fu.FU25) {
					strb.append("七対子　　：");
				} else if (fu == Fu.FU30) {
					strb.append("平和、鳴き：");
				} else if (fu == Fu.FU40) {
					strb.append("門前　　　：");
				} else {
					strb.append(fu.toString() + ":");
				}

				if (cond.getRonHan() == Han.CANNOT) {
					strb.append("無理です!!\n");
				} else {
					strb.append(cond.getRonHan().toString() + "("
							+ cond.getRonPoint() + "点)で");
					if (condPoint == (double) cond.getRonPoint()) {
						strb.append("同点です!!\n");
					} else {
						strb.append("逆転です!!\n");
					}
				}
				break;

			case TSUMO:
				if (fu == Fu.FU20) {
					strb.append("平和　　　：");
				} else if (fu == Fu.FU25) {
					strb.append("七対子　　：");
				} else if (fu == Fu.FU30) {
					strb.append("それ以外　：");
				} else {
					strb.append(fu.toString() + ":");
				}

				if (cond.getTsumoHan() == Han.CANNOT) {
					strb.append("無理です!!\n");
				} else {
					if (mGm.getParentChild(mGm.getMe()) == ParentChild.PARENT) {
						strb.append(cond.getTsumoHan().toString() + "("
								+ cond.getParentTsumoPoint() + "点オール)で");
						if (mGm.getCondPoint(aType, mTarget) == (double) (cond
								.getParentTsumoPoint() * 3)) {
							strb.append("同点です!!\n");
						} else {
							strb.append("逆転です!!\n");
						}
					} else {
						strb.append(cond.getTsumoHan().toString() + "("
								+ cond.getChildTsumoPoint() + "-"
								+ cond.getParentTsumoPoint() + "点)で");
						if (mGm.getCondPoint(aType, mTarget) == (double) (cond
								.getChildTsumoPoint() * 2 + cond
								.getParentTsumoPoint())) {
							strb.append("同点です!!\n");
						} else {
							strb.append("逆転です!!\n");
						}
					}
				}
				break;
			default:
				break;
			}
		}
		return strb.toString();
	}
}

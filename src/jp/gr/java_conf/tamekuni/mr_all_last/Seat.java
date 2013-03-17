package jp.gr.java_conf.tamekuni.mr_all_last;

public enum Seat {
	BASE(0), SHIMOTYA(1), TOIMEN(2), KAMITYA(3);

	private int mValue;
	private static final int SIZE = 4;

	private Seat(int aValue) {
		mValue = aValue;
	}

	public int toValue() {
		return mValue;
	}

	public static int size() {
		return SIZE;
	}

	public static Seat toSeat(int aValue) {
		if (aValue == BASE.toValue()) {
			return BASE;
		}
		if (aValue == SHIMOTYA.toValue()) {
			return SHIMOTYA;
		}
		if (aValue == TOIMEN.toValue()) {
			return TOIMEN;
		}
		if (aValue == KAMITYA.toValue()) {
			return KAMITYA;
		}
		return BASE;
	}
}

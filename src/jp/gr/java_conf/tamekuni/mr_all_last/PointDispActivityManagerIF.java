package jp.gr.java_conf.tamekuni.mr_all_last;

import android.widget.Button;
import android.widget.ImageButton;

public interface PointDispActivityManagerIF {
	public void nextActivity(Seat aSeat);

	public void adjustDisplayWidth(double aRate);

	public void rotate();

	public void setSeatInfoText();

	public void setKyotakuText();

	public void setTsumiboText();

	public Button getSeatBtnId(Seat aSeat);

	public ImageButton getRotetBtId();
}

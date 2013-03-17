package jp.gr.java_conf.tamekuni.mr_all_last;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import jp.gr.java_conf.tamekuni.mr_all_last.Seat;

public interface PointInputActivityManagerIF {

	public void nextActivity(Seat aSeat);

	public void adjustDisplayWidth(double aRate);

	public void rotate();

	public EditText getSeatEtId(Seat aSeat);

	public Button getSeatBtnId(Seat aSeat);

	public ImageButton getRotetBtId();

}

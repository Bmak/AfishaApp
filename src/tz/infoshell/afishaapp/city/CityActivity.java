package tz.infoshell.afishaapp.city;

import java.util.ArrayList;

import tz.infoshell.afishaapp.R;
import tz.infoshell.afishaapp.R.id;
import tz.infoshell.afishaapp.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CityActivity extends Activity {

	private Cities _city;
	
	private LinearLayout _framesContainer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.city);
		
		_city = (Cities) getIntent().getSerializableExtra("city");
		
		TextView text = (TextView) findViewById(R.id.city_text);
		text.setText(_city.getCity());
		
		_framesContainer = (LinearLayout) findViewById(R.id.frames_container);
        for (int i = 0; i < _city.getItems().size(); i++) {
            ItemFrame frame = new ItemFrame(this, _city.getItem(i));
            
            _framesContainer.addView(frame);
        }
		
	}
}

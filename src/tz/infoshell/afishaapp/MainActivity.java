package tz.infoshell.afishaapp;

import java.util.ArrayList;

import tz.infoshell.afishaapp.city.Cities;
import tz.infoshell.afishaapp.city.CityActivity;
import tz.infoshell.afishaapp.service.AfishaService;
import tz.infoshell.afishaapp.service.BootBroadReceiv;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

public class MainActivity extends ListActivity implements
		OnCheckedChangeListener {

	public static final String LOG_TAG = "myLog";
	private final String PATH = "http://s.afisha.ru/Afisha7Files/Rss/popular.xml";
	private ArrayList<Cities> cityList;
	private Switch _serviceSwitcher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		cityList = new ArrayList<Cities>();

		_serviceSwitcher = (Switch) findViewById(R.id.switch_service);
		_serviceSwitcher.setOnCheckedChangeListener(this);

		init();
	}

	public void init() {
		if (isNetworkAvailable()) {
			new AsyncData(this, cityList).execute(PATH);
		} else {
			showToast("No Network Connection!!!");
		}
	}

	public void showToast(String msg) {
		Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
	}

	public boolean isNetworkAvailable() {
		ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(MainActivity.this, CityActivity.class);

		intent.putExtra("city", (Cities) cityList.get(position));
		startActivity(intent);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		Log.d(MainActivity.LOG_TAG, "CHANGE CHECK " + isChecked);
		
		Intent intent = new Intent(this, AfishaService.class);
		if (isChecked) {
			startService(intent.putExtra("time", 3).putExtra("label", "Call 1"));
			startService(intent.putExtra("time", 1).putExtra("label", "Call 2"));
			startService(intent.putExtra("time", 4).putExtra("label", "Call 3"));
		} else {
			stopService(intent);
		}
		
		int flag = (isChecked ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
				: PackageManager.COMPONENT_ENABLED_STATE_DISABLED);

		ComponentName component = new ComponentName(MainActivity.this, BootBroadReceiv.class);
		getPackageManager().setComponentEnabledSetting(component, flag, PackageManager.DONT_KILL_APP);
	}
}

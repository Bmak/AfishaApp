package tz.infoshell.afishaapp.service;

import java.util.concurrent.TimeUnit;

import tz.infoshell.afishaapp.MainActivity;
import tz.infoshell.afishaapp.R;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;


public class AfishaService extends IntentService {

	public AfishaService() {
		super("afisha service");
	}
	
	@Override
	public void onCreate() {
	    super.onCreate();
	    Log.d(MainActivity.LOG_TAG, "onCreate");
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		int tm = intent.getIntExtra("time", 0);
	    String label = intent.getStringExtra("label");
	    Log.d(MainActivity.LOG_TAG, "onHandleIntent start " + label);
	    try {
	      TimeUnit.SECONDS.sleep(tm);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	    Log.d(MainActivity.LOG_TAG, "onHandleIntent end " + label);
	}
	
	@Override
	public void onDestroy() {
	    super.onDestroy();
	    Log.d(MainActivity.LOG_TAG, "onDestroy");
	}
}

package tz.infoshell.afishaapp.service;

import tz.infoshell.afishaapp.MainActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootBroadReceiv extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(MainActivity.LOG_TAG, "onReceive " + intent.getAction());
	    context.startService(new Intent(context, AfishaService.class));
	}
	
	
}

package com.bluebox.james;
import android.content.Context;

public class Application extends android.app.Application {
	public static final String TAG = "JAMES";
	private static Context sContext;

	public void onCreate() {
		sContext = getApplicationContext();
		Mock.init();
	}
	
	public static Context getContext() {
		return sContext;
	}
}

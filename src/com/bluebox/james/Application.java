package com.bluebox.james;
import android.content.Context;

public class Application extends android.app.Application {
	public static final String TAG = "JAMES";
	
    public static final String ARG_FEATURE_ID = "feature_id";
    public static final String ARG_ROOM_ID = "room_id";
    public static final String ARG_ACTION_ID = "action_id";

	private static Context sContext;

	public void onCreate() {
		sContext = getApplicationContext();
		Mock.init();
	}
	
	public static Context getContext() {
		return sContext;
	}
}

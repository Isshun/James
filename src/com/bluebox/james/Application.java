package com.bluebox.james;
import android.content.Context;

import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.featureModel;
import com.bluebox.james.service.RoomService;

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

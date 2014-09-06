package com.bluebox.james;
import org.smallbox.lib.SmallboxLib;

import android.content.Context;

import com.bluebox.james.datasource.JSONDataSource;
import com.bluebox.james.datasource.MockDataSource;

public class Application extends android.app.Application {
	public static final String TAG = "JAMES";
	
    public static final String ARG_FEATURE_ID = "feature_id";
    public static final String ARG_ROOM_ID = "room_id";
//    public static final String ARG_SCENARIO_ID = "action_id";
	public static final String ARG_SCENARIO_POS = "action_pos";

	private static Context sContext;

	public void onCreate() {
		sContext = getApplicationContext();
		SmallboxLib.setContext(sContext);
		
		ApplicationService.getInstance().addDataSource(MockDataSource.class);
		ApplicationService.getInstance().addDataSource(JSONDataSource.class);

		Mock.init();
		
	}
	
	public static Context getContext() {
		return sContext;
	}
}

package org.smallbox.doomotic;
import org.smallbox.doomotic.datasource.JSONDataSource;
import org.smallbox.doomotic.datasource.MockDataSource;
import org.smallbox.lib.SmallboxLib;

import android.content.Context;

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

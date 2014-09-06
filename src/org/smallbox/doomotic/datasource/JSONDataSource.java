package org.smallbox.doomotic.datasource;

import org.smallbox.doomotic.JSONUtils;

import android.util.Log;

public class JSONDataSource {
	private DataSourceListener mListener;

	public JSONDataSource(DataSourceListener listener) {
		mListener = listener;

		Log.i("JSONDataSource", "[JSONDataSource] load devices");
		JSONUtils.loadDevices(mListener);
//
//		Log.i("JSONDataSource", "[JSONDataSource] load features");
//		JSONUtils.loadFeatures(mListener);
//
//		Log.i("JSONDataSource", "[JSONDataSource] load scenarios");
//		JSONUtils.loadScenarios(mListener);

		Log.i("JSONDataSource", "[JSONDataSource] load rooms");
		JSONUtils.loadRooms(mListener);

		mListener.complete();
	}

}

package org.smallbox.doomotic.datasource;

import java.io.IOException;

import org.smallbox.doomotic.Application;
import org.smallbox.doomotic.JSONUtils;

import android.widget.Toast;

public class MockDataSource {
	private DataSourceListener mListener;

	public MockDataSource(DataSourceListener listener) {
		mListener = listener;

		try {
			String [] filenames = Application.getContext().getAssets().list("mock");
			for (String filename: filenames) {
				if (JSONUtils.has(filename) == false) {
					JSONUtils.copyFromAssets("mock/" + filename, filename);
					Toast.makeText(Application.getContext(), "install mock file: " + filename, Toast.LENGTH_SHORT).show();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mListener.complete();
	}
}

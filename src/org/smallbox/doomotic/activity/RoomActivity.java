package org.smallbox.doomotic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.bluebox.james.R;

public class RoomActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.room, menu);
		return true;
	}

}

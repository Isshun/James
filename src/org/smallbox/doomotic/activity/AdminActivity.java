package org.smallbox.doomotic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.bluebox.james.R;

public class AdminActivity extends FragmentActivity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        
        findViewById(R.id.bt_rooms).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(AdminActivity.this, AdminRoomsActivity.class);
				startActivity(intent);
			}
		});
    }

}

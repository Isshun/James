package com.bluebox.james.activity;

import org.smallbox.lib.dialog.BaseDialogFragment.OnCloseListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.bluebox.james.ApplicationService;
import com.bluebox.james.ApplicationService.DeviceFilter;
import com.bluebox.james.R;
import com.bluebox.james.Utils;
import com.bluebox.james.adapter.SwitchListAdapter;
import com.bluebox.james.dialog.DeviceEditDialog;
import com.bluebox.james.model.DeviceBaseModel;

public class AdminSwitchsActivity extends FragmentActivity implements OnCloseListener {
	private SwitchListAdapter mAdapter;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_switchs);

        mAdapter = new SwitchListAdapter();
        
        ListView list = (ListView)findViewById(R.id.list_probes);
        list.setAdapter(mAdapter);
        list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				final DeviceEditDialog dialog = DeviceEditDialog.newInstance((int)id);
				dialog.setOnCloseListener(AdminSwitchsActivity.this);
				dialog.show(getSupportFragmentManager(), "dialog");
			}
		});
        
        refresh();
        
        Utils.hideKeyboard(this);
    }

    private void refresh() {
    	mAdapter.setDevices(ApplicationService.getInstance().getDevices(new DeviceFilter() {
			@Override
			public boolean isMatching(DeviceBaseModel device) {
				return device.isSwitch();
			}
			
		}));
    	mAdapter.notifyDataSetChanged();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.devices, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_add_device:
			final DeviceEditDialog dialog = DeviceEditDialog.newInstance();
			dialog.setOnCloseListener(this);
			dialog.show(getSupportFragmentManager(), "dialog");
        	return true;
        }
        return false;
    }

	@Override
	public void onClose() {
		refresh();
	}
	
}

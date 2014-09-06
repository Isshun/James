package org.smallbox.doomotic.activity;

import org.smallbox.doomotic.ApplicationService;
import org.smallbox.doomotic.Utils;
import org.smallbox.doomotic.ApplicationService.DeviceFilter;
import org.smallbox.doomotic.adapter.SwitchListAdapter;
import org.smallbox.doomotic.dialog.DeviceEditDialog;
import org.smallbox.doomotic.model.DeviceBaseModel;
import org.smallbox.lib.dialog.BaseDialogFragment.OnCloseListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.bluebox.james.R;

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

package com.bluebox.james.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.bluebox.james.R;
import com.bluebox.james.Utils;
import com.bluebox.james.adapter.SwitchListAdapter;

public class AdminSwitchsActivity extends FragmentActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_switchs);

        ListAdapter adapter = new SwitchListAdapter(this, R.layout.view_switchs_list_entry);
        
        ListView list = (ListView)findViewById(R.id.list_probes);
        list.setAdapter(adapter);
        
        Utils.hideKeyboard(this);
    }

}

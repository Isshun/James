package org.smallbox.doomotic.activity;

import org.smallbox.doomotic.Utils;
import org.smallbox.doomotic.adapter.ProbeListAdapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.bluebox.james.R;

public class AdminProbesActivity extends FragmentActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_probes);

        ListAdapter adapter = new ProbeListAdapter(this, R.layout.list_entry_probes);
        
        ListView list = (ListView)findViewById(R.id.list_probes);
        list.setAdapter(adapter);
        
        Utils.hideKeyboard(this);
    }

}

package org.smallbox.doomotic.adapter;

import java.util.List;

import org.smallbox.doomotic.model.DeviceProbeModel;
import org.smallbox.doomotic.service.DoomService;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.bluebox.james.R;

public class ProbeListAdapter extends ArrayAdapter<DeviceProbeModel> {

	private List<DeviceProbeModel> mProbeList;

	public ProbeListAdapter(Context context, int resource) {
		super(context, resource);

		mProbeList = DoomService.getInstance().getProbeList();
	}
	
	@Override
	public int getCount() {
		return mProbeList.size();
	}

	@Override
	public DeviceProbeModel getItem(int pos) {
		return mProbeList.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return mProbeList.get(pos).getId();
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		
		if (view == null) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_entry_probes, null);
		}
		
		EditText editName = (EditText) view.findViewById(R.id.edit_name);
		EditText editId = (EditText) view.findViewById(R.id.edit_id);
		
		DeviceProbeModel probe = mProbeList.get(pos);
		
		editName.setText(probe.getName());
		editId.setText(String.valueOf(probe.getId()));
		
		return view;
	}
}

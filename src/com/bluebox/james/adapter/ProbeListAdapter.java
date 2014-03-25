package com.bluebox.james.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.bluebox.james.R;
import com.bluebox.james.model.ProbeModel;
import com.bluebox.james.service.RoomService;

public class ProbeListAdapter extends ArrayAdapter<ProbeModel> {

	private List<ProbeModel> mProbeList;

	public ProbeListAdapter(Context context, int resource) {
		super(context, resource);

		mProbeList = RoomService.getInstance().getProbeList();
	}
	
	@Override
	public int getCount() {
		return mProbeList.size();
	}

	@Override
	public ProbeModel getItem(int pos) {
		return mProbeList.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return mProbeList.get(pos).getId();
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		
		if (view == null) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_probes_list_entry, null);
		}
		
		EditText editName = (EditText) view.findViewById(R.id.edit_name);
		EditText editId = (EditText) view.findViewById(R.id.edit_id);
		
		ProbeModel probe = mProbeList.get(pos);
		
		editName.setText(probe.getName());
		editId.setText(String.valueOf(probe.getId()));
		
		return view;
	}
}

package com.bluebox.james.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.bluebox.james.R;
import com.bluebox.james.SwitchModel;
import com.bluebox.james.model.ProbeModel;
import com.bluebox.james.service.RoomService;

public class SwitchListAdapter extends ArrayAdapter<SwitchModel> {

	private List<SwitchModel> mSwitchList;

	public SwitchListAdapter(Context context, int resource) {
		super(context, resource);

		mSwitchList = RoomService.getInstance().getSwitchList();
	}
	
	@Override
	public int getCount() {
		return mSwitchList.size();
	}

	@Override
	public SwitchModel getItem(int pos) {
		return mSwitchList.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return mSwitchList.get(pos).getId();
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		
		if (view == null) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_switch_list_entry, null);
		}
		
		EditText editName = (EditText) view.findViewById(R.id.edit_name);
		EditText editId = (EditText) view.findViewById(R.id.edit_id);
		
		SwitchModel sw = mSwitchList.get(pos);
		
		editName.setText(sw.getName());
		editId.setText(String.valueOf(sw.getId()));
		
		return view;
	}
}
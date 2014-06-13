package com.bluebox.james.adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bluebox.james.R;
import com.bluebox.james.model.DeviceBaseModel;
import com.bluebox.james.service.DoomService;

public class DeviceAdapter extends BaseAdapter {

	@Override
	public int getCount() {
		return DoomService.getInstance().getDevices().size();
	}

	@Override
	public Object getItem(int pos) {
		return DoomService.getInstance().getDevices().get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return DoomService.getInstance().getDevices().get(pos).getId();
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		DeviceBaseModel device = DoomService.getInstance().getDevices().get(pos);
		int padding = (int)parent.getContext().getResources().getDimension(R.dimen.list_device_padding);
		
		TextView text = new TextView(parent.getContext());
		//AbsListView.LayoutParams lp = new AbsListView.LayoutParams(100, 100);
//		v.setLayoutParams(lp);
//		v.setBackgroundColor((Integer)getItem(pos));
		text.setText(device.getName());
		text.setPadding(padding, padding, padding, padding);
		return text;
	}

	public List<DeviceBaseModel> getDevices() {
		return DoomService.getInstance().getDevices();
	}
	
}
package com.bluebox.james.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bluebox.james.R;
import com.bluebox.james.model.DeviceBaseModel;

public class SwitchListAdapter extends BaseAdapter {

	private List<DeviceBaseModel> 	mDevices;

	@Override
	public int getCount() {
		return mDevices != null ? mDevices.size() : 0;
	}

	@Override
	public DeviceBaseModel getItem(int pos) {
		return mDevices.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return mDevices.get(pos).getId();
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		
		if (view == null) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_entry_switch, null);
		}
		
		DeviceBaseModel device = mDevices.get(pos);
		
		((TextView)view.findViewById(R.id.lb_device)).setText(device.getName());
		
		return view;
	}

	public void setDevices(List<DeviceBaseModel> devices) {
		mDevices = devices;
	}
}

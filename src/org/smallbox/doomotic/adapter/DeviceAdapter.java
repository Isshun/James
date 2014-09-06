package org.smallbox.doomotic.adapter;

import java.util.List;

import org.smallbox.doomotic.model.DeviceBaseModel;
import org.smallbox.doomotic.service.DoomService;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bluebox.james.R;

public class DeviceAdapter extends BaseAdapter {
	private List<DeviceBaseModel> 	mDevices;

	public DeviceAdapter() {
		mDevices = DoomService.getInstance().getDevices();
	}
	
	@Override
	public int getCount() {
		return DoomService.getInstance().getDevices().size() + 1;
	}

	@Override
	public Object getItem(int pos) {
		if (pos > 0) {
			return DoomService.getInstance().getDevices().get(pos - 1);
		}
		return null;
	}

	@Override
	public long getItemId(int pos) {
		if (pos > 0) {
			return DoomService.getInstance().getDevices().get(pos - 1).getId();
		}
		return 0;
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		int padding = (int)parent.getContext().getResources().getDimension(R.dimen.list_device_padding);
		
		TextView text = new TextView(parent.getContext());
		if (pos > 0) {
			DeviceBaseModel device = DoomService.getInstance().getDevices().get(pos - 1);
			text.setText(device.getName());
		}
		text.setPadding(padding, padding, padding, padding);
		
		return text;
	}

	public List<DeviceBaseModel> getDevices() {
		return DoomService.getInstance().getDevices();
	}

	public DeviceBaseModel getDevice(int pos) {
		if (pos > 0) {
			return DoomService.getInstance().getDevices().get(pos - 1);
		}
		return null;
	}

	public int indexOf(DeviceBaseModel device) {
		return mDevices.indexOf(device) + 1;
	}
	
}
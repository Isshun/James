package com.bluebox.james.dialog;

import org.smallbox.lib.dialog.BaseDialogFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.bluebox.james.ApplicationService;
import com.bluebox.james.JSONUtils;
import com.bluebox.james.R;
import com.bluebox.james.model.DeviceBaseModel;
import com.bluebox.james.service.DoomService;

public class DeviceEditDialog extends BaseDialogFragment {
	private static final String ARG_DEVICE_ID = "device_id";

	public static DeviceEditDialog newInstance(int deviceId) {
		DeviceEditDialog fragment = new DeviceEditDialog();
		Bundle args = new Bundle();
		args.putInt(ARG_DEVICE_ID, deviceId);
		fragment.setArguments(args);
		return fragment;
	}

	public static DeviceEditDialog newInstance() {
		return newInstance(0);
	}

	protected int 				mColor;
	private EditText 			mEditId;
	private EditText 			mEditName;
	private DeviceBaseModel 	mDevice;

	@Override
	protected View onCreateDialog(LayoutInflater inflater) {
		final View view = inflater.inflate(R.layout.dialog_device, null);
		
		mEditName = (EditText)view.findViewById(R.id.edit_name);
		mEditId = (EditText)view.findViewById(R.id.edit_id);
		
		mDevice = ApplicationService.getInstance().getDevice(getArguments().getInt(ARG_DEVICE_ID, 0));
		if (mDevice != null) {
			customize(mDevice);
		}
		
		setTitle(R.string.title_dialog_create_feature);
		setPositiveButton(mDevice != null ? R.string.bt_dialog_save : R.string.bt_dialog_create);
		setNegativeButton(R.string.bt_dialog_cancel);
		
		return view;
	}

	private void customize(DeviceBaseModel device) {
		mEditId.setText(String.valueOf(device.getDeviceId()));
		mEditName.setText(device.getName());
	}

	@Override
	protected void onCancel() {
	}

	@Override
	protected void onConfirm() {
		int deviceId = Integer.valueOf(mEditId.getText().toString());
		String deviceName = mEditName.getText().toString();
		
		if (mDevice == null) {
			mDevice = DoomService.getInstance().createDevice(deviceName, deviceId);
		}
		
		mDevice.setName(deviceName);
		mDevice.setDeviceId(deviceId);
		
		JSONUtils.saveDevices();
	}

	public int getColor() {
		return mColor;
	}
}

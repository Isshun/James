package org.smallbox.doomotic.dialog;

import org.smallbox.doomotic.adapter.DeviceAdapter;
import org.smallbox.doomotic.model.DeviceBaseModel;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.bluebox.james.R;

public class SelectDeviceDialogFragment extends BaseDialogFragment {
	
	protected DeviceBaseModel mDevice;

	@Override
	protected void onCreateDialog() {
        final ListAdapter adapter = new DeviceAdapter();
        final ListView list = new ListView(getActivity());
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				mDevice = (DeviceBaseModel)adapter.getItem(pos);
				mHasBeenActivated = true;
				save();
			}
		});
		
		setTitle(R.string.title_dialog_select_color);
		setView(list);
		setNegativeButton(R.string.bt_dialog_cancel);
    }

	public DeviceBaseModel getDevice() {
		return mDevice;
	}

	@Override
	protected void onCancel() {
	}

	@Override
	protected void onSave() {
	}
}

package org.smallbox.doomotic.dialog;

import java.util.ArrayList;
import java.util.List;

import org.smallbox.doomotic.Application;
import org.smallbox.doomotic.adapter.DeviceAdapter;
import org.smallbox.doomotic.model.DeviceBaseModel;
import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.model.ScenarioOptionModel;
import org.smallbox.doomotic.service.DoomService;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bluebox.james.R;

public class EditScenarioDialogFragment extends BaseDialogFragment {
	private static class DeviceEntry {
		public boolean 			isOn;
		public DeviceBaseModel 	device;

		public DeviceEntry(DeviceBaseModel device, boolean isOn) {
			this.isOn = isOn;
			this.device = device;
		}
	}
	
	private static final LinearLayout.LayoutParams spinnerLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
	private static final LinearLayout.LayoutParams textLp = new LinearLayout.LayoutParams(170, LinearLayout.LayoutParams.MATCH_PARENT);
	private static final LinearLayout.LayoutParams linearLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 65);

	private SelectIconDialogFragment 	mSelectIconDialog;
	private SelectColorDialogFragment 	mSelectColorDialog;
	private ScenarioOptionModel 		mScenario;
	private FeatureModel 				mFeature;
	private EditText 					mEditScenarioName;
	private List<DeviceEntry> 			mDeviceEntries;

	@Override
	protected void onCreateDialog() {
		final View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_edit_scenario, null, false);
		final int optionIndex = getArguments().getInt(Application.ARG_SCENARIO_POS);
		mFeature = DoomService.getInstance().getFeature(getArguments().getLong(Application.ARG_FEATURE_ID));
		mScenario = mFeature.getOptions().get(optionIndex);
		mDeviceEntries = new ArrayList<DeviceEntry>();

		// Cutomize dialog
		setTitle(R.string.edit_scenario);
		setView(view);
		setNegativeButton(R.string.bt_dialog_cancel);
		setPositiveButton(R.string.bt_dialog_save);

		mEditScenarioName = (EditText)view.findViewById(R.id.edit_scenario_name);
		mEditScenarioName.setText(mScenario.getLabel());

		// "icon" image
		final ImageView imgIcon = (ImageView)view.findViewById(R.id.img_icon);
		if (mScenario.getIcon() != -1) {
			imgIcon.setImageResource(mScenario.getIcon());
		}

		// "select icon" dialog
		mSelectIconDialog = new SelectIconDialogFragment();
		mSelectIconDialog.setOnCloseListener(new SelectIconDialogFragment.OnCloseListener() {
			@Override
			public void onClose() {
				if (mSelectIconDialog.getIcon() != -1) {
					imgIcon.setImageResource(mSelectIconDialog.getIcon());
				} else {
					imgIcon.setImageDrawable(null);
				}
			}
		});
		view.findViewById(R.id.bt_icon).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mSelectIconDialog.show(getFragmentManager().beginTransaction(), "dialog");
			}
		});

		// "color" button
		final View btColor = view.findViewById(R.id.bt_color);
		//btColor.setBackgroundColor(mScenario.getColor());

		view.findViewById(R.id.frame_preview).setBackgroundColor(mScenario.getColor());

		// "select color" dialog
		mSelectColorDialog = new SelectColorDialogFragment();
		mSelectColorDialog.setOnCloseListener(new SelectColorDialogFragment.OnCloseListener() {
			@Override
			public void onClose() {
				btColor.setBackgroundColor(mSelectColorDialog.getColor() != -1 ? mSelectColorDialog.getColor() : Color.TRANSPARENT);
			}
		});
		//    	Bundle args = new Bundle();
		//        args.putLong(Application.ARG_SCENARIO_ID, scenario.getId());
		//        f.setArguments(args);
		view.findViewById(R.id.bt_color).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mSelectColorDialog.show(getFragmentManager().beginTransaction(), "dialog");
			}
		});

		final DeviceAdapter adapter = new DeviceAdapter();
		final ViewGroup layout = (ViewGroup)view.findViewById(R.id.dialog);
		spinnerLp.weight = 1;
		spinnerLp.rightMargin = 20;
		for (DeviceBaseModel device: mScenario.getDevices().keySet()) {
			addDeviceEntry(layout, adapter, device);
		}
		addDeviceEntry(layout, adapter, null);
		
		TextView text = new TextView(getActivity());
		text.setText("Add new action");
		text.setLayoutParams(linearLp);
		text.setPadding(20, 10, 10, 10);
		text.setGravity(Gravity.CENTER_VERTICAL);
		layout.addView(text);
	}

	private void addDeviceEntry(ViewGroup layout, final DeviceAdapter adapter, DeviceBaseModel device) {
		boolean isOn = device != null && mScenario.getDevices().get(device) > 0;
		final DeviceEntry entry = new DeviceEntry(device, isOn);
		mDeviceEntries.add(entry);
		
		LinearLayout lineLayout = new LinearLayout(getActivity());
		lineLayout.setLayoutParams(linearLp);
		layout.addView(lineLayout);

		Spinner spinnerValues = new Spinner(getActivity());
		spinnerValues.setAdapter(new BaseAdapter() {
			@Override
			public View getView(int pos, View arg1, ViewGroup arg2) {
				int padding = (int)getActivity().getResources().getDimension(R.dimen.list_device_padding);
				TextView text = new TextView(getActivity());
				text.setText(pos == 0 ? "Turn on" : "Turn off");
				text.setPadding(padding, padding, padding, padding);
				text.setGravity(Gravity.CENTER_VERTICAL);
				return text;
			}
			
			@Override
			public long getItemId(int arg0) {
				return 0;
			}
			
			@Override
			public Object getItem(int arg0) {
				return null;
			}
			
			@Override
			public int getCount() {
				return 2;
			}
		});
		spinnerValues.setLayoutParams(textLp);
		spinnerValues.setPadding(0, -20, 0, 0);
		spinnerValues.setGravity(Gravity.CENTER_VERTICAL);
		spinnerValues.setSelection(isOn ? 0 : 1);
		spinnerValues.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				entry.isOn = pos == 0;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		lineLayout.addView(spinnerValues);

		Spinner spinner = new Spinner(getActivity());
		spinner.setAdapter(adapter);
		spinner.setLayoutParams(spinnerLp);
		spinner.setPadding(0, -20, 0, 0);
		spinner.setSelection(adapter.getDevices().indexOf(device));
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				entry.device = adapter.getDevice(pos);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		spinner.setSelection(adapter.indexOf(device));
		lineLayout.addView(spinner);
	}

	@Override
	protected void onSave() {
		// Set label
		mScenario.setLabel(mEditScenarioName.getText().toString());

		// Set icon
		if (mSelectIconDialog.hasBeenActivated()) {
			mScenario.setIcon(mSelectIconDialog.getIcon());
		}

		// Set color
		if (mSelectColorDialog.hasBeenActivated()) {
			mScenario.setColor(mSelectColorDialog.getColor());
		}
		
		mScenario.getDevices().clear();
		for (DeviceEntry entry: mDeviceEntries) {
			mScenario.addDevice(entry.device, entry.isOn ? 1 : 0);
		}

		DoomService.getInstance().saveScenario(mScenario);
	}

	@Override
	protected void onCancel() {
	}
}
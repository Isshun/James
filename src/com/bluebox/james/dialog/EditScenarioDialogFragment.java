package com.bluebox.james.dialog;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bluebox.james.Application;
import com.bluebox.james.R;
import com.bluebox.james.adapter.DeviceAdapter;
import com.bluebox.james.model.DeviceBaseModel;
import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.ScenarioOptionModel;
import com.bluebox.james.service.DoomService;

public class EditScenarioDialogFragment extends BaseDialogFragment {

	private SelectIconDialogFragment 	mSelectIconDialog;
	private SelectColorDialogFragment 	mSelectColorDialog;
	private ScenarioOptionModel 		mScenario;
	private FeatureModel 				mFeature;
	private EditText 					mEditScenarioName;

	@Override
	protected void onCreateDialog() {
		final View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_edit_scenario, null, false);
		final int optionIndex = getArguments().getInt(Application.ARG_SCENARIO_POS);
		mFeature = DoomService.getInstance().getFeature(getArguments().getLong(Application.ARG_FEATURE_ID));
		mScenario = mFeature.getOptions().get(optionIndex);

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
		final LinearLayout.LayoutParams spinnerLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		final LinearLayout.LayoutParams textLp = new LinearLayout.LayoutParams(170, LinearLayout.LayoutParams.MATCH_PARENT);
		final LinearLayout.LayoutParams linearLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 65);
		spinnerLp.weight = 1;
		spinnerLp.rightMargin = 20;
		for (DeviceBaseModel device: mScenario.getDevices().keySet()) {
			Spinner spinner = new Spinner(getActivity());
			
			LinearLayout lineLayout = new LinearLayout(getActivity());
			lineLayout.setLayoutParams(linearLp);
			layout.addView(lineLayout);

			TextView text = new TextView(getActivity());
			text.setText("Turn " + (mScenario.getDevices().get(device) > 0 ? "on" : "off"));
			text.setLayoutParams(textLp);
			text.setPadding(20, 10, 10, 10);
			text.setGravity(Gravity.CENTER_VERTICAL);
			lineLayout.addView(text);
			
			//			Spinner spinner = (Spinner)view.findViewById(R.id.edit_device_1_id);
			int pos = adapter.getDevices().indexOf(device);
			spinner.setAdapter(adapter);
			spinner.setLayoutParams(spinnerLp);
			spinner.setPadding(0, -20, 0, 0);
			spinner.setSelection(pos);
			lineLayout.addView(spinner);
		}
		
		TextView text = new TextView(getActivity());
		text.setText("Add new action");
		text.setLayoutParams(linearLp);
		text.setPadding(20, 10, 10, 10);
		text.setGravity(Gravity.CENTER_VERTICAL);
		layout.addView(text);
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

		mScenario.commit();
	}

	@Override
	protected void onCancel() {
	}
}
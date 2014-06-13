package com.bluebox.james.dialog;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.bluebox.james.Application;
import com.bluebox.james.R;
import com.bluebox.james.model.DeviceBaseModel;
import com.bluebox.james.model.ScenarioModel;
import com.bluebox.james.service.DoomService;

public class EditScenarioDialogFragment extends BaseDialogFragment {
	
	private SelectIconDialogFragment 	mSelectIconDialog;
	private SelectColorDialogFragment 	mSelectColorDialog;
	private ScenarioModel 				mScenario;

    @Override
	protected void onCreateDialog() {
    	final View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_edit_scenario, null, false);
        mScenario = DoomService.getInstance().getScenario(getArguments().getLong(Application.ARG_SCENARIO_ID));

        // Cutomize dialog
        setTitle(R.string.edit_scenario);
        setView(view);
		setNegativeButton(R.string.bt_dialog_cancel);
		setPositiveButton(R.string.bt_dialog_save);
        
        EditText editScenarioName = (EditText)view.findViewById(R.id.edit_scenario_name);
        editScenarioName.setText(mScenario.getName());
        
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
    	Bundle args = new Bundle();
        args.putLong(Application.ARG_SCENARIO_ID, mScenario.getId());
        mSelectIconDialog.setArguments(args);
        view.findViewById(R.id.bt_icon).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mSelectIconDialog.show(getFragmentManager().beginTransaction(), "dialog");
			}
		});

        // "color" button
        final View btColor = view.findViewById(R.id.bt_color);
        btColor.setBackgroundColor(mScenario.getColor());

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

        int i = 0;
        for (DeviceBaseModel device: mScenario.getDevices().keySet()) {
        	if (i == 0) {
                EditText editIdAction1 = (EditText)view.findViewById(R.id.edit_device_1_id);
                editIdAction1.setText(String.valueOf(device.getId()));
                EditText editValueAction1 = (EditText)view.findViewById(R.id.edit_device_1_value);
                editValueAction1.setText(String.valueOf(mScenario.getDevices().get(device)));
        	}
        	if (i == 1) {
                EditText editIdAction2 = (EditText)view.findViewById(R.id.edit_device_2_id);
                editIdAction2.setText(String.valueOf(device.getId()));
                EditText editValueAction2 = (EditText)view.findViewById(R.id.edit_device_2_value);
                editValueAction2.setText(String.valueOf(mScenario.getDevices().get(device)));
        	}
        	if (i == 2) {
                EditText editIdAction3 = (EditText)view.findViewById(R.id.edit_device_3_id);
                editIdAction3.setText(String.valueOf(device.getId()));
                EditText editValueAction3 = (EditText)view.findViewById(R.id.edit_device_3_value);
                editValueAction3.setText(String.valueOf(mScenario.getDevices().get(device)));
        	}
        	if (i == 3) {
                EditText editIdAction4 = (EditText)view.findViewById(R.id.edit_device_4_id);
                editIdAction4.setText(String.valueOf(device.getId()));
                EditText editValueAction4 = (EditText)view.findViewById(R.id.edit_device_4_value);
                editValueAction4.setText(String.valueOf(mScenario.getDevices().get(device)));
        	}
        	
			i++;
		}
	}
	
	@Override
	protected void onSave() {
		mScenario.setIcon(mSelectIconDialog.getIcon());
		mScenario.setColor(mSelectColorDialog.getColor());
    	mScenario.commit();
	}

	@Override
	protected void onCancel() {
	}
}
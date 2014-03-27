package com.bluebox.james.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.bluebox.james.Application;
import com.bluebox.james.R;
import com.bluebox.james.model.DeviceBaseModel;
import com.bluebox.james.model.FeatureBaseModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.ScenarioModel;
import com.bluebox.james.service.RoomService;

public class EditScenarioDialogFragment extends DialogFragment {
	
	private OnCloseListener mOnCloseListener;

	public static interface OnCloseListener {
		void onClose();
	}

    public void setOnCloseListener(OnCloseListener onCloseListener) {
    	mOnCloseListener = onCloseListener;
	}

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	final View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_edit_scenario, null, false);
    	final RoomModel room = RoomService.getInstance().getRoom(getArguments().getLong(Application.ARG_ROOM_ID));
        final FeatureBaseModel scene = RoomService.getInstance().getFeature(getArguments().getLong(Application.ARG_FEATURE_ID));
        final ScenarioModel scenario = RoomService.getInstance().getScenario(getArguments().getLong(Application.ARG_SCENARIO_ID));

        EditText editScenarioName = (EditText)view.findViewById(R.id.edit_scenario_name);
        editScenarioName.setText(scenario.getName());
        
        // "icon" image
        final ImageView imgIcon = (ImageView)view.findViewById(R.id.img_icon);
        imgIcon.setImageResource(scenario.getIcon());

        // "select icon" dialog
		final SelectIconDialogFragment f = new SelectIconDialogFragment();
		f.setOnCloseListener(new SelectIconDialogFragment.OnCloseListener() {
			@Override
			public void onClose() {
				if (f.getIcon() != -1) {
			        imgIcon.setImageResource(f.getIcon());
				}
			}
		});
    	Bundle args = new Bundle();
        args.putLong(Application.ARG_SCENARIO_ID, scenario.getId());
        f.setArguments(args);
        view.findViewById(R.id.bt_icon).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
		        f.show(getFragmentManager().beginTransaction(), "dialog");
			}
		});

        // "color" button
        final View btColor = view.findViewById(R.id.bt_color);
        btColor.setBackgroundColor(scenario.getColor());

        // "select color" dialog
		final SelectColorDialogFragment colorDialog = new SelectColorDialogFragment();
		f.setOnCloseListener(new SelectIconDialogFragment.OnCloseListener() {
			@Override
			public void onClose() {
				if (colorDialog.getColor() != -1) {
			        btColor.setBackgroundColor(colorDialog.getColor());
				}
			}
		});
//    	Bundle args = new Bundle();
//        args.putLong(Application.ARG_SCENARIO_ID, scenario.getId());
//        f.setArguments(args);
        view.findViewById(R.id.bt_icon).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				colorDialog.show(getFragmentManager().beginTransaction(), "dialog");
			}
		});

        int i = 0;
        for (DeviceBaseModel device: scenario.getDevices().keySet()) {
        	if (i == 0) {
                EditText editIdAction1 = (EditText)view.findViewById(R.id.edit_device_1_id);
                editIdAction1.setText(String.valueOf(device.getId()));
                EditText editValueAction1 = (EditText)view.findViewById(R.id.edit_device_1_value);
                editValueAction1.setText(String.valueOf(scenario.getDevices().get(device)));
        	}
        	if (i == 1) {
                EditText editIdAction2 = (EditText)view.findViewById(R.id.edit_device_2_id);
                editIdAction2.setText(String.valueOf(device.getId()));
                EditText editValueAction2 = (EditText)view.findViewById(R.id.edit_device_2_value);
                editValueAction2.setText(String.valueOf(scenario.getDevices().get(device)));
        	}
        	if (i == 2) {
                EditText editIdAction3 = (EditText)view.findViewById(R.id.edit_device_3_id);
                editIdAction3.setText(String.valueOf(device.getId()));
                EditText editValueAction3 = (EditText)view.findViewById(R.id.edit_device_3_value);
                editValueAction3.setText(String.valueOf(scenario.getDevices().get(device)));
        	}
        	if (i == 3) {
                EditText editIdAction4 = (EditText)view.findViewById(R.id.edit_device_4_id);
                editIdAction4.setText(String.valueOf(device.getId()));
                EditText editValueAction4 = (EditText)view.findViewById(R.id.edit_device_4_value);
                editValueAction4.setText(String.valueOf(scenario.getDevices().get(device)));
        	}
        	
			i++;
		}
        
//        Utils.hideKeyboard(this);

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.edit_scenario)
                .setView(view)
                .setPositiveButton("Save",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        	if (f.getIcon() != -1) {
                        		scenario.setIcon(f.getIcon());
                        	}

//                        	RoomService.getInstance().addAction(feature, new ActionModel(editActionName.getText().toString(), R.drawable.ic_alarm));
                        	mOnCloseListener.onClose();
                        }
                    }
                )
                .setNegativeButton("Cancel", null)
                .create();
    }
}
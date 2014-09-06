package org.smallbox.doomotic.dialog;

import org.smallbox.doomotic.Application;
import org.smallbox.doomotic.NotImplementedException;
import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.model.ScenarioOptionModel;
import org.smallbox.doomotic.service.DoomService;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.bluebox.james.R;

public class NewScenarioDialogFragment extends DialogFragment {
	
	private OnCloseListener mOnCloseListener;

	public static interface OnCloseListener {
		void onClose();
	}

    public void setOnCloseListener(OnCloseListener onCloseListener) {
    	mOnCloseListener = onCloseListener;
	}

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final long featureId = getArguments().getLong(Application.ARG_FEATURE_ID);
        final FeatureModel feature = DoomService.getInstance().getFeature(featureId);
    	final View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_create_scenario, null, false);
    	
    	final EditText editActionName = (EditText)view.findViewById(R.id.edit_scenario_name);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Add new scenario")
                .setView(view)
                .setPositiveButton("Create",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        	throw new NotImplementedException();
//                        	ScenarioOptionModel scenario = DoomService.getInstance().createScenario(editActionName.getText().toString(), R.drawable.ic_alarm);
//                        	feature.addScenarioOption(option);
//                        	DoomService.getInstance().addScenarioToFeature(feature, scenario);
//                        	mOnCloseListener.onClose();
                        }
                    }
                )
                .setNegativeButton("Cancel", null)
                .create();
    }
}
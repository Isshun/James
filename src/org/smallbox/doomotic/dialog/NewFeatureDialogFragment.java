package org.smallbox.doomotic.dialog;

import org.smallbox.doomotic.Application;
import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.model.RoomModel;
import org.smallbox.doomotic.service.DoomService;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.bluebox.james.R;

public class NewFeatureDialogFragment extends DialogFragment {
	
	private OnCloseListener mOnCloseListener;

	public static interface OnCloseListener {
		void onClose();
	}

    public void setOnCloseListener(OnCloseListener onCloseListener) {
    	mOnCloseListener = onCloseListener;
	}

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final long roomId = getArguments().getLong(Application.ARG_ROOM_ID);
        final RoomModel room = DoomService.getInstance().getRoom(roomId);
    	final View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_create_feature, null, false);
    	
    	final EditText editActionName = (EditText)view.findViewById(R.id.edit_feature_name);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Add new feature")
                .setView(view)
                .setPositiveButton("Create",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        	FeatureModel feature = DoomService.getInstance().createFeature(FeatureModel.SCENE_SCENARIO, editActionName.getText().toString(), R.drawable.ic_alarm, Color.GRAY);
                        	DoomService.getInstance().addFeatureToRoom(room, feature);
                        	mOnCloseListener.onClose();
                        }
                    }
                )
                .setNegativeButton("Cancel", null)
                .create();
    }
}
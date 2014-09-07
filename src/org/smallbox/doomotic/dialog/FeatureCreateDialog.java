package org.smallbox.doomotic.dialog;

import org.smallbox.doomotic.Application;
import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.model.RoomModel;
import org.smallbox.doomotic.service.DoomService;
import org.smallbox.lib.dialog.BaseDialogFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;

import com.bluebox.james.R;

public class FeatureCreateDialog extends BaseDialogFragment {

	public static FeatureCreateDialog newInstance(RoomModel room) {
		FeatureCreateDialog f = new FeatureCreateDialog();
    	
		Bundle args = new Bundle();
        args.putInt(Application.ARG_ROOM_ID, room.getId());
        f.setArguments(args);
        
		return f;
	}

	private RoomModel mRoom;
	private EditText mEditActionName;

	@Override
    public void onCreateDialog() {
        setContentView(R.layout.fragment_dialog_create_feature);
    	
        mRoom = DoomService.getInstance().getRoom(getArguments().getInt(Application.ARG_ROOM_ID));
    	mEditActionName = (EditText)findViewById(R.id.edit_feature_name);

        setTitle(R.string.title_dialog_create_feature);
        setPositiveButton(R.string.bt_dialog_create);
        setNegativeButton(R.string.bt_dialog_cancel);
    }

	@Override
	protected void onCancel() {
	}

	@Override
	protected void onConfirm() {
    	FeatureModel feature = DoomService.getInstance().createFeature(FeatureModel.SCENE_SCENARIO, mEditActionName.getText().toString(), R.drawable.ic_alarm, Color.GRAY);
    	DoomService.getInstance().addFeatureToRoom(mRoom, feature);
	}
}
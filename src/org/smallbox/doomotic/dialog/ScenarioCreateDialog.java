package org.smallbox.doomotic.dialog;

import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.service.DoomService;
import org.smallbox.lib.dialog.BaseDialogFragment;

import android.widget.EditText;

import com.bluebox.james.R;

public class ScenarioCreateDialog extends BaseDialogFragment 	{
	private EditText mEditName;

	@Override
	protected void onCreateDialog() {
        setContentView(R.layout.fragment_dialog_create_scenario);
    	setTitle(R.string.title_dialog_create_scenario);
    	
    	mEditName = (EditText)findViewById(R.id.edit_scenario_name);
	}

	@Override
	protected void onCancel() {
	}

	@Override
	protected void onConfirm() {
		DoomService.getInstance().createFeature(FeatureModel.SCENE_SCENARIO, mEditName.getText().toString(), 0, 0);
	}
}
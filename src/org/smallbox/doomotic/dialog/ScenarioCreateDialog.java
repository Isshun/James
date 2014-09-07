package org.smallbox.doomotic.dialog;

import org.smallbox.doomotic.ApplicationService;
import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.service.DoomService;
import org.smallbox.lib.dialog.BaseDialogFragment;

import android.os.Bundle;
import android.widget.EditText;

import com.bluebox.james.R;

public class ScenarioCreateDialog extends BaseDialogFragment {
	private static final String ARG_FEATURE_ID = "feature_id";
	
	private EditText 			mEditName;
	private FeatureModel 		mFeature;

	public static ScenarioCreateDialog newInstance(FeatureModel feature) {
    	Bundle args = new Bundle();
		args.putInt(ARG_FEATURE_ID, feature.getId());
		ScenarioCreateDialog f = new ScenarioCreateDialog();
        f.setArguments(args);
        return f;
	}

	@Override
	protected void onCreateDialog() {
        setContentView(R.layout.fragment_dialog_create_scenario);
    	setTitle(R.string.title_dialog_create_scenario);
		setNegativeButton(R.string.bt_dialog_cancel);
		setPositiveButton(R.string.bt_dialog_create);

		mFeature = ApplicationService.getInstance().getFeature(getArguments().getInt(ARG_FEATURE_ID));
    	mEditName = (EditText)findViewById(R.id.edit_scenario_name);
	}

	@Override
	protected void onCancel() {
	}

	@Override
	protected void onConfirm() {
		if (mFeature != null) {
			DoomService.getInstance().createScenarioOption(mFeature, mEditName.getText().toString(), 0);
		}
	}
}
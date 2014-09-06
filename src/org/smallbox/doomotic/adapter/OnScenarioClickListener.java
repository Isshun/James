package org.smallbox.doomotic.adapter;

import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.model.ScenarioOptionModel;

public interface OnScenarioClickListener {
	void onScenarioClick(FeatureModel feature, ScenarioOptionModel scenario);
}

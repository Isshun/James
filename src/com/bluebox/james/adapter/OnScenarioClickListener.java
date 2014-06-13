package com.bluebox.james.adapter;

import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.ScenarioOptionModel;

public interface OnScenarioClickListener {
	void onScenarioClick(FeatureModel feature, ScenarioOptionModel scenario);
}

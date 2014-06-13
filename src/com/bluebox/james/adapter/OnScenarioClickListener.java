package com.bluebox.james.adapter;

import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.ScenarioModel;

public interface OnScenarioClickListener {
	void onScenarioClick(FeatureModel feature, ScenarioModel scenario);
}

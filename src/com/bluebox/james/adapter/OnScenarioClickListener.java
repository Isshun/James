package com.bluebox.james.adapter;

import com.bluebox.james.model.FeatureBaseModel;
import com.bluebox.james.model.ScenarioModel;

public interface OnScenarioClickListener {
	void onScenarioClick(FeatureBaseModel feature, ScenarioModel scenario);
}

package com.bluebox.james.model.scenario;

import com.bluebox.james.DBHelper;
import com.bluebox.james.model.DeviceBaseModel;
import com.bluebox.james.model.FeatureModel;

public class ScenarioCustom extends ScenarioBase {

	public ScenarioCustom(FeatureModel feature) {
		super(feature);
	}

	@Override
	public void commit() {
		DBHelper.getInstance().updateScenario(this);
	}
	
	public ScenarioCustom asCustom() {
		return (ScenarioCustom)this;
	}

	@Override
	public DeviceBaseModel getDevice() {
		return null;
	}

}

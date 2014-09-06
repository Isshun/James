package com.bluebox.james.model.scenario;

import com.bluebox.james.JSONUtils;
import com.bluebox.james.model.DeviceBaseModel;
import com.bluebox.james.model.FeatureModel;

public class ScenarioCustom extends ScenarioBase {

	public ScenarioCustom(FeatureModel feature) {
		super(feature);
	}

	@Override
	public void commit() {
//		DBHelper.getInstance().updateScenario(this);
		JSONUtils.saveRooms();
	}
	
	public ScenarioCustom asCustom() {
		return (ScenarioCustom)this;
	}

	@Override
	public DeviceBaseModel getDevice() {
		return null;
	}

	@Override
	public int getType() {
		return FeatureModel.SCENE_SCENARIO;
	}

}

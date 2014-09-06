package org.smallbox.doomotic.model.scenario;

import org.smallbox.doomotic.JSONUtils;
import org.smallbox.doomotic.model.DeviceBaseModel;
import org.smallbox.doomotic.model.FeatureModel;

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

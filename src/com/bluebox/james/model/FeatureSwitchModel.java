package com.bluebox.james.model;


public class FeatureSwitchModel extends FeatureBaseModel {
	
	public FeatureSwitchModel(long id, String name, int icon, int color) {
		super(FeatureBaseModel.SCENE_UNKNOW, id, name, icon, color);
	}

	@Override
	public ScenarioModel nextScenario() {
		if (mScenarios.size() == 2) {
			mOn = !mOn;
			int index = mOn ? 1 : 0;
			return mScenarios.get(index);
		}
		return null;
	}


}

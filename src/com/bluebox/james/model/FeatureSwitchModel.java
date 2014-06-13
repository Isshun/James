package com.bluebox.james.model;


public class FeatureSwitchModel extends FeatureBaseModel {
	public FeatureSwitchModel(long id, String name, int icon, int color) {
		super(FeatureBaseModel.SCENE_SWITCH, id, name, icon, color);
		
		addScenario(new ScenarioModel(-1, "On", -1, -1));
		addScenario(new ScenarioModel(-1, "Off", -1, -1));
	}

	@Override
	public ScenarioModel nextScenario() {
		if (mScenarios.size() == 2) {
			mOn = !mOn;
			int index = mOn ? 1 : 0;
			mScenario = mScenarios.get(index);
			return mScenario;
		}
		return null;
	}


}

package com.bluebox.james.model;


public class SceneSwitchModel extends FeatureModel {
	
	public SceneSwitchModel(String name, int icon) {
		super(FeatureModel.SCENE_UNKNOW, name, icon);
	}

	@Override
	public ActionModel nextAction() {
		if (mActions.size() == 2) {
			mOn = !mOn;
			int index = mOn ? 1 : 0;
			return mActions.get(index);
		}
		return null;
	}


}

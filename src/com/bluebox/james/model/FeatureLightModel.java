package com.bluebox.james.model;

public class FeatureLightModel extends FeatureBaseModel {
	private float 	mValue;
	private boolean	mIsVariometer;
	
	public FeatureLightModel(long id, String name, int icon) {
		super(FeatureBaseModel.SCENE_LIGHT, id, name, icon);
		
		mValue = 1f;
	}
	
	public void increase() {
		mValue += 0.2f;
	}

	public void decrease() {
		mValue -= 0.2f;
	}

}

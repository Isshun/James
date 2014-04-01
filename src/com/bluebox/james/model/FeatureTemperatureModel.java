package com.bluebox.james.model;

import android.util.Log;

public class FeatureTemperatureModel extends FeatureBaseModel {
	private float mCurrent;
	private float mExpected; 
	
	public FeatureTemperatureModel(long id, String name, int icon, int color) {
		super(FeatureBaseModel.SCENE_TEMPERATURE, id, name, icon, color);
		
		mExpected = 24f;
	}
	
	public void increase() {
		mExpected += 0.5f;
		Log.d("temp", "temp increase: " + mExpected);
	}

	public void decrease() {
		mExpected -= 0.5f;
		Log.d("temp", "temp decrease: " + mExpected);
	}
	
	public void setCurrent(float current) {
		mCurrent = current;
	}
	
	public void setExpected(float expected) {
		mExpected = expected;
	}
	
	public float getExpected() {
		return mExpected;
	}

	public float getCurent() {
		return mCurrent;
	}

	public float getDifference() {
		return mCurrent - mExpected;
	}
}

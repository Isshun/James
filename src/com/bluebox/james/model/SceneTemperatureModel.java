package com.bluebox.james.model;

import android.util.Log;

public class SceneTemperatureModel extends SceneModel {
	private float mCurrent;
	private float mExpected; 
	
	public SceneTemperatureModel(String name) {
		super(SceneModel.SCENE_TEMPERATURE, name);
		
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

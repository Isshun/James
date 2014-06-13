//package com.bluebox.james.model;
//
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//
//public class FeatureTemperatureModel extends FeatureBaseModel {
//	private ScenarioModel 	mScenarioIncrease;
//	private ScenarioModel 	mScenarioDecrease; 
//	private float 			mCurrent;
//	private float 			mExpected;
//	
//	public FeatureTemperatureModel(long id, String name, int icon, int color) {
//		super(FeatureBaseModel.SCENE_TEMPERATURE, id, name, icon, color);
//		
//		
//		mExpected = 24f;
//	}
//	
//	public void setCurrent(float current) {
//		mCurrent = current;
//	}
//	
//	public void setExpected(float expected) {
//		mExpected = expected;
//	}
//	
//	public float getExpected() {
//		return mExpected;
//	}
//
//	public float getCurent() {
//		return mCurrent;
//	}
//
//	public float getDifference() {
//		return mCurrent - mExpected;
//	}
//	
//	@Override
//	public boolean isToggleButtons() {
//		return false;
//	}
//
//	@Override
//	public String getFormattedValue() {
//		return mExpected + "°";
//	}
//}

package com.bluebox.james.model;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class FeatureTemperatureModel extends FeatureBaseModel {
	private ScenarioModel 	mScenarioIncrease;
	private ScenarioModel 	mScenarioDecrease; 
	private float 			mCurrent;
	private float 			mExpected;
	
	public FeatureTemperatureModel(long id, String name, int icon, int color) {
		super(FeatureBaseModel.SCENE_TEMPERATURE, id, name, icon, color);
		
		mScenarioDecrease = new ScenarioModel(-1, "decrease", -1, -1);
		mScenarioDecrease.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				decrease();
			}
		});
		addScenario(mScenarioDecrease);

		mScenarioIncrease = new ScenarioModel(-1, "increase", -1, -1);
		mScenarioIncrease.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				increase();
			}
		});
		addScenario(mScenarioIncrease);
		
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
	
	@Override
	public boolean isToggleButtons() {
		return false;
	}

	@Override
	public String getFormattedValue() {
		return mExpected + "°";
	}
}

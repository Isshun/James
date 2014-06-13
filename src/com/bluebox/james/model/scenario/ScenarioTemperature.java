package com.bluebox.james.model.scenario;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.ScenarioOptionModel;

public class ScenarioTemperature extends ScenarioBase {
	private ScenarioOptionModel mScenarioDecrease;
	private ScenarioOptionModel mScenarioIncrease;
	private float mExpected;

	public ScenarioTemperature(FeatureModel feature) {
		super(feature);
		
		mScenarioDecrease = new ScenarioOptionModel(this, null, 0, "Decrease", -1, -1);
		mScenarioDecrease.setFeature(feature);
		mScenarioDecrease.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				decrease();
			}
		});
		add(mScenarioDecrease);

		mScenarioIncrease = new ScenarioOptionModel(this, null, 1, "Increase", -1, -1);
		mScenarioIncrease.setFeature(feature);
		mScenarioIncrease.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				increase();
			}
		});
		add(mScenarioIncrease);
		
		mExpected = 24;
	}

	public void increase() {
		mExpected += 0.5f;
		Log.d("temp", "temp increase: " + mExpected);
	}

	public void decrease() {
		mExpected -= 0.5f;
		Log.d("temp", "temp decrease: " + mExpected);
	}

	@Override
	public String getFormatterValue() {
		return mExpected + "°";
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
	}

}
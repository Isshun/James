package org.smallbox.doomotic.model.scenario;

import org.smallbox.doomotic.model.DeviceBaseModel;
import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.model.ScenarioOptionModel;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class ScenarioTemperature extends ScenarioBase {
	private ScenarioOptionModel mScenarioDecrease;
	private ScenarioOptionModel mScenarioIncrease;
	private float 				mExpected;
	private DeviceBaseModel 	mDevice;

	public ScenarioTemperature(FeatureModel feature, DeviceBaseModel device) {
		super(feature);
		
		mDevice = device;
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

	public ScenarioTemperature asTemperature() {
		return (ScenarioTemperature)this;
	}

	@Override
	public DeviceBaseModel getDevice() {
		return mDevice;
	}
	

	@Override
	public int getType() {
		return FeatureModel.SCENE_TEMPERATURE;
	}


}
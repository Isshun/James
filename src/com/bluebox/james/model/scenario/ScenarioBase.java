package com.bluebox.james.model.scenario;

import java.util.ArrayList;
import java.util.List;

import android.view.View;

import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.ScenarioOptionModel;

public abstract class ScenarioBase {
	private long						mId;
	private List<ScenarioOptionModel> 	mOptions;
	private ScenarioOptionModel 		mCurrent;
	private FeatureModel 				mFeature;

	public ScenarioBase(FeatureModel feature) {
		mFeature = feature;
		mOptions = new ArrayList<ScenarioOptionModel>();
	}
	
	public ScenarioOptionModel next() {
		if (mOptions.size() == 0) {
			return null;
		}
		
		// Set next scenario
		int index = (mOptions.indexOf(mCurrent) + 1) % mOptions.size();
		setCurrentOption(mOptions.get(index));
		//mOn = index == 0;
		return mCurrent;
	}

	public void setCurrentOption(ScenarioOptionModel scenarioModel) {
		mCurrent = scenarioModel;
	}

	public void add(ScenarioOptionModel option) {
		if (mOptions.size() == 0) {
			mCurrent = option;
		}
		mOptions.add(option);
	}

	public ScenarioOptionModel getCurrent() {
		return mCurrent;
	}

	public List<ScenarioOptionModel> getOptions() {
		return mOptions;
	}

	public String getFormatterValue() {
		return null;
	}
	
	public void setId(long dbId) {
		mId = dbId;
	}

	public long getId() {
		return mId;
	}

	public FeatureModel getFeature() {
		return mFeature;
	}

	public abstract void commit();

	public ScenarioSwitch asSwitch() {
		return null;
	}
}

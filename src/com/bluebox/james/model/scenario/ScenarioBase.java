package com.bluebox.james.model.scenario;

import java.util.ArrayList;
import java.util.List;

import com.bluebox.james.DBHelper;
import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.ScenarioModel;

public abstract class ScenarioBase {
	private long					mId;
	private List<ScenarioModel> mOptions;
	private ScenarioModel 		mCurrent;
	private FeatureModel 		mFeature;

	public ScenarioBase(FeatureModel feature) {
		mFeature = feature;
		mOptions = new ArrayList<ScenarioModel>();
	}
	
	public ScenarioModel next() {
		if (mOptions.size() == 0) {
			return null;
		}
		
		// Set next scenario
		int index = (mOptions.indexOf(mCurrent) + 1) % mOptions.size();
		setCurrentOption(mOptions.get(index));
		//mOn = index == 0;
		return mCurrent;
	}

	public void setCurrentOption(ScenarioModel scenarioModel) {
		mCurrent = scenarioModel;
	}

	public void add(ScenarioModel option) {
		if (mOptions.size() == 0) {
			mCurrent = option;
		}
		mOptions.add(option);
	}

	public ScenarioModel getCurrent() {
		return mCurrent;
	}

	public List<ScenarioModel> getOptions() {
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
}

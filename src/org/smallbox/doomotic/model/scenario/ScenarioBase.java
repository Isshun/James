package org.smallbox.doomotic.model.scenario;

import java.util.ArrayList;
import java.util.List;

import org.smallbox.doomotic.model.DeviceBaseModel;
import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.model.ScenarioOptionModel;

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

	// TODO: remove
	public ScenarioSwitch asSwitch() {
		return null;
	}

	// TODO: remove
	public ScenarioCustom asCustom() {
		return null;
	}

	// TODO: remove
	public ScenarioTemperature asTemperature() {
		return null;
	}

	public abstract DeviceBaseModel getDevice();
	
	public abstract int getType();
}

package com.bluebox.james.model;

import java.util.List;

import com.bluebox.james.DBHelper;
import com.bluebox.james.model.scenario.ScenarioBase;
import com.bluebox.james.model.scenario.ScenarioCustom;
import com.bluebox.james.model.scenario.ScenarioSwitch;
import com.bluebox.james.model.scenario.ScenarioTemperature;

public class FeatureModel extends DBModel {
	
	public final static int	SCENE_UNKNOW = 0;
	public final static int	SCENE_SCENARIO = 1;
	public final static int	SCENE_TEMPERATURE = 2;
	public final static int SCENE_ALARM = 3;
	public final static int SCENE_SWITCH = 4;
	public final static int	SCENE_COUNT = 5;
	
	public String 					mName;
	public List<DeviceBaseModel>	mEquipments;
	protected boolean 				mOn;
	private int 					mType;
	private int 					mIcon;
	private int 					mColor;
	private ScenarioBase			mScenario;
	private ScenarioBase			mCustomScenario;
	private DeviceBaseModel mDevice;
	
	public FeatureModel(int type, long id, String name, int icon, int color) {
		mDbId = id;
		mType = type;
		mName = name;
		mIcon = icon;
		mColor = color;
		
		// TODO
		switch (type) {
		case SCENE_SCENARIO:
			mScenario = new ScenarioCustom(this);
			mCustomScenario = mScenario;
			break;
		case SCENE_SWITCH:
			mScenario = new ScenarioSwitch(this, null);
			mCustomScenario = new ScenarioCustom(this);
			break;
		case SCENE_TEMPERATURE:
			mScenario = new ScenarioTemperature(this);
			mCustomScenario = new ScenarioCustom(this);
			break;
		}
	}
	
	public FeatureModel(String name) {
		mType = SCENE_UNKNOW;
		mName = name;
	}
	
	public int getType() {
		return mType;
	}

	public String getTypeName() {
		switch (mType) {
		case SCENE_TEMPERATURE: return "Temperature";
		case SCENE_SCENARIO: return "Light";
		case SCENE_ALARM: return "Alarm";
		case SCENE_SWITCH: return "Switch";
		}
		return null;
	}

	public String getName() {
		return mName;
	}
	
	public List<ScenarioOptionModel> getOptions() {
		return mScenario.getOptions();
	}
	
	public List<DeviceBaseModel> getEquipments() {
		return mEquipments;
	}

	public ScenarioOptionModel nextScenario() {
		return mScenario.next();
	}
	
	public boolean isOn() {
		return mOn;
	}

	public void addScenario(ScenarioOptionModel scenario) {
		mScenario.add(scenario);
		scenario.setFeature(this);
	}

	public ScenarioOptionModel getScenario() {
		return mScenario.getCurrent();
	}

	public int getIcon() {
		return mIcon;
	}

	public int getColor() {
		return mColor;
	}

	public FeatureModel setType(int type) {
		if (mType == type) {
			return this;
		}
		
		switch (type) {
		case FeatureModel.SCENE_SWITCH:
			mScenario = new ScenarioSwitch(this, null);
			break;
		case FeatureModel.SCENE_SCENARIO:
			mScenario = mCustomScenario;
			break;
		case FeatureModel.SCENE_TEMPERATURE:
			mScenario = new ScenarioTemperature(this);
			break;
		}
			
		mType = type;
		return this;
	}

	public void commit() {
		if (mScenario != null) {
			mScenario.commit();
		}
		DBHelper.getInstance().updateFeature(this);
	}

	public void setColor(int color) {
		mColor = color;
	}

	public boolean isType(int type) {
		return mType == type;
	}
	
	public boolean isToggleButtons() {
		return mType == SCENE_TEMPERATURE ? false : true;
	}

	public String getFormattedValue() {
		return mScenario.getFormatterValue();
	}

	public void addCustomScenario(ScenarioOptionModel scenario) {
		mCustomScenario.add(scenario);
		scenario.setScenario(mCustomScenario);
		scenario.setFeature(this);
	}

	public void setScenario(ScenarioOptionModel scenario) {
		mScenario.setCurrentOption(scenario);
	}

	public void setScenario(ScenarioBase scenario) {
		mScenario = scenario;
	}
}

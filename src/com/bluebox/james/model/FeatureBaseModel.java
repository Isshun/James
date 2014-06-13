package com.bluebox.james.model;

import java.util.ArrayList;
import java.util.List;

import com.bluebox.james.DBHelper;

public class FeatureBaseModel extends DBModel {
	
	public final static int	SCENE_UNKNOW = 0;
	public final static int	SCENE_SCENARIO = 1;
	public final static int	SCENE_TEMPERATURE = 2;
	public final static int SCENE_ALARM = 3;
	public final static int SCENE_SWITCH = 4;
	public final static int	SCENE_COUNT = 5;
	
	public String 					mName;
	public List<DeviceBaseModel>	mEquipments;
	protected List<ScenarioModel> 	mScenarios;
	protected ScenarioModel 		mScenario;
	protected boolean 				mOn;
	private int 					mType;
	private int 					mIcon;
	private int 					mColor;
	private List<ScenarioModel> 	mCustomScenarios;
	
	public FeatureBaseModel(int type, long id, String name, int icon, int color) {
		mDbId = id;
		mType = type;
		mName = name;
		mIcon = icon;
		mColor = color;
		mScenarios = new ArrayList<ScenarioModel>();
		mCustomScenarios = new ArrayList<ScenarioModel>();
	}
	
	public FeatureBaseModel(String name) {
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
	
	public List<ScenarioModel> getScenarios() {
		return mScenarios;
	}
	
	public List<DeviceBaseModel> getEquipments() {
		return mEquipments;
	}

	public ScenarioModel nextScenario() {
		if (mScenarios.size() == 0) {
			return null;
		}
		
		// Set next scenario
		int index = (mScenarios.indexOf(mScenario) + 1) % mScenarios.size();
		setScenario(mScenarios.get(index));
		mOn = index == 0;
		return mScenario;
	}
	
	public boolean isOn() {
		return mOn;
	}

	public void addScenario(ScenarioModel scenario) {
		if (mScenarios.size() == 0) {
			mScenario = scenario;
		}
		mScenarios.add(scenario);
		scenario.setFeature(this);
	}

	public ScenarioModel getScenario() {
		return mScenario;
	}

	public int getIcon() {
		return mIcon;
	}

	public int getColor() {
		return mColor;
	}

	public FeatureBaseModel setType(int type) {
		if (mType == type) {
			return this;
		}
		
		mScenarios.clear();
		switch (type) {
		case FeatureBaseModel.SCENE_SWITCH:
			addScenario(new ScenarioModel(-1, "On", -1, -1));
			addScenario(new ScenarioModel(-1, "Off", -1, -1));
			break;
		case FeatureBaseModel.SCENE_SCENARIO:
			for (ScenarioModel scenario: mCustomScenarios) {
				addScenario(scenario);
			}
			break;
		case FeatureBaseModel.SCENE_TEMPERATURE:
			addScenario(new ScenarioModel(-1, "Decrease", -1, -1));
			addScenario(new ScenarioModel(-1, "Increase", -1, -1));
			break;
		}
			
		mType = type;
		return this;
	}

	public void commit() {
		DBHelper.getInstance().updateFeature(this);
	}

	public void setColor(int color) {
		mColor = color;
	}

	public void setScenario(ScenarioModel scenario) {
		mScenario = scenario;
	}

	public boolean isType(int type) {
		return mType == type;
	}
	
	public boolean isToggleButtons() {
		return true;
	}

	public String getFormattedValue() {
		return null;
	}

	public void addCustomScenario(ScenarioModel scenario) {
		mCustomScenarios.add(scenario);
		if (mType == SCENE_SCENARIO) {
			addScenario(scenario);
		}
	}
}

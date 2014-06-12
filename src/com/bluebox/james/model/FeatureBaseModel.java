package com.bluebox.james.model;

import java.util.ArrayList;
import java.util.List;

import com.bluebox.james.DBHelper;

public class FeatureBaseModel extends DBModel {
	
	public final static int	SCENE_UNKNOW = 0;
	public final static int	SCENE_LIGHT = 1;
	public final static int	SCENE_TEMPERATURE = 2;
	public final static int	SCENE_COUNT = 3;
	public final static int SCENE_ALARM = 4;
	public final static int SCENE_SWITCH = 5;
	
	public String 					mName;
	public List<DeviceBaseModel>	mEquipments;
	protected List<ScenarioModel> 	mScenarios;
	private ScenarioModel 			mScenario;
	protected boolean 				mOn;
	private int 					mType;
	private int 					mIcon;
	private int 					mColor;
	
	public FeatureBaseModel(int type, long id, String name, int icon, int color) {
		mDbId = id;
		mType = type;
		mName = name;
		mIcon = icon;
		mColor = color;
		mScenarios = new ArrayList<ScenarioModel>();
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
		case SCENE_LIGHT: return "Light";
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
		mScenario = mScenarios.get(index);
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

	public void setType(int type) {
		mType = type;
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
}

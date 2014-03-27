package com.bluebox.james.model;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.bluebox.james.DBHelper;

public class FeatureBaseModel extends DBModel {
	
	public final static int	SCENE_UNKNOW = 0;
	public final static int	SCENE_LIGHT = 1;
	public final static int	SCENE_TEMPERATURE = 2;
	public final static int	SCENE_COUNT = 3;
	public final static int SCENE_ALARM = 4;
	public final static int SCENE_SWITCH = 5;
	
	private static long				sCount;
	public String 					mName;
	public List<DeviceBaseModel>	mEquipments;
	protected List<ScenarioModel> 	mScenario;
	protected boolean 				mOn;
	private int 					mType;
	private int 					mCurrentSwitch;
	private int 					mNbSwitch;
	private ScenarioModel 			mCurrentScenario;
	private int 					mIcon;
	
	public FeatureBaseModel(int type, long id, String name, int icon) {
		mDbId = id;
		mType = type;
		mName = name;
		mIcon = icon;
		mScenario = new ArrayList<ScenarioModel>();
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
		return mScenario;
	}
	
	public List<DeviceBaseModel> getEquipments() {
		return mEquipments;
	}

	public ScenarioModel nextScenario() {
		if (mNbSwitch > 0) {
			mCurrentSwitch = (mCurrentSwitch + 1) % mNbSwitch;
			mOn = mCurrentSwitch == 0;
			mCurrentScenario = mScenario.get(mCurrentSwitch);
			return mCurrentScenario;
		}
		return null;
	}
	
	public boolean isOn() {
		return mOn;
	}

	public void addScenario(ScenarioModel scenario) {
		if (mScenario.size() == 0) {
			mCurrentScenario = scenario;
		}
		mScenario.add(scenario);
		mNbSwitch++;
	}

	public ScenarioModel getScenario() {
		return mCurrentScenario;
	}

	public int getIcon() {
		return mIcon;
	}

	public int getColor() {
		return Color.CYAN;
	}
}

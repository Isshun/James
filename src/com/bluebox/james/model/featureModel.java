package com.bluebox.james.model;

import java.util.ArrayList;
import java.util.List;

public class FeatureModel {
	
	public final static int	SCENE_UNKNOW = 0;
	public final static int	SCENE_LIGHT = 1;
	public final static int	SCENE_TEMPERATURE = 2;
	public final static int	SCENE_COUNT = 3;
	public final static int SCENE_ALARM = 4;
	public final static int SCENE_SWITCH = 5;
	
	private static long			sCount;
	public String 				mName;
	public List<DeviceModel>	mEquipments;
	protected List<ScenarioModel> mActions;
	protected boolean 			mOn;
	private int 				mType;
	private int 				mCurrentSwitch;
	private int 				mNbSwitch;
	private ScenarioModel 		mCurrentAction;
	private int 				mIcon;
	private long 				mId;
	
	public FeatureModel(int type, String name, int icon) {
		mId = sCount++;
		mType = type;
		mName = name;
		mIcon = icon;
		mActions = new ArrayList<ScenarioModel>();
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
		case SCENE_LIGHT: return "Light";
		case SCENE_ALARM: return "Alarm";
		case SCENE_SWITCH: return "Switch";
		}
		return null;
	}

	public String getName() {
		return mName;
	}
	
	public List<ScenarioModel> getActions() {
		return mActions;
	}
	
	public List<DeviceModel> getEquipments() {
		return mEquipments;
	}

	public long getId() {
		return mId;
	}

	public ScenarioModel nextAction() {
		if (mNbSwitch > 0) {
			mCurrentSwitch = (mCurrentSwitch + 1) % mNbSwitch;
			mOn = mCurrentSwitch == 0;
			mCurrentAction = mActions.get(mCurrentSwitch);
			return mCurrentAction;
		}
		return null;
	}
	
	public boolean isOn() {
		return mOn;
	}

	public void addAction(ScenarioModel action) {
		if (mActions.size() == 0) {
			mCurrentAction = action;
		}
		mActions.add(action);
		mNbSwitch++;
	}

	public ScenarioModel getAction() {
		return mCurrentAction;
	}

	public int getIcon() {
		return mIcon;
	}
}

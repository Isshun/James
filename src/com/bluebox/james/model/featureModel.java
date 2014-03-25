package com.bluebox.james.model;

import java.util.ArrayList;
import java.util.List;

public class FeatureModel {
	
	public final static int	SCENE_UNKNOW = 0;
	public final static int	SCENE_LIGHT = 1;
	public final static int	SCENE_TEMPERATURE = 2;
	public final static int	SCENE_COUNT = 3;
	
	private static long			sCount;
	public String 				mName;
	public List<EquipmentModel>	mEquipments;
	protected List<ActionModel> mActions;
	protected boolean 			mOn;
	private int 				mType;
	private int 				mCurrentSwitch;
	private int 				mNbSwitch;
	private ActionModel 		mCurrentAction;
	private int 				mIcon;
	private long 				mId;
	
	public FeatureModel(int type, String name, int icon) {
		mId = sCount++;
		mType = type;
		mName = name;
		mIcon = icon;
		mActions = new ArrayList<ActionModel>();
	}
	
	public FeatureModel(String name) {
		mType = SCENE_UNKNOW;
		mName = name;
	}
	
	public int getType() {
		return mType;
	}

	public String getName() {
		return mName;
	}
	
	public List<ActionModel> getActions() {
		return mActions;
	}
	
	public List<EquipmentModel> getEquipments() {
		return mEquipments;
	}

	public long getId() {
		return 0;
	}

	public ActionModel nextAction() {
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

	public void addAction(ActionModel action) {
		if (mActions.size() == 0) {
			mCurrentAction = action;
		}
		mActions.add(action);
		mNbSwitch++;
	}

	public ActionModel getAction() {
		return mCurrentAction;
	}

	public int getIcon() {
		return mIcon;
	}
}

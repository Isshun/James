package com.bluebox.james.model;

import java.util.ArrayList;
import java.util.List;

import com.bluebox.james.SceneAction;

public class SceneModel {
	
	public final static int	SCENE_UNKNOW = 0;
	public final static int	SCENE_LIGHT = 1;
	public final static int	SCENE_TEMPERATURE = 2;
	public final static int	SCENE_COUNT = 3;
	
	public String mName;
	public List<EquipmentModel>	mEquipments;
	private List<String> mUrls;
	private boolean mOn;
	private int mType;
	private int mCurrentSwitch;
	private int mNbSwitch;
	
	public SceneModel(int type, String name) {
		mType = type;
		mName = name;
		mUrls = new ArrayList<String>();
	}
	
	public SceneModel(String name) {
		mType = SCENE_UNKNOW;
		mName = name;
	}
	
	public int getType() {
		return mType;
	}

	public String getName() {
		return mName;
	}
	
	public List<EquipmentModel> getEquipments() {
		return mEquipments;
	}

	public long getId() {
		return 0;
	}

	public SceneAction nextAction() {
		if (mNbSwitch > 0) {
			mCurrentSwitch = (mCurrentSwitch + 1) % mNbSwitch;
			mOn = mCurrentSwitch == 0;
			SceneAction action = new SceneAction(mCurrentSwitch, mUrls.get(mCurrentSwitch));
			return action;
		}
		return null;
	}
	
	public boolean isOn() {
		return mOn;
	}

	public void addUrl(String url) {
		mUrls.add(url);
		mNbSwitch++;
	}
}

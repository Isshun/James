package org.smallbox.doomotic.model;

import java.util.List;

import org.smallbox.doomotic.JSONUtils;
import org.smallbox.doomotic.model.scenario.ScenarioBase;
import org.smallbox.doomotic.model.scenario.ScenarioCustom;

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
	
	public FeatureModel(int type, int  id, String name, int icon, int color) {
		super(id);
		mType = type;
		mName = name;
		mIcon = icon;
		mColor = color;
		
		mScenario = new ScenarioCustom(this);
	}
	
	public FeatureModel(String name) {
		super();
		mType = SCENE_UNKNOW;
		mName = name;
		mCustomScenario = mScenario = new ScenarioCustom(this);
	}
	
	public FeatureModel(int id) {
		super(id);
		mCustomScenario = mScenario = new ScenarioCustom(this);
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
		return mScenario != null ? mScenario.getOptions() : null;
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

	public void addScenarioOption(ScenarioOptionModel option) {
		mScenario.add(option);
		option.setFeature(this);
	}

	public ScenarioOptionModel getCurrentOption() {
		return mScenario != null ? mScenario.getCurrent() : null;
	}

	public int getIcon() {
		return mIcon;
	}

	public int getColor() {
		return mColor;
	}

	public FeatureModel setType(int type) {
//		if (mType == type) {
//			return this;
//		}
//		
//		DeviceBaseModel device = mScenario != null ? mScenario.getDevice() : null;
//		
//		switch (type) {
//		case FeatureModel.SCENE_SWITCH:
//			mScenario = new ScenarioSwitch(this, device);
//			break;
//		case FeatureModel.SCENE_SCENARIO:
//			mScenario = mCustomScenario;
//			break;
//		case FeatureModel.SCENE_TEMPERATURE:
//			mScenario = new ScenarioTemperature(this, device);
//			break;
//		}
//			
		mType = type;
		return this;
	}

	public void commit() {
		if (mScenario != null) {
			mScenario.commit();
		}
//		DBHelper.getInstance().updateFeature(this);
		JSONUtils.saveRooms();
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
		scenario.setFeature(this);
	}

	public void setScenario(ScenarioOptionModel option) {
		if (mScenario == null) {
			mScenario = new ScenarioCustom(this);
		}
		mScenario.setCurrentOption(option);
	}

	public void setScenario(ScenarioBase scenario) {
		mScenario = scenario;
		mCustomScenario = scenario;
	}

	public void setIcon(int icon) {
		mIcon = icon;
	}

	public ScenarioBase getCurrentScenario() {
		return mScenario;
	}

	public void setName(String name) {
		mName = name;
	}

	public ScenarioBase getScenario() {
		return mScenario;
	}

	public void addScenario(ScenarioBase scenario) {
		
	}
}

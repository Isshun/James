package com.bluebox.james.datasource;

import com.bluebox.james.model.DeviceBaseModel;
import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.ScenarioOptionModel;
import com.bluebox.james.model.scenario.ScenarioBase;

public interface DataSourceListener {
	void complete();
	boolean isComplete();
	void addDevice(DeviceBaseModel device);
	void addRoom(RoomModel room);
	void addScenario(ScenarioBase scenario);
	void addFeature(FeatureModel feature);
}

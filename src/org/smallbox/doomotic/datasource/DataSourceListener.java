package org.smallbox.doomotic.datasource;

import org.smallbox.doomotic.model.DeviceBaseModel;
import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.model.RoomModel;
import org.smallbox.doomotic.model.scenario.ScenarioBase;

public interface DataSourceListener {
	void complete();
	boolean isComplete();
	void addDevice(DeviceBaseModel device);
	void addRoom(RoomModel room);
	void addScenario(ScenarioBase scenario);
	void addFeature(FeatureModel feature);
}

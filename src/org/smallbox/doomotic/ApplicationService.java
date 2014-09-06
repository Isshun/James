package org.smallbox.doomotic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.smallbox.doomotic.datasource.DataSourceListener;
import org.smallbox.doomotic.model.DeviceBaseModel;
import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.model.RoomModel;
import org.smallbox.doomotic.model.scenario.ScenarioBase;

public class ApplicationService {
	public static interface DeviceFilter {
		boolean isMatching(DeviceBaseModel device);
	}
	
	private static ApplicationService sSelf;
	
	private List<RoomModel>				mRooms;
	private List<DeviceBaseModel>		mDevices;
	private List<FeatureModel>			mFeatures;
	private List<ScenarioBase>			mScenarios;
	
	public ApplicationService() {
		mRooms = new ArrayList<RoomModel>();
		mDevices = new ArrayList<DeviceBaseModel>();
		mFeatures = new ArrayList<FeatureModel>();
		mScenarios = new ArrayList<ScenarioBase>();
	}
	
	public static ApplicationService getInstance() {
		if (sSelf == null) {
			sSelf = new ApplicationService();
		}
		return sSelf;
	}

	public void addDataSource(Class<?> className) {
		DataSourceListener listener = new DataSourceListener() {
			private boolean mDataSourceLoaded;

			@Override
			public void complete() {
				mDataSourceLoaded = true;
			}

			@Override
			public boolean isComplete() {
				return mDataSourceLoaded;
			}

			@Override
			public void addDevice(DeviceBaseModel device) {
				mDevices.add(device);
			}

			@Override
			public void addRoom(RoomModel room) {
				mRooms.add(room);
			}

			@Override
			public void addScenario(ScenarioBase scenario) {
				mScenarios.add(scenario);
			}

			@Override
			public void addFeature(FeatureModel feature) {
				mFeatures.add(feature);
			}
		};

		try {
			Constructor<?> constructor = className.getConstructor(DataSourceListener.class);
			constructor.newInstance(new Object[] { listener });
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public List<RoomModel> getRooms() {
		return mRooms;
	}

	public List<DeviceBaseModel> getDevices() {
		return mDevices;
	}
	
	public List<DeviceBaseModel> getDevices(DeviceFilter filter) {
		List<DeviceBaseModel> devices = new ArrayList<DeviceBaseModel>();
		
		for (DeviceBaseModel device: mDevices) {
			if (filter.isMatching(device)) {
				devices.add(device);
			}
		}
		
		return devices;
	}

	public List<FeatureModel> getFeatures() {
		return mFeatures;
	}

	public List<ScenarioBase> getScenarios() {
		return mScenarios;
	}

	public DeviceBaseModel getDevice(int deviceId) {
		for (DeviceBaseModel device: mDevices) {
			if (device.getId() == deviceId) {
				return device;
			}
		}
		return null;
	}

	public ScenarioBase getScenario(int scenarioId) {
		for (ScenarioBase scenario: mScenarios) {
			if (scenario.getId() == scenarioId) {
				return scenario;
			}
		}
		return null;
	}

	public FeatureModel getFeature(int featureId) {
		for (FeatureModel feature: mFeatures) {
			if (feature.getId() == featureId) {
				return feature;
			}
		}
		return null;
	}

	public void addFeature(FeatureModel feature) {
		mFeatures.add(feature);
	}

	public void addRoom(RoomModel room) {
		mRooms.add(room);
	}

	public void addScenario(ScenarioBase scenario) {
		mScenarios.add(scenario);
	}

	public void addDevice(DeviceBaseModel device) {
		mDevices.add(device);
	}

}

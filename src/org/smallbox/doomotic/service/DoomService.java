package org.smallbox.doomotic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.smallbox.doomotic.Application;
import org.smallbox.doomotic.ApplicationService;
import org.smallbox.doomotic.JSONUtils;
import org.smallbox.doomotic.model.DeviceBaseModel;
import org.smallbox.doomotic.model.DeviceProbeModel;
import org.smallbox.doomotic.model.DeviceSwitchModel;
import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.model.FloorModel;
import org.smallbox.doomotic.model.RoomModel;
import org.smallbox.doomotic.model.ScenarioOptionModel;
import org.smallbox.doomotic.model.scenario.ScenarioBase;
import org.smallbox.doomotic.model.scenario.ScenarioSwitch;

import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

public class DoomService {
	private List<FloorModel>	mFloors;

	private static DoomService 			sRoomService;
	private Map<Integer, RoomModel> 		mRooms;
//	private Map<Long, ScenarioBase> 	mScenarios;
	private Map<Integer, FeatureModel> mFeatures;
	private Map<Integer, DeviceProbeModel> mProbes;
	private Map<Integer, DeviceSwitchModel> mSwitchs;

	private DoomService() {
		mFloors = new ArrayList<FloorModel>();
		mFloors.add(new FloorModel(0, "Basement"));
		mFloors.add(new FloorModel(1, "Ground floor"));
		mFloors.add(new FloorModel(2, "2nd floor"));

		mRooms = new HashMap<Integer, RoomModel>();
		mFeatures = new HashMap<Integer, FeatureModel>();
//		mScenarios = new HashMap<Long, ScenarioOptionModel>();
		mProbes = new HashMap<Integer, DeviceProbeModel>();
		mSwitchs = new HashMap<Integer, DeviceSwitchModel>();
	}

	public static DoomService getInstance() {
		if (sRoomService == null) {
			sRoomService = new DoomService();
		}
		return sRoomService;
	}

	public int getCount() {
		return mRooms.size();
	}

//	public List<ScenarioOptionModel> getAllActions() {
//		return new ArrayList<ScenarioOptionModel>(mScenarios.values());
//	}

	//	public List<RoomModel> getRoomList() {
	//		return new ArrayList<RoomModel>(mRooms.values());
	//	}

	public static void execute(ScenarioOptionModel scenario) {
		final AQuery aquery = new AQuery(Application.getContext());

		scenario.execute(new OnExecuteListener() {
			@Override
			public void execute(String url, int value) {
				if (url != null) {
					// Execute url
					Log.d("RoomService", "RoomService: execute " + url);

					aquery.ajax(url, String.class, new AjaxCallback<String>() {
						@Override
						public void callback(String url, String html, AjaxStatus status) {
							Log.d("CALLBACK", "callback: " + status.getCode());
						}
					});
				} else {
					Log.e("RoomService", "RoomService: no url fo current value");
				}
			}
		});
	}

	public void addRoom(RoomModel room) {
		mRooms.put(room.getId(), room);

		for (FeatureModel scene: room.getFeatures()) {
			mFeatures.put(scene.getId(), scene);

//			for (ScenarioOptionModel action: scene.getOptions()) {
//				mScenarios.put(action.getId(), action);
//			}
		}
	}

	public FeatureModel getFeature(int sceneId) {
		return mFeatures.get(sceneId);
	}

//	public ScenarioOptionModel getScenario(long scenarioId) {
//		return mScenarios.get(scenarioId);
//	}

//	public void addScenarioToFeature(FeatureModel feature, ScenarioOptionModel scenario) {
//		mScenarios.put(scenario.getId(), scenario);
//		feature.addCustomScenario(scenario);
//
//		JSONUtils.saveRooms();
//		
//		//DBHelper.getInstance().addScenarioToFeature(feature, scenario);
//	}

	public RoomModel getRoom(int id) {
		return mRooms.get(id);
	}

	public List<DeviceProbeModel> getProbeList() {
		return new ArrayList<DeviceProbeModel>(mProbes.values());
	}

	public List<DeviceSwitchModel> getSwitchList() {
		return new ArrayList<DeviceSwitchModel>(mSwitchs.values());
	}

	public void addProbe(DeviceProbeModel device) {
		mProbes.put(device.getId(), device);

		//		DBHelper.getInstance().createProbe(device);
	}

	public void addSwitch(DeviceSwitchModel device) {
		mSwitchs.put(device.getId(), device);

		//DBHelper.getInstance().createSwitch(device);
		
		ApplicationService.getInstance().addDevice(device);
	}

	public void addFeatureToRoom(RoomModel room, FeatureModel feature) {
//		DBHelper.getInstance().addFeatureToRoom(room, feature);

		room.addFeature(feature);
		
		JSONUtils.saveRooms();
	}

	public void addDeviceToScenario(ScenarioOptionModel scenario, DeviceBaseModel device, int value) {
//		DBHelper.getInstance().addDeviceToScenario(scenario, device, value);

		scenario.addDevice(device, value);
		
		JSONUtils.saveRooms();
	}

	public void init() {
//		DBHelper.getInstance().open();
//		//		if (DBHelper.getInstance().isExists() == false) {
//		//			DBHelper.getInstance().onCreate(DBHelper.getInstance().getWritableDatabase());
//		//		}
//		//		DBHelper.getInstance().onCreate(DBHelper.getInstance().getWritableDatabase());
//		//		DBHelper.getInstance().reset(DBHelper.getInstance().getWritableDatabase());
//
//		try {
//			DBHelper.getInstance().load();
//		} catch (Exception e) {
//			e.printStackTrace();
//			DBHelper.getInstance().reset(DBHelper.getInstance().getWritableDatabase());
//			DBHelper.getInstance().load();
//		}
//		mSwitchs = DBHelper.getInstance().getSwitchs();
//		//		mProbes = DBHelper.getInstance().getProbes();
//		mRooms = DBHelper.getInstance().getRooms();
//		mScenarios = DBHelper.getInstance().getScenarios();
//		mFeatures = DBHelper.getInstance().getFeatures();
		
		ApplicationService service = ApplicationService.getInstance();
		
		mSwitchs = new HashMap<Integer, DeviceSwitchModel>();
		for (DeviceBaseModel device: service.getDevices()) {
			mSwitchs.put(device.getId(), device.getDeviceSwitch());
		}
		
		mRooms = new HashMap<Integer, RoomModel>();
		for (RoomModel room: service.getRooms()) {
			mRooms.put(room.getId(), room);
		}

//		mScenarios = new HashMap<Long, ScenarioBase>();
//		for (ScenarioBase scenario: service.getScenarios()) {
//			mScenarios.put(scenario.getId(), scenario);
//		}

		mFeatures = new HashMap<Integer, FeatureModel>();
		for (FeatureModel feature: service.getFeatures()) {
			mFeatures.put(feature.getId(), feature);
		}
	}

	public RoomModel createRoom(String name, int icon) {
		RoomModel room = new RoomModel(-1, name, icon);

		ApplicationService.getInstance().addRoom(room);
		
		//DBHelper.getInstance().createRoom(room);

		return room;
	}

	public ScenarioOptionModel createScenarioOption(FeatureModel feature, String name, int icon) {
		ScenarioOptionModel option = new ScenarioOptionModel();
		option.setLabel(name);
		
		feature.addScenarioOption(option);
	
		return option;
		
//		ScenarioOptionModel scenario = new ScenarioOptionModel(null, null, -1, name, icon, Color.RED);
//
////		DBHelper.getInstance().createScenario(scenario);
//
//		ApplicationService.getInstance().addScenario(scenario);
//		return scenario;
//		throw new RuntimeException("not implemented");
	}

	public ScenarioBase createScenarioSwitch(FeatureModel feature, DeviceBaseModel device) {
		ScenarioSwitch scenarioSwitch = new ScenarioSwitch(feature, device);

		//DBHelper.getInstance().createScenarioSwitch(scenarioSwitch);

		return scenarioSwitch;
	}

	public FeatureModel createFeature(int type, String name, int icon, int color) {
		FeatureModel feature = null;

		feature = new FeatureModel(type, -1, name, icon, color);
		ApplicationService.getInstance().addFeature(feature);
		//DBHelper.getInstance().createFeature(feature);
		JSONUtils.saveRooms();

		return feature;
	}

	public DeviceBaseModel createDevice(String name, int deviceId) {
		DeviceSwitchModel device = new DeviceSwitchModel(-1, name, deviceId);

		//DBHelper.getInstance().createSwitch(device);
		
		ApplicationService.getInstance().addDevice(device);

		return device;
	}

	// TODO: dirty
	public List<RoomModel> getRooms() {
		return new ArrayList<RoomModel>(mRooms.values());
	}

	public List<DeviceBaseModel> getDevices() {
		return new ArrayList<DeviceBaseModel>(mSwitchs.values());
	}

	public List<FloorModel> getFloors() {
		List<FloorModel> floors = new ArrayList<FloorModel>();
		for (FloorModel floor: mFloors) {
			floors.add(floor);
		}
		return floors;
	}

	public FloorModel getFloor(int id) {
		for (FloorModel floor: mFloors) {
			if (floor.getId() == id) {
				return floor;
			}
		}
		return null;
	}

	public void saveScenario(ScenarioOptionModel mScenario) {
		JSONUtils.saveRooms();
	}

}

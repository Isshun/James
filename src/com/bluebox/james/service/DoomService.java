package com.bluebox.james.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Color;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.bluebox.james.Application;
import com.bluebox.james.DBHelper;
import com.bluebox.james.model.DeviceBaseModel;
import com.bluebox.james.model.DeviceProbeModel;
import com.bluebox.james.model.DeviceSwitchModel;
import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.ScenarioModel;
import com.bluebox.james.model.scenario.ScenarioBase;
import com.bluebox.james.model.scenario.ScenarioSwitch;

public class DoomService {

	private static DoomService 			sRoomService;
	private Map<Long, RoomModel> 		mRooms;
	private Map<Long, ScenarioModel> 	mScenarios;
	private Map<Long, FeatureModel> mFeatures;
	private Map<Long, DeviceProbeModel> mProbes;
	private Map<Long,DeviceSwitchModel> mSwitchs;

	private DoomService() {
		mRooms = new HashMap<Long, RoomModel>();
		mFeatures = new HashMap<Long, FeatureModel>();
		mScenarios = new HashMap<Long, ScenarioModel>();
		mProbes = new HashMap<Long, DeviceProbeModel>();
		mSwitchs = new HashMap<Long, DeviceSwitchModel>();
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

	public List<ScenarioModel> getAllActions() {
		return new ArrayList<ScenarioModel>(mScenarios.values());
	}

//	public List<RoomModel> getRoomList() {
//		return new ArrayList<RoomModel>(mRooms.values());
//	}

	public static void execute(ScenarioModel scenario) {
		AQuery aquery = new AQuery(Application.getContext());
		
		Map<DeviceBaseModel, Integer> devices = scenario.getDevices();
		for (DeviceBaseModel device: devices.keySet()) {
			int value = devices.get(device);
			String url = device.getUrl(value);
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
	}

	public void addRoom(RoomModel room) {
		mRooms.put(room.getId(), room);
		
		for (FeatureModel scene: room.getFeatures()) {
			mFeatures.put(scene.getId(), scene);

			for (ScenarioModel action: scene.getScenarios()) {
				mScenarios.put(action.getId(), action);
			}
		}
	}

	public FeatureModel getFeature(long sceneId) {
		return mFeatures.get(sceneId);
	}

	public ScenarioModel getScenario(long scenarioId) {
		return mScenarios.get(scenarioId);
	}

	public void addScenarioToFeature(FeatureModel feature, ScenarioModel scenario) {
		mScenarios.put(scenario.getId(), scenario);
		feature.addCustomScenario(scenario);
		
		DBHelper.getInstance().addScenarioToFeature(feature, scenario);
	}

	public RoomModel getRoom(long id) {
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

		DBHelper.getInstance().createSwitch(device);
	}

	public void addFeatureToRoom(RoomModel room, FeatureModel feature) {
		DBHelper.getInstance().addFeatureToRoom(room, feature);

		room.addFeature(feature);
	}
	
	public void addDeviceToScenario(ScenarioModel scenario, DeviceBaseModel device, int value) {
		DBHelper.getInstance().addDeviceToScenario(scenario, device, value);

		scenario.addDevice(device, value);
	}

	public void init() {
		DBHelper.getInstance().open();
//		if (DBHelper.getInstance().isExists() == false) {
//			DBHelper.getInstance().onCreate(DBHelper.getInstance().getWritableDatabase());
//		}
//		DBHelper.getInstance().onCreate(DBHelper.getInstance().getWritableDatabase());
//		DBHelper.getInstance().reset(DBHelper.getInstance().getWritableDatabase());

		DBHelper.getInstance().load();
		mSwitchs = DBHelper.getInstance().getSwitchs();
//		mProbes = DBHelper.getInstance().getProbes();
		mRooms = DBHelper.getInstance().getRooms();
		mScenarios = DBHelper.getInstance().getScenarios();
		mFeatures = DBHelper.getInstance().getFeatures();
	}

	public RoomModel createRoom(String name, int icon) {
		RoomModel room = new RoomModel(-1, name, icon);
		
		DBHelper.getInstance().createRoom(room);
		
		return room;
	}

	public ScenarioModel createScenario(String name, int icon) {
		ScenarioModel scenario = new ScenarioModel(null, -1, name, icon, Color.RED);
		
		DBHelper.getInstance().createScenario(scenario);
		
		return scenario;
	}

	public ScenarioBase createScenarioSwitch(FeatureModel feature, DeviceBaseModel device) {
		ScenarioSwitch scenarioSwitch = new ScenarioSwitch(feature, device);
		
		DBHelper.getInstance().createScenarioSwitch(scenarioSwitch);
		
		return scenarioSwitch;
	}

	public FeatureModel createFeature(int type, String name, int icon, int color) {
		FeatureModel feature = null;

		feature = new FeatureModel(type, -1, name, icon, color);
		DBHelper.getInstance().createFeature(feature);
		
		return feature;
	}

	public DeviceBaseModel createDevice(String name, int deviceId) {
		DeviceSwitchModel device = new DeviceSwitchModel(-1, name, deviceId);

		DBHelper.getInstance().createSwitch(device);
		
		return device;
	}

	// TODO: dirty
	public List<RoomModel> getRooms() {
		return new ArrayList<RoomModel>(mRooms.values());
	}

}

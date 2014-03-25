package com.bluebox.james.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.bluebox.james.Application;
import com.bluebox.james.SwitchModel;
import com.bluebox.james.model.ScenarioModel;
import com.bluebox.james.model.DeviceModel;
import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.ProbeModel;
import com.bluebox.james.model.RoomModel;

public class RoomService {

	private static RoomService 			sRoomService;
	private Map<Long, RoomModel> 		mRooms;
	private Map<Long, ScenarioModel> 		mActions;
	private Map<Long, FeatureModel> 	mFeatures;
	private HashMap<Long, ProbeModel> 	mProbes;
	private Map<Long,SwitchModel> 		mSwitchs;

	private RoomService() {
		mRooms = new HashMap<Long, RoomModel>();
		mFeatures = new HashMap<Long, FeatureModel>();
		mActions = new HashMap<Long, ScenarioModel>();
		mProbes = new HashMap<Long, ProbeModel>();
		mSwitchs = new HashMap<Long, SwitchModel>();
	}

	public static RoomService getInstance() {
		if (sRoomService == null) {
			sRoomService = new RoomService();
		}
		return sRoomService;
	}

	public int getCount() {
		return mRooms.size();
	}

	public List<ScenarioModel> getAllActions() {
		return new ArrayList<ScenarioModel>(mActions.values());
	}

//	public List<RoomModel> getRoomList() {
//		return new ArrayList<RoomModel>(mRooms.values());
//	}

	public static void execute(ScenarioModel action) {
		AQuery aquery = new AQuery(Application.getContext());
		
		Map<DeviceModel, Integer> equipments = action.getEquipments();
		for (DeviceModel equipment: equipments.keySet()) {
			int value = equipments.get(equipment);
			String url = equipment.getUrl(value);
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
		
		for (FeatureModel scene: room.getScenes()) {
			mFeatures.put(scene.getId(), scene);

			for (ScenarioModel action: scene.getActions()) {
				mActions.put(action.getId(), action);
			}
		}
	}

	public FeatureModel getFeature(long sceneId) {
		return mFeatures.get(sceneId);
	}

	public ScenarioModel getAction(long actionId) {
		return mActions.get(actionId);
	}

	public void addAction(FeatureModel scene, ScenarioModel action) {
		mActions.put(action.getId(), action);
		scene.addAction(action);
	}

	public RoomModel getRoom(long id) {
		return mRooms.get(id);
	}

	public List<ProbeModel> getProbeList() {
		return new ArrayList<ProbeModel>(mProbes.values());
	}

	public List<SwitchModel> getSwitchList() {
		return new ArrayList<SwitchModel>(mSwitchs.values());
	}

	public void addProbe(ProbeModel device) {
		mProbes.put(device.getId(), device);
	}

	public void addSwitch(SwitchModel device) {
		mSwitchs.put(device.getId(), device);
	}

}

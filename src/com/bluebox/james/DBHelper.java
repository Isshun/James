package com.bluebox.james;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bluebox.james.model.DeviceBaseModel;
import com.bluebox.james.model.DeviceProbeModel;
import com.bluebox.james.model.DeviceSwitchModel;
import com.bluebox.james.model.FeatureBaseModel;
import com.bluebox.james.model.FeatureLightModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.ScenarioModel;
 
public class DBHelper extends SQLiteOpenHelper {
 
	private static DBHelper mSelf;

	private SQLiteDatabase		mDB;

	private Map<Long, RoomModel> mRooms;

	private Map<Long, DeviceSwitchModel> mSwitchs;

	private static final int 	DB_VERSION = 1;
	private static final String DB_NAME = "data.db";

	private static final String T_ROOM = "t_room";
	private static final String T_ROOM_COL_ID = "Id";
	private static final String T_ROOM_COL_NAME = "Name";
	private static final String T_ROOM_COL_ICON = "Icon";
	private static final String CREATE_TABLE_ROOM = "CREATE TABLE " + T_ROOM + " ("
			+ T_ROOM_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ T_ROOM_COL_NAME + " TEXT NOT NULL, "
			+ T_ROOM_COL_ICON + " INTEGER NULL);";
	 
	private static final String T_FEATURE = "t_feature";
	private static final String T_FEATURE_COL_ID = "Id";
	private static final String T_FEATURE_COL_TYPE = "Type";
	private static final String T_FEATURE_COL_NAME = "Name";
	private static final String T_FEATURE_COL_ICON = "Icon";
	private static final String CREATE_TABLE_FEATURE = "CREATE TABLE " + T_FEATURE + " ("
			+ T_FEATURE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ T_FEATURE_COL_TYPE + " LONG NOT NULL, "
			+ T_FEATURE_COL_ICON + " INTEGER NOT NULL, "
			+ T_FEATURE_COL_NAME + " TEXT NOT NULL);";
 
	private static final String T_SCENARIO = "t_scenario";
	private static final String T_SCENARIO_COL_ID = "Id";
	private static final String T_SCENARIO_COL_ICON = "Icon";
	private static final String T_SCENARIO_COL_COLOR = "Color";
	private static final String T_SCENARIO_COL_NAME = "Name";
	private static final String CREATE_TABLE_SCENARIO = "CREATE TABLE " + T_SCENARIO + " ("
			+ T_SCENARIO_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ T_SCENARIO_COL_COLOR + " INTEGER NOT NULL, "
			+ T_SCENARIO_COL_ICON + " INTEGER NOT NULL, "
			+ T_SCENARIO_COL_NAME + " TEXT NOT NULL);";

	private static final String T_PROBE = "t_probe";
	private static final String T_PROBE_COL_ID = "Id";
	private static final String T_PROBE_COL_DEVICE_ID = "DeviceId";
	private static final String T_PROBE_COL_DEVICE_NAME = "Name";
	private static final String CREATE_TABLE_PROBE = "CREATE TABLE " + T_PROBE + " ("
			+ T_PROBE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ T_PROBE_COL_DEVICE_ID + " LONG NOT NULL, "
			+ T_PROBE_COL_DEVICE_NAME + " TEXT NOT NULL);";
	 
	private static final String T_SWITCH = "t_switch";
	private static final String T_SWITCH_COL_ID = "Id";
	private static final String T_SWITCH_COL_DEVICE_ID = "DeviceId";
	private static final String T_SWITCH_COL_DEVICE_NAME = "Name";
	private static final String CREATE_TABLE_SWITCH = "CREATE TABLE " + T_SWITCH + " ("
			+ T_SWITCH_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ T_SWITCH_COL_DEVICE_ID + " LONG NOT NULL, "
			+ T_SWITCH_COL_DEVICE_NAME + " TEXT NOT NULL);";
 
	private static final String T_ROOM_FEATURES = "t_room_features";
	private static final String T_ROOM_FEATURES_ID_ROOM = "roomId";
	private static final String T_ROOM_FEATURES_ID_FEATURE = "featureId";
	private static final String CREATE_TABLE_ROOM_FEATURE = "CREATE TABLE " + T_ROOM_FEATURES + " ("
			+ T_ROOM_FEATURES_ID_ROOM + " INTEGER NO NULL, "
			+ T_ROOM_FEATURES_ID_FEATURE + " INTEGER NOT NULL);";
 
	private static final String T_FEATURE_SCENARIOS = "t_feature_scenarios";
	private static final String T_FEATURE_SCENARIOS_ID_FEATURE = "featureId";
	private static final String T_FEATURE_SCENARIOS_ID_SCENARIO = "scenarioId";
	private static final String CREATE_TABLE_FEATURE_SCENARIOS = "CREATE TABLE " + T_FEATURE_SCENARIOS + " ("
			+ T_FEATURE_SCENARIOS_ID_FEATURE + " INTEGER NO NULL, "
			+ T_FEATURE_SCENARIOS_ID_SCENARIO + " INTEGER NOT NULL);";
 
	private static final String T_SCENARIO_DEVICES = "t_scenario_devices";
	private static final String T_SCENARIO_DEVICES_ID_SCENARIO = "featureId";
	private static final String T_SCENARIO_DEVICES_ID_DEVICE = "scenarioId";
	private static final String T_SCENARIO_DEVICES_VALUE = "value";
	private static final String CREATE_TABLE_SCENARIO_DEVICES = "CREATE TABLE " + T_SCENARIO_DEVICES + " ("
			+ T_SCENARIO_DEVICES_ID_SCENARIO + " INTEGER NO NULL, "
			+ T_SCENARIO_DEVICES_ID_DEVICE + " INTEGER NO NULL, "
			+ T_SCENARIO_DEVICES_VALUE + " INTEGER NOT NULL);";
 
	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
 
	public void open() {
		mDB = getWritableDatabase();
	}
 
	public void close() {
		mDB.close();
		mDB = null;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_SCENARIO_DEVICES);
		db.execSQL(CREATE_TABLE_FEATURE_SCENARIOS);
		db.execSQL(CREATE_TABLE_SCENARIO);
		db.execSQL(CREATE_TABLE_FEATURE);
//		db.execSQL(CREATE_TABLE_PROBE);
		db.execSQL(CREATE_TABLE_SWITCH);
		db.execSQL(CREATE_TABLE_ROOM);
		db.execSQL(CREATE_TABLE_ROOM_FEATURE);
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		reset(db);
	}

	public void reset(SQLiteDatabase db) {
		try { db.execSQL("DROP TABLE " + T_SCENARIO_DEVICES + ";"); } catch (Exception e) { e.printStackTrace(); }
		try { db.execSQL("DROP TABLE " + T_FEATURE_SCENARIOS + ";"); } catch (Exception e) { e.printStackTrace(); }
		try { db.execSQL("DROP TABLE " + T_ROOM_FEATURES + ";"); } catch (Exception e) { e.printStackTrace(); }
		try { db.execSQL("DROP TABLE " + T_SCENARIO + ";"); } catch (Exception e) { e.printStackTrace(); }
		try { db.execSQL("DROP TABLE " + T_FEATURE + ";"); } catch (Exception e) { e.printStackTrace(); }
//		try { db.execSQL("DROP TABLE " + T_PROBE + ";"); } catch (Exception e) { e.printStackTrace(); }
		try { db.execSQL("DROP TABLE " + T_SWITCH + ";"); } catch (Exception e) { e.printStackTrace(); }
		try { db.execSQL("DROP TABLE " + T_ROOM + ";"); } catch (Exception e) { e.printStackTrace(); }
		
		onCreate(db);
	}

	public long createProbe(DeviceProbeModel device) {
		ContentValues values = new ContentValues();
		
		values.put(DBHelper.T_PROBE_COL_DEVICE_ID, device.getDeviceId());
		values.put(DBHelper.T_PROBE_COL_DEVICE_NAME, device.getName());

		long dbId = mDB.insert(DBHelper.T_PROBE, null, values);
		device.setId(dbId);

		return mDB.insert(DBHelper.T_PROBE, null, values);
	}

	public long createSwitch(DeviceSwitchModel device) {
		ContentValues values = new ContentValues();
		
		values.put(DBHelper.T_SWITCH_COL_DEVICE_ID, device.getDeviceId());
		values.put(DBHelper.T_SWITCH_COL_DEVICE_NAME, device.getName());

		long dbId = mDB.insert(DBHelper.T_SWITCH, null, values);
		device.setId(dbId);
		
		return dbId;
	}

	public long createRoom(RoomModel room) {
		ContentValues values = new ContentValues();
		
		values.put(DBHelper.T_ROOM_COL_NAME, room.getName());
		values.put(DBHelper.T_ROOM_COL_ICON, room.getImgBackground());

		long dbId = mDB.insert(DBHelper.T_ROOM, null, values);
		room.setId(dbId);
		
		return dbId;
	}

//	public long createDevice(DeviceBaseModel device) {
//		ContentValues values = new ContentValues();
//		
//		values.put(DBHelper.T_SWITCH_COL_DEVICE_NAME, device.getName());
//		values.put(DBHelper.T_SWITCH_COL_DEVICE_ID, device.getDeviceId());
//
//		long dbId = mDB.insert(DBHelper.T_SWITCH, null, values);
//		device.setId(dbId);
//		
//		return dbId;
//	}
//
	public long createFeature(FeatureBaseModel feature) {
		ContentValues values = new ContentValues();
		
		values.put(DBHelper.T_FEATURE_COL_NAME, feature.getName());
		values.put(DBHelper.T_FEATURE_COL_ICON, feature.getIcon());
		values.put(DBHelper.T_FEATURE_COL_TYPE, feature.getType());

		long dbId = mDB.insert(DBHelper.T_FEATURE, null, values);
		feature.setId(dbId);
		
		return dbId;
	}

	public long createScenario(ScenarioModel scenario) {
		ContentValues values = new ContentValues();
		
		values.put(DBHelper.T_SCENARIO_COL_NAME, scenario.getName());
		values.put(DBHelper.T_SCENARIO_COL_ICON, scenario.getIcon());
		values.put(DBHelper.T_SCENARIO_COL_COLOR, scenario.getIcon());

		long dbId = mDB.insert(DBHelper.T_SCENARIO, null, values);
		scenario.setId(dbId);
		
		return dbId;
	}

	public void addScenarioToFeature(FeatureBaseModel feature, ScenarioModel scenario) {
		ContentValues values = new ContentValues();
		
		values.put(DBHelper.T_FEATURE_SCENARIOS_ID_FEATURE, feature.getId());
		values.put(DBHelper.T_FEATURE_SCENARIOS_ID_SCENARIO, scenario.getId());

		mDB.insert(DBHelper.T_FEATURE_SCENARIOS, null, values);
	}

	public void addDeviceToScenario(ScenarioModel scenario, DeviceBaseModel device, int value) {
		ContentValues values = new ContentValues();
		
		values.put(DBHelper.T_SCENARIO_DEVICES_ID_SCENARIO, scenario.getId());
		values.put(DBHelper.T_SCENARIO_DEVICES_ID_DEVICE, device.getId());
		values.put(DBHelper.T_SCENARIO_DEVICES_VALUE, value);

		mDB.insert(DBHelper.T_SCENARIO_DEVICES, null, values);
	}

	public void addFeatureToRoom(RoomModel room, FeatureBaseModel feature) {
		ContentValues values = new ContentValues();
		
		values.put(DBHelper.T_ROOM_FEATURES_ID_ROOM, room.getId());
		values.put(DBHelper.T_ROOM_FEATURES_ID_FEATURE, feature.getId());

		mDB.insert(DBHelper.T_ROOM_FEATURES, null, values);
	}

	public static DBHelper getInstance() {
		if (mSelf == null) {
			mSelf = new DBHelper(Application.getContext(), DB_NAME, null, DB_VERSION);
		}
		return mSelf;
	}

	public Map<Long, DeviceSwitchModel> getSwitchs() {
		return mSwitchs;
//		Map<Long, DeviceSwitchModel> switchs = new HashMap<Long, DeviceSwitchModel>();
//		
//		Cursor c = mDB.query(T_SWITCH, new String[] {T_SWITCH_COL_ID, T_SWITCH_COL_DEVICE_ID, T_SWITCH_COL_DEVICE_NAME}, null, null, null, null, null);
//
//		if (c.getCount() == 0)
//			return switchs;
//
//		while (c.moveToNext()) {
//			DeviceSwitchModel sw = new DeviceSwitchModel(c.getString(2), c.getLong(1));
//			switchs.put(sw.getId(), sw);
//		}
//		c.close();
//
//		return switchs;
	}

	public Map<Long, DeviceProbeModel> getProbes() {
		Map<Long, DeviceProbeModel> probes = new HashMap<Long, DeviceProbeModel>();
		
		Cursor c = mDB.query(T_PROBE, new String[] {T_PROBE_COL_ID, T_PROBE_COL_DEVICE_ID, T_PROBE_COL_DEVICE_NAME}, null, null, null, null, null);

		if (c.getCount() == 0)
			return probes;

		while (c.moveToNext()) {
			DeviceProbeModel probe = new DeviceProbeModel(c.getInt(0), c.getString(2), c.getLong(1));
			probes.put(probe.getId(), probe);
		}
		c.close();

		return probes;
	}

	public void load() {
		Log.i("LOAD_DB", "load db");

		mRooms = new HashMap<Long, RoomModel>();
		Map<Long, FeatureBaseModel> features = new HashMap<Long, FeatureBaseModel>();
		Map<Long, ScenarioModel> scenarios = new HashMap<Long, ScenarioModel>();
		mSwitchs = new HashMap<Long, DeviceSwitchModel>();

		{ // Get rooms
			Cursor c = mDB.query(T_ROOM, new String[] {T_ROOM_COL_ID, T_ROOM_COL_NAME, T_ROOM_COL_ICON}, null, null, null, null, null);
			while (c.moveToNext()) {
				Log.i("LOAD_DB", "Load room: " + c.getString(1));
				
				RoomModel room = new RoomModel(c.getLong(0), c.getString(1), c.getInt(2));
				mRooms.put(room.getId(), room);
			}
			c.close();
		}

		{ // Get features
			Cursor c = mDB.query(T_FEATURE, new String[] {T_FEATURE_COL_ID, T_FEATURE_COL_TYPE, T_FEATURE_COL_NAME, T_FEATURE_COL_ICON}, null, null, null, null, null);
			while (c.moveToNext()) {
				Log.i("LOAD_DB", "Load feature: " + c.getString(2));
				
				FeatureBaseModel feature = null;
				switch (c.getInt(1)) {
				case FeatureBaseModel.SCENE_LIGHT:
					feature = new FeatureLightModel(c.getLong(0), c.getString(2), c.getInt(3));
					break;
				default:
					feature = new FeatureLightModel(c.getLong(0), c.getString(2), c.getInt(3));
					break;
				}
				features.put(feature.getId(), feature);
			}
			c.close();
		}

		{ // Get scenarios
			Cursor c = mDB.query(T_SCENARIO, new String[] {T_SCENARIO_COL_ID, T_SCENARIO_COL_NAME, T_SCENARIO_COL_ICON, T_SCENARIO_COL_COLOR}, null, null, null, null, null);
			while (c.moveToNext()) {
				Log.i("LOAD_DB", "Load scenario: " + c.getString(1));

				ScenarioModel scenario = new ScenarioModel(c.getLong(0), c.getString(1), c.getInt(2), c.getInt(3));
				scenarios.put(scenario.getId(), scenario);
			}
			c.close();
		}

		{ // Get device
			Cursor c = mDB.query(T_SWITCH, new String[] {T_SWITCH_COL_ID, T_SWITCH_COL_DEVICE_ID, T_SWITCH_COL_DEVICE_NAME}, null, null, null, null, null);
			while (c.moveToNext()) {
				Log.i("LOAD_DB", "Load device: " + c.getLong(0) + " (" + c.getString(2) + ", #" + c.getLong(1) + ")");

				DeviceSwitchModel sw = new DeviceSwitchModel(c.getLong(0), c.getString(2), c.getLong(1));
				mSwitchs.put(sw.getId(), sw);
			}
			c.close();
		}
		
		{ // Add feature to room
			Cursor c = mDB.query(T_ROOM_FEATURES, new String[] {T_ROOM_FEATURES_ID_ROOM, T_ROOM_FEATURES_ID_FEATURE}, null, null, null, null, null);
			while (c.moveToNext()) {
				Log.i("LOAD_DB", "Add feature: " + c.getString(1) + " to room: " + c.getString(0));

				RoomModel room = mRooms.get(c.getLong(0));
				FeatureBaseModel feature = features.get(c.getLong(1));
				room.addFeature(feature);
			}
			c.close();
		}

		{ // Add scenarios to feature
			Cursor c = mDB.query(T_FEATURE_SCENARIOS, new String[] {T_FEATURE_SCENARIOS_ID_FEATURE, T_FEATURE_SCENARIOS_ID_SCENARIO}, null, null, null, null, null);
			while (c.moveToNext()) {
				Log.i("LOAD_DB", "Add scenario: " + c.getString(1) + " to feature: " + c.getString(0));

				FeatureBaseModel feature = features.get(c.getLong(0));
				ScenarioModel scenario = scenarios.get(c.getLong(1));
				feature.addScenario(scenario);
			}
			c.close();
		}

		{ // Add devices to scenario
			Cursor c = mDB.query(T_SCENARIO_DEVICES, new String[] {T_SCENARIO_DEVICES_ID_SCENARIO, T_SCENARIO_DEVICES_ID_DEVICE, T_SCENARIO_DEVICES_VALUE}, null, null, null, null, null);
			while (c.moveToNext()) {
				Log.i("LOAD_DB", "Add device: " + c.getString(1) + " to scenario: " + c.getString(0));

				ScenarioModel scenario = scenarios.get(c.getLong(0));
				DeviceBaseModel device = mSwitchs.get(c.getLong(1));
				scenario.addDevice(device, c.getInt(2));
			}
			c.close();
		}

//		{ // Get device
//			Cursor c = mDB.query(T_SWITCH, new String[] {T_SWITCH_COL_ID, T_SWITCH_COL_DEVICE_ID, T_SWITCH_COL_DEVICE_NAME}, null, null, null, null, null);
//			while (c.moveToNext()) {
//				DeviceSwitchModel sw = new DeviceSwitchModel(c.getString(2), c.getLong(1));
//				devices.put(sw.getId(), sw);
//			}
//			c.close();
//		}
	}

	public Map<Long, RoomModel> getRooms() {
		return mRooms;
	}

}
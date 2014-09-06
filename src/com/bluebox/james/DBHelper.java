package com.bluebox.james;
//package com.bluebox.james;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import com.bluebox.james.model.DeviceBaseModel;
//import com.bluebox.james.model.DeviceSwitchModel;
//import com.bluebox.james.model.FeatureModel;
//import com.bluebox.james.model.RoomModel;
//import com.bluebox.james.model.ScenarioOptionModel;
//import com.bluebox.james.model.scenario.Log;
//import com.bluebox.james.model.scenario.ScenarioCustom;
//import com.bluebox.james.model.scenario.ScenarioSwitch;
//
//public class DBHelper extends SQLiteOpenHelper {
//
//	private static DBHelper mSelf;
//
//	private SQLiteDatabase		mDB;
//
//	private Map<Long, RoomModel> mRooms;
//
//	private Map<Long, DeviceSwitchModel> mSwitchs;
//
//	private HashMap<Long, ScenarioOptionModel> mScenarios;
//	private HashMap<Long,ScenarioSwitch> mScenarioSwitchs;
//
//	private HashMap<Long, FeatureModel> mFeatures;
//
//	private static final int 	DB_VERSION = 1;
//	private static final String DB_NAME = "data.db";
//
//	private static final String T_ROOM = "t_room";
//	private static final String T_ROOM_COL_ID = "Id";
//	private static final String T_ROOM_COL_NAME = "Name";
//	private static final String T_ROOM_COL_ICON = "Icon";
//	private static final String CREATE_TABLE_ROOM = "CREATE TABLE " + T_ROOM + " ("
//			+ T_ROOM_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//			+ T_ROOM_COL_NAME + " TEXT NOT NULL, "
//			+ T_ROOM_COL_ICON + " INTEGER NULL);";
//
//	private static final String T_FEATURE = "t_feature";
//	private static final String T_FEATURE_COL_ID = "Id";
//	private static final String T_FEATURE_COL_TYPE = "Type";
//	private static final String T_FEATURE_COL_NAME = "Name";
//	private static final String T_FEATURE_COL_ICON = "Icon";
//	private static final String T_FEATURE_COL_COLOR = "Color";
//	private static final String CREATE_TABLE_FEATURE = "CREATE TABLE " + T_FEATURE + " ("
//			+ T_FEATURE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//			+ T_FEATURE_COL_TYPE + " LONG NOT NULL, "
//			+ T_FEATURE_COL_COLOR + " INTEGER NOT NULL, "
//			+ T_FEATURE_COL_ICON + " INTEGER NOT NULL, "
//			+ T_FEATURE_COL_NAME + " TEXT NOT NULL);";
//
//	private static final String T_SCENARIO = "t_scenario";
//	private static final String T_SCENARIO_COL_ID = "Id";
//	private static final String T_SCENARIO_COL_ICON = "Icon";
//	private static final String T_SCENARIO_COL_COLOR = "Color";
//	private static final String T_SCENARIO_COL_NAME = "Name";
//	private static final String CREATE_TABLE_SCENARIO = "CREATE TABLE " + T_SCENARIO + " ("
//			+ T_SCENARIO_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//			+ T_SCENARIO_COL_COLOR + " INTEGER NOT NULL, "
//			+ T_SCENARIO_COL_ICON + " INTEGER NOT NULL, "
//			+ T_SCENARIO_COL_NAME + " TEXT NOT NULL);";
//
//	private static final String T_SCENARIO_SWITCH = "t_scenario_switch";
//	private static final String T_SCENARIO_SWITCH_COL_ID = "Id";
//	private static final String T_SCENARIO_SWITCH_COL_FEATURE_ID = "FeatureId";
//	private static final String T_SCENARIO_SWITCH_COL_DEVICE_ID = "DeviceId";
//	private static final String T_SCENARIO_SWITCH_COL_ICON_ON = "IconOn";
//	private static final String T_SCENARIO_SWITCH_COL_ICON_OFF = "IconOff";
//	private static final String T_SCENARIO_SWITCH_COL_COLOR_ON = "ColorOn";
//	private static final String T_SCENARIO_SWITCH_COL_COLOR_OFF = "ColorOff";
//	private static final String T_SCENARIO_SWITCH_COL_LABEL_ON = "LabelOn";
//	private static final String T_SCENARIO_SWITCH_COL_LABEL_OFF = "LabelOff";
//	private static final String CREATE_TABLE_SCENARIO_SWITCH = "CREATE TABLE " + T_SCENARIO_SWITCH + " ("
//			+ T_SCENARIO_SWITCH_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//			+ T_SCENARIO_SWITCH_COL_FEATURE_ID + " INTEGER NOT NULL, "
//			+ T_SCENARIO_SWITCH_COL_DEVICE_ID + " INTEGER NOT NULL, "
//			+ T_SCENARIO_SWITCH_COL_COLOR_ON + " INTEGER NOT NULL, "
//			+ T_SCENARIO_SWITCH_COL_COLOR_OFF + " INTEGER NOT NULL, "
//			+ T_SCENARIO_SWITCH_COL_ICON_ON + " INTEGER NOT NULL, "
//			+ T_SCENARIO_SWITCH_COL_ICON_OFF + " INTEGER NOT NULL, "
//			+ T_SCENARIO_SWITCH_COL_LABEL_ON + " TEXT NOT NULL, "
//			+ T_SCENARIO_SWITCH_COL_LABEL_OFF + " TEXT NOT NULL);";
//
//	//	private static final String T_PROBE = "t_probe";
//	//	private static final String T_PROBE_COL_ID = "Id";
//	//	private static final String T_PROBE_COL_DEVICE_ID = "DeviceId";
//	//	private static final String T_PROBE_COL_DEVICE_NAME = "Name";
//	//	private static final String CREATE_TABLE_PROBE = "CREATE TABLE " + T_PROBE + " ("
//	//			+ T_PROBE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//	//			+ T_PROBE_COL_DEVICE_ID + " LONG NOT NULL, "
//	//			+ T_PROBE_COL_DEVICE_NAME + " TEXT NOT NULL);";
//
//	private static final String T_SWITCH = "t_switch";
//	private static final String T_SWITCH_COL_ID = "Id";
//	private static final String T_SWITCH_COL_DEVICE_ID = "DeviceId";
//	private static final String T_SWITCH_COL_DEVICE_NAME = "Name";
//	private static final String CREATE_TABLE_SWITCH = "CREATE TABLE " + T_SWITCH + " ("
//			+ T_SWITCH_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//			+ T_SWITCH_COL_DEVICE_ID + " LONG NOT NULL, "
//			+ T_SWITCH_COL_DEVICE_NAME + " TEXT NOT NULL);";
//
//	private static final String T_ROOM_FEATURES = "t_room_features";
//	private static final String T_ROOM_FEATURES_ID_ROOM = "roomId";
//	private static final String T_ROOM_FEATURES_ID_FEATURE = "featureId";
//	private static final String CREATE_TABLE_ROOM_FEATURE = "CREATE TABLE " + T_ROOM_FEATURES + " ("
//			+ T_ROOM_FEATURES_ID_ROOM + " INTEGER NO NULL, "
//			+ T_ROOM_FEATURES_ID_FEATURE + " INTEGER NOT NULL);";
//
//	private static final String T_FEATURE_SCENARIOS = "t_feature_scenarios";
//	private static final String T_FEATURE_SCENARIOS_ID_FEATURE = "featureId";
//	private static final String T_FEATURE_SCENARIOS_ID_SCENARIO = "scenarioId";
//	private static final String CREATE_TABLE_FEATURE_SCENARIOS = "CREATE TABLE " + T_FEATURE_SCENARIOS + " ("
//			+ T_FEATURE_SCENARIOS_ID_FEATURE + " INTEGER NO NULL, "
//			+ T_FEATURE_SCENARIOS_ID_SCENARIO + " INTEGER NOT NULL);";
//
//	private static final String T_SCENARIO_DEVICES = "t_scenario_devices";
//	private static final String T_SCENARIO_DEVICES_ID_SCENARIO = "featureId";
//	private static final String T_SCENARIO_DEVICES_ID_DEVICE = "scenarioId";
//	private static final String T_SCENARIO_DEVICES_VALUE = "value";
//	private static final String CREATE_TABLE_SCENARIO_DEVICES = "CREATE TABLE " + T_SCENARIO_DEVICES + " ("
//			+ T_SCENARIO_DEVICES_ID_SCENARIO + " INTEGER NO NULL, "
//			+ T_SCENARIO_DEVICES_ID_DEVICE + " INTEGER NO NULL, "
//			+ T_SCENARIO_DEVICES_VALUE + " INTEGER NOT NULL);";
//
//	public DBHelper(Context context, String name, CursorFactory factory, int version) {
//		super(context, name, factory, version);
//	}
//
//	public void open() {
//		mDB = getWritableDatabase();
//	}
//
//	public void close() {
//		mDB.close();
//		mDB = null;
//	}
//
//	public boolean isExists() {
//		Cursor c = mDB.query("sqlite_master WHERE type='table' AND name='" + T_ROOM + "';", null, null, null, null, null, null);
//		return c.getCount() > 0;
//	}
//
//	@Override
//	public void onCreate(SQLiteDatabase db) {
//		db.execSQL(CREATE_TABLE_SCENARIO_DEVICES);
//		db.execSQL(CREATE_TABLE_FEATURE_SCENARIOS);
//		db.execSQL(CREATE_TABLE_SCENARIO);
//		db.execSQL(CREATE_TABLE_SCENARIO_SWITCH);
//		db.execSQL(CREATE_TABLE_FEATURE);
//		//		db.execSQL(CREATE_TABLE_PROBE);
//		db.execSQL(CREATE_TABLE_SWITCH);
//		db.execSQL(CREATE_TABLE_ROOM);
//		db.execSQL(CREATE_TABLE_ROOM_FEATURE);
//	}
//
//	@Override
//	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		reset(db);
//	}
//
//	public void reset(SQLiteDatabase db) {
//		try { db.execSQL("DROP TABLE " + T_SCENARIO_DEVICES + ";"); } catch (Exception e) { e.printStackTrace(); }
//		try { db.execSQL("DROP TABLE " + T_FEATURE_SCENARIOS + ";"); } catch (Exception e) { e.printStackTrace(); }
//		try { db.execSQL("DROP TABLE " + T_ROOM_FEATURES + ";"); } catch (Exception e) { e.printStackTrace(); }
//		try { db.execSQL("DROP TABLE " + T_SCENARIO + ";"); } catch (Exception e) { e.printStackTrace(); }
//		try { db.execSQL("DROP TABLE " + T_SCENARIO_SWITCH + ";"); } catch (Exception e) { e.printStackTrace(); }
//		try { db.execSQL("DROP TABLE " + T_FEATURE + ";"); } catch (Exception e) { e.printStackTrace(); }
//		//		try { db.execSQL("DROP TABLE " + T_PROBE + ";"); } catch (Exception e) { e.printStackTrace(); }
//		try { db.execSQL("DROP TABLE " + T_SWITCH + ";"); } catch (Exception e) { e.printStackTrace(); }
//		try { db.execSQL("DROP TABLE " + T_ROOM + ";"); } catch (Exception e) { e.printStackTrace(); }
//
//		onCreate(db);
//	}
//
//	//	public long createProbe(DeviceProbeModel device) {
//	//		ContentValues values = new ContentValues();
//	//		
//	//		values.put(DBHelper.T_PROBE_COL_DEVICE_ID, device.getDeviceId());
//	//		values.put(DBHelper.T_PROBE_COL_DEVICE_NAME, device.getName());
//	//
//	//		long dbId = mDB.insert(DBHelper.T_PROBE, null, values);
//	//		device.setId(dbId);
//	//
//	//		return mDB.insert(DBHelper.T_PROBE, null, values);
//	//	}
//
////	public long createSwitch(DeviceSwitchModel device) {
////		ContentValues values = new ContentValues();
////
////		values.put(DBHelper.T_SWITCH_COL_DEVICE_ID, device.getDeviceId());
////		values.put(DBHelper.T_SWITCH_COL_DEVICE_NAME, device.getName());
////
////		long dbId = mDB.insert(DBHelper.T_SWITCH, null, values);
////		device.setId(dbId);
////
////		return dbId;
////	}
////
////	public long createRoom(RoomModel room) {
////		ContentValues values = new ContentValues();
////
////		values.put(DBHelper.T_ROOM_COL_NAME, room.getName());
////		values.put(DBHelper.T_ROOM_COL_ICON, room.getImgBackground());
////
////		long dbId = mDB.insert(DBHelper.T_ROOM, null, values);
////		room.setId(dbId);
////
////		return dbId;
////	}
//
//	//	public long createDevice(DeviceBaseModel device) {
//	//		ContentValues values = new ContentValues();
//	//		
//	//		values.put(DBHelper.T_SWITCH_COL_DEVICE_NAME, device.getName());
//	//		values.put(DBHelper.T_SWITCH_COL_DEVICE_ID, device.getDeviceId());
//	//
//	//		long dbId = mDB.insert(DBHelper.T_SWITCH, null, values);
//	//		device.setId(dbId);
//	//		
//	//		return dbId;
//	//	}
//	//
////	public long createFeature(FeatureModel feature) {
////		ContentValues values = new ContentValues();
////
////		values.put(DBHelper.T_FEATURE_COL_NAME, feature.getName());
////		values.put(DBHelper.T_FEATURE_COL_ICON, feature.getIcon());
////		values.put(DBHelper.T_FEATURE_COL_COLOR, feature.getColor());
////		values.put(DBHelper.T_FEATURE_COL_TYPE, feature.getType());
////
////		long dbId = mDB.insert(DBHelper.T_FEATURE, null, values);
////		feature.setId(dbId);
////
////		return dbId;
////	}
////
////	public long createScenario(ScenarioOptionModel scenario) {
////		ContentValues values = new ContentValues();
////
////		values.put(DBHelper.T_SCENARIO_COL_NAME, scenario.getLabel());
////		values.put(DBHelper.T_SCENARIO_COL_ICON, scenario.getIcon());
////		values.put(DBHelper.T_SCENARIO_COL_COLOR, scenario.getIcon());
////
////		long dbId = mDB.insert(DBHelper.T_SCENARIO, null, values);
////		scenario.setId(dbId);
////
////		return dbId;
////	}
//
//	public long createScenarioSwitch(ScenarioSwitch scenario) {
//		ContentValues values = new ContentValues();
//
//		values.put(DBHelper.T_SCENARIO_SWITCH_COL_FEATURE_ID, scenario.getFeature().getId());
//		values.put(DBHelper.T_SCENARIO_SWITCH_COL_DEVICE_ID, scenario.getOn().getDevice().getId());
//
//		values.put(DBHelper.T_SCENARIO_SWITCH_COL_LABEL_ON, scenario.getOn().getLabel());
//		values.put(DBHelper.T_SCENARIO_SWITCH_COL_ICON_ON, scenario.getOn().getIcon());
//		values.put(DBHelper.T_SCENARIO_SWITCH_COL_COLOR_ON, scenario.getOn().getColor());
//
//		values.put(DBHelper.T_SCENARIO_SWITCH_COL_LABEL_OFF, scenario.getOff().getLabel());
//		values.put(DBHelper.T_SCENARIO_SWITCH_COL_ICON_OFF, scenario.getOff().getIcon());
//		values.put(DBHelper.T_SCENARIO_SWITCH_COL_COLOR_OFF, scenario.getOff().getColor());
//
//		long dbId = mDB.insert(DBHelper.T_SCENARIO_SWITCH, null, values);
//		scenario.setId(dbId);
//
//		return dbId;
//	}
//
//	public int updateScenarioOption(ScenarioOptionModel scenario) {
//		ContentValues values = new ContentValues();
//
//		values.put(DBHelper.T_SCENARIO_COL_NAME, scenario.getLabel());
//		values.put(DBHelper.T_SCENARIO_COL_ICON, scenario.getIcon());
//		values.put(DBHelper.T_SCENARIO_COL_COLOR, scenario.getColor());
//
//		return mDB.update(DBHelper.T_SCENARIO, values, DBHelper.T_SCENARIO_COL_ID + " = " + scenario.getId(), null);
//	}
//
//	/**
//	 * Update simple switch scenario
//	 * 
//	 * @param scenarioSwitch
//	 */
//	public int updateScenario(ScenarioSwitch scenario) {
//		ContentValues values = new ContentValues();
//
//		values.put(DBHelper.T_SCENARIO_SWITCH_COL_FEATURE_ID, scenario.getFeature().getId());
//		values.put(DBHelper.T_SCENARIO_SWITCH_COL_DEVICE_ID, scenario.getOn().getDevice() != null ? scenario.getOn().getDevice().getId() : -1);
//
//		values.put(DBHelper.T_SCENARIO_SWITCH_COL_LABEL_ON, scenario.getOn().getLabel());
//		values.put(DBHelper.T_SCENARIO_SWITCH_COL_ICON_ON, scenario.getOn().getIcon());
//		values.put(DBHelper.T_SCENARIO_SWITCH_COL_COLOR_ON, scenario.getOn().getColor());
//
//		values.put(DBHelper.T_SCENARIO_SWITCH_COL_LABEL_OFF, scenario.getOff().getLabel());
//		values.put(DBHelper.T_SCENARIO_SWITCH_COL_ICON_OFF, scenario.getOff().getIcon());
//		values.put(DBHelper.T_SCENARIO_SWITCH_COL_COLOR_OFF, scenario.getOff().getColor());
//
//		return mDB.update(DBHelper.T_SCENARIO_SWITCH, values, DBHelper.T_SCENARIO_SWITCH_COL_ID + " = " + scenario.getId(), null);
//	}
//
//	public void updateScenario(ScenarioCustom scenario) {
//		for (ScenarioOptionModel option: scenario.getOptions()) {
//			updateScenarioOption(option);
//		}
//	}
//
//	public int updateFeature(FeatureModel feature) {
//		ContentValues values = new ContentValues();
//
//		values.put(DBHelper.T_FEATURE_COL_NAME, feature.getName());
//		values.put(DBHelper.T_FEATURE_COL_ICON, feature.getIcon());
//		values.put(DBHelper.T_FEATURE_COL_COLOR, feature.getColor());
//		values.put(DBHelper.T_FEATURE_COL_TYPE, feature.getType());
//
//		return mDB.update(DBHelper.T_FEATURE, values, DBHelper.T_FEATURE_COL_ID + " = " + feature.getId(), null);
//	}
//
//	public void addScenarioToFeature(FeatureModel feature, ScenarioOptionModel scenario) {
//		ContentValues values = new ContentValues();
//
//		values.put(DBHelper.T_FEATURE_SCENARIOS_ID_FEATURE, feature.getId());
//		values.put(DBHelper.T_FEATURE_SCENARIOS_ID_SCENARIO, scenario.getId());
//
//		mDB.insert(DBHelper.T_FEATURE_SCENARIOS, null, values);
//
//		
//		mScenarios.put(scenario.getId(), scenario);
//	}
//
//	public void addDeviceToScenario(ScenarioOptionModel scenario, DeviceBaseModel device, int value) {
//		ContentValues values = new ContentValues();
//
//		values.put(DBHelper.T_SCENARIO_DEVICES_ID_SCENARIO, scenario.getId());
//		values.put(DBHelper.T_SCENARIO_DEVICES_ID_DEVICE, device.getId());
//		values.put(DBHelper.T_SCENARIO_DEVICES_VALUE, value);
//
//		mDB.insert(DBHelper.T_SCENARIO_DEVICES, null, values);
//
//		//mSwitchs.put(device.getId(), device);
//	}
//
//	public void addFeatureToRoom(RoomModel room, FeatureModel feature) {
//		ContentValues values = new ContentValues();
//
//		values.put(DBHelper.T_ROOM_FEATURES_ID_ROOM, room.getId());
//		values.put(DBHelper.T_ROOM_FEATURES_ID_FEATURE, feature.getId());
//
//		mDB.insert(DBHelper.T_ROOM_FEATURES, null, values);
//
//		mFeatures.put(feature.getId(), feature);
//	}
//
//	public static DBHelper getInstance() {
//		if (mSelf == null) {
//			mSelf = new DBHelper(Application.getContext(), DB_NAME, null, DB_VERSION);
//		}
//		return mSelf;
//	}
//
//	public Map<Long, DeviceSwitchModel> getSwitchs() {
//		return mSwitchs;
//		//		Map<Long, DeviceSwitchModel> switchs = new HashMap<Long, DeviceSwitchModel>();
//		//		
//		//		Cursor c = mDB.query(T_SWITCH, new String[] {T_SWITCH_COL_ID, T_SWITCH_COL_DEVICE_ID, T_SWITCH_COL_DEVICE_NAME}, null, null, null, null, null);
//		//
//		//		if (c.getCount() == 0)
//		//			return switchs;
//		//
//		//		while (c.moveToNext()) {
//		//			DeviceSwitchModel sw = new DeviceSwitchModel(c.getString(2), c.getLong(1));
//		//			switchs.put(sw.getId(), sw);
//		//		}
//		//		c.close();
//		//
//		//		return switchs;
//	}
//
//	//	public Map<Long, DeviceProbeModel> getProbes() {
//	//		Map<Long, DeviceProbeModel> probes = new HashMap<Long, DeviceProbeModel>();
//	//		
//	//		Cursor c = mDB.query(T_PROBE, new String[] {T_PROBE_COL_ID, T_PROBE_COL_DEVICE_ID, T_PROBE_COL_DEVICE_NAME}, null, null, null, null, null);
//	//
//	//		if (c.getCount() == 0)
//	//			return probes;
//	//
//	//		while (c.moveToNext()) {
//	//			DeviceProbeModel probe = new DeviceProbeModel(c.getInt(0), c.getString(2), c.getLong(1));
//	//			probes.put(probe.getId(), probe);
//	//		}
//	//		c.close();
//	//
//	//		return probes;
//	//	}
////
////	public void load() {
////		Log.info("load db");
////
////		mRooms = new HashMap<Long, RoomModel>();
////		mFeatures = new HashMap<Long, FeatureModel>();
////		mScenarios = new HashMap<Long, ScenarioOptionModel>();
////		mScenarioSwitchs = new HashMap<Long, ScenarioSwitch>();
////		mSwitchs = new HashMap<Long, DeviceSwitchModel>();
////
////		{ // Get rooms
////			Cursor c = mDB.query(T_ROOM, new String[] {T_ROOM_COL_ID, T_ROOM_COL_NAME, T_ROOM_COL_ICON}, null, null, null, null, null);
////			while (c.moveToNext()) {
////				Log.info("Load room: " + c.getString(1));
////
////				RoomModel room = new RoomModel(c.getLong(0), c.getString(1), c.getInt(2));
////				mRooms.put(room.getId(), room);
////				// TODO
////				ApplicationService.getInstance().getRooms().add(room);
////			}
////			c.close();
////		}
////
////		{ // Get features
////			Cursor c = mDB.query(T_FEATURE, new String[] {T_FEATURE_COL_ID, T_FEATURE_COL_TYPE, T_FEATURE_COL_NAME, T_FEATURE_COL_ICON, T_FEATURE_COL_COLOR}, null, null, null, null, null);
////			while (c.moveToNext()) {
////				Log.info("Load feature: " + c.getString(2));
////
////				FeatureModel feature = new FeatureModel(c.getInt(1), c.getLong(0), c.getString(2), c.getInt(3), c.getInt(4));
////				mFeatures.put(feature.getId(), feature);
////				
////				// TODO
////				ApplicationService.getInstance().getFeatures().add(feature);
////			}
////			c.close();
////		}
////
////		{ // Get scenarios
////			Cursor c = mDB.query(T_SCENARIO, new String[] {T_SCENARIO_COL_ID, T_SCENARIO_COL_NAME, T_SCENARIO_COL_ICON, T_SCENARIO_COL_COLOR}, null, null, null, null, null);
////			while (c.moveToNext()) {
////				Log.info("Load scenario: " + c.getString(1));
////
////				ScenarioOptionModel scenario = new ScenarioOptionModel(null, null, c.getLong(0), c.getString(1), c.getInt(2), c.getInt(3));
////				mScenarios.put(scenario.getId(), scenario);
////
////				// TODO
////				ApplicationService.getInstance().getScenarios().add(scenario);
////			}
////			c.close();
////		}
////
////		{ // Get device
////			Cursor c = mDB.query(T_SWITCH, new String[] {T_SWITCH_COL_ID, T_SWITCH_COL_DEVICE_ID, T_SWITCH_COL_DEVICE_NAME}, null, null, null, null, null);
////			while (c.moveToNext()) {
////				Log.info("Load device: " + c.getLong(0) + " (" + c.getString(2) + ", #" + c.getLong(1) + ")");
////
////				DeviceSwitchModel sw = new DeviceSwitchModel(c.getLong(0), c.getString(2), c.getLong(1));
////				mSwitchs.put(sw.getId(), sw);
////				
////				// TODO
////				ApplicationService.getInstance().getDevices().add(sw);
////			}
////			c.close();
////		}
////
////		{ // Get scenario switch
////			Cursor c = mDB.query(T_SCENARIO_SWITCH, new String[] {
////					T_SCENARIO_SWITCH_COL_ID,
////					T_SCENARIO_SWITCH_COL_DEVICE_ID,
////					T_SCENARIO_SWITCH_COL_FEATURE_ID,
////					T_SCENARIO_SWITCH_COL_LABEL_ON,
////					T_SCENARIO_SWITCH_COL_LABEL_OFF,
////					T_SCENARIO_SWITCH_COL_ICON_ON,
////					T_SCENARIO_SWITCH_COL_ICON_OFF,
////					T_SCENARIO_SWITCH_COL_COLOR_ON,
////					T_SCENARIO_SWITCH_COL_COLOR_OFF
////			}, null, null, null, null, null);
////			while (c.moveToNext()) {
////				//Log.info("Load scenario swtich: " + c.getString(1));
////
////				FeatureModel feature = mFeatures.get(c.getLong(2));
////				DeviceBaseModel device = mSwitchs.get(c.getLong(1));
////
////				if (feature == null) { Log.error("Feature cannot be null"); continue; }
////				if (device == null) { Log.error("Device cannot be null"); continue; }
////				ScenarioSwitch scenario = new ScenarioSwitch(feature, device);
////				scenario.getOn().setLabel(c.getString(3));
////				scenario.getOff().setLabel(c.getString(4));
////				scenario.getOn().setIcon(c.getInt(5));
////				scenario.getOff().setIcon(c.getInt(6));
////				scenario.getOn().setColor(c.getInt(7));
////				scenario.getOff().setColor(c.getInt(8));
////				scenario.setId(c.getLong(0));
////				mScenarioSwitchs.put(scenario.getId(), scenario);
////				feature.setScenario(scenario);
////			}
////			c.close();
////		}
////
////		{ // Add feature to room
////			Cursor c = mDB.query(T_ROOM_FEATURES, new String[] {T_ROOM_FEATURES_ID_ROOM, T_ROOM_FEATURES_ID_FEATURE}, null, null, null, null, null);
////			while (c.moveToNext()) {
////				Log.info("Add feature: " + c.getString(1) + " to room: " + c.getString(0));
////
////				RoomModel room = mRooms.get(c.getLong(0));
////				FeatureModel feature = mFeatures.get(c.getLong(1));
////				room.addFeature(feature);
////			}
////			c.close();
////		}
////
////		{ // Add scenarios to feature
////			Cursor c = mDB.query(T_FEATURE_SCENARIOS, new String[] {T_FEATURE_SCENARIOS_ID_FEATURE, T_FEATURE_SCENARIOS_ID_SCENARIO}, null, null, null, null, null);
////			while (c.moveToNext()) {
////				Log.info("Add scenario: " + c.getString(1) + " to feature: " + c.getString(0));
////
////				FeatureModel feature = mFeatures.get(c.getLong(0));
////				ScenarioOptionModel scenario = mScenarios.get(c.getLong(1));
////				feature.addCustomScenario(scenario);
////			}
////			c.close();
////		}
////
////		{ // Add devices to scenario
////			Cursor c = mDB.query(T_SCENARIO_DEVICES, new String[] {T_SCENARIO_DEVICES_ID_SCENARIO, T_SCENARIO_DEVICES_ID_DEVICE, T_SCENARIO_DEVICES_VALUE}, null, null, null, null, null);
////			while (c.moveToNext()) {
////				Log.info("Add device: " + c.getString(1) + " to scenario: " + c.getString(0));
////
////				ScenarioOptionModel scenario = mScenarios.get(c.getLong(0));
////				DeviceBaseModel device = mSwitchs.get(c.getLong(1));
////				scenario.addDevice(device, c.getInt(2));
////			}
////			c.close();
////		}
////
////		//		{ // Get device
////		//			Cursor c = mDB.query(T_SWITCH, new String[] {T_SWITCH_COL_ID, T_SWITCH_COL_DEVICE_ID, T_SWITCH_COL_DEVICE_NAME}, null, null, null, null, null);
////		//			while (c.moveToNext()) {
////		//				DeviceSwitchModel sw = new DeviceSwitchModel(c.getString(2), c.getLong(1));
////		//				devices.put(sw.getId(), sw);
////		//			}
////		//			c.close();
////		//		}
////	}
//
//	public Map<Long, RoomModel> getRooms() {
//		return mRooms;
//	}
//
//	public Map<Long, ScenarioOptionModel> getScenarios() {
//		return mScenarios;
//	}
//
//	public Map<Long, FeatureModel> getFeatures() {
//		return mFeatures;
//	}
//
//}
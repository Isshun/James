package org.smallbox.doomotic;

import org.smallbox.doomotic.model.DeviceBaseModel;
import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.model.RoomModel;
import org.smallbox.doomotic.model.ScenarioOptionModel;
import org.smallbox.doomotic.model.scenario.ScenarioBase;
import org.smallbox.doomotic.service.DoomService;

import android.graphics.Color;
import android.util.Log;

public class Mock {

	public static void init() {
		
//		DBHelper.getInstance().reset(DBHelper.getInstance().getWritableDatabase());

		
		DoomService.getInstance().init();
		
//		if (DoomService.getInstance().getCount() != 0) {
//			Log.i("LOAD_DB", "load ok");
//			return;
//		}
//		
////		RoomService.getInstance().addProbe(new ProbeModel("Probe - Living room", 42));
////		RoomService.getInstance().addProbe(new ProbeModel("Probe - Ellen bedroom", 43));
////		RoomService.getInstance().addProbe(new ProbeModel("Probe - Parents' bedroom", 44));
////		RoomService.getInstance().addProbe(new ProbeModel("Probe - Outside", 45));
////		RoomService.getInstance().addProbe(new ProbeModel("Probe - Bathroom", 46));
////
////		RoomService.getInstance().addSwitch(new SwitchModel("Living - light 1", 4));
////		RoomService.getInstance().addSwitch(new SwitchModel("Living - light 2", 8));
////		RoomService.getInstance().addSwitch(new SwitchModel("Living - light 3", 20));
////		RoomService.getInstance().addSwitch(new SwitchModel("Living - ampli", 19));
//
//		// Salon
//		{
//			DeviceBaseModel light1 = DoomService.getInstance().createDevice("Light bed", 4);
//			DeviceBaseModel light2 = DoomService.getInstance().createDevice("Light lounge", 8);
//			DeviceBaseModel light3 = DoomService.getInstance().createDevice("Light desk", 20);
//
//			RoomModel salon = DoomService.getInstance().createRoom("Living Room", R.drawable.bg_living);
//
//			FeatureModel ftLight = DoomService.getInstance().createFeature(FeatureModel.SCENE_SCENARIO, "Light", R.drawable.bg_light, Color.RED);
//			DoomService.getInstance().addFeatureToRoom(salon, ftLight);
//			
//			ScenarioOptionModel scn1 = DoomService.getInstance().createScenario("Bright", R.drawable.ic_light_on);
//			scn1.setColor(Color.rgb(223, 90, 72));
//			DoomService.getInstance().addDeviceToScenario(scn1, light1, 0);
//			DoomService.getInstance().addDeviceToScenario(scn1, light2, 100);
//			DoomService.getInstance().addDeviceToScenario(scn1, light3, 100);
//			DoomService.getInstance().addScenarioToFeature(ftLight, scn1);
//
//			ScenarioOptionModel scn2 = DoomService.getInstance().createScenario("Medium", R.drawable.ic_light_medium);
//			scn2.setColor(Color.rgb(223, 90, 72));
//			DoomService.getInstance().addDeviceToScenario(scn2, light1, 100);
//			DoomService.getInstance().addDeviceToScenario(scn2, light2, 0);
//			DoomService.getInstance().addDeviceToScenario(scn2, light3, 100);
//			DoomService.getInstance().addScenarioToFeature(ftLight, scn2);
//
//			ScenarioOptionModel scn3 = DoomService.getInstance().createScenario("Light off", R.drawable.ic_light_off);
//			scn3.setColor(Color.rgb(223, 90, 72));
//			DoomService.getInstance().addDeviceToScenario(scn3, light1, 0);
//			DoomService.getInstance().addDeviceToScenario(scn3, light2, 0);
//			DoomService.getInstance().addDeviceToScenario(scn3, light3, 0);
//			DoomService.getInstance().addScenarioToFeature(ftLight, scn3);
//
//						
//			FeatureModel ampli = DoomService.getInstance().createFeature(FeatureModel.SCENE_SWITCH, "Ampli", R.drawable.bg_temp, Color.RED);
//			DoomService.getInstance().addFeatureToRoom(salon, ampli);
//			DeviceBaseModel eAmpli = DoomService.getInstance().createDevice("ampli", 19);
//			ScenarioBase ampliA1 = DoomService.getInstance().createScenarioSwitch(ampli, eAmpli);
//			//ampliA1.setColor(Color.rgb(234, 188, 48));
////			DoomService.getInstance().addDeviceToScenario(ampliA1, eAmpli, 0);
//			//DoomService.getInstance().addScenarioToFeature(ampli, ampliA1);
//
////			ScenarioModel ampliA2 = DoomService.getInstance().createScenario("On", R.drawable.bg_switch);
////			ampliA2.setColor(Color.rgb(234, 188, 48));
////			DoomService.getInstance().addDeviceToScenario(ampliA2, eAmpli, 100);
////			DoomService.getInstance().addScenarioToFeature(ampli, ampliA2);
//		}
//
////			{
////				DeviceBaseModel tempEquip = new DeviceProbeModel("Sonde 1", 100);
////				FeatureTemperatureModel temp = new FeatureTemperatureModel("Temp. salon", R.drawable.bg_temp);
////				ScenarioModel confTempAction = new ScenarioModel("23°", R.drawable.bg_temp);
////				confTempAction.addDevice(tempEquip, 100);
////				temp.addScenario(confTempAction);
////	
////				temp.setCurrent(21f);
////				temp.setExpected(24f);
////				
////				salon.addFeature(temp);
////			}
////			
////			{
////				DeviceBaseModel tempEquip = new DeviceProbeModel("Sonde 2", 101);
////				FeatureTemperatureModel temp = new FeatureTemperatureModel("Temp. terrasse", R.drawable.bg_temp);
////				ScenarioModel confTempAction = new ScenarioModel("12°", R.drawable.bg_temp);
////				confTempAction.addDevice(tempEquip, 100);
////				temp.addScenario(confTempAction);
////	
////				temp.setCurrent(12f);
////				salon.addFeature(temp);
////			}
////			
////			
////			//EquipmentModel tempEquip = new EquipmentModel("Sonde 1");
////			FeatureLightModel alarmScene = new FeatureLightModel("Alarm", R.drawable.bg_alarm);
////			ScenarioModel confAlarmAction = new ScenarioModel("08:30am", R.drawable.bg_alarm);
////			confAlarmAction.addDevice(light1, 100);
////			confAlarmAction.setColor(Color.rgb(77, 202, 185));
////			alarmScene.addScenario(confAlarmAction);
////			//temp.addAction(confTempAction);
////
////			
////			salon.addFeature(alarmScene);
////			//
////			salon.addFeature(ampli);
////			RoomService.getInstance().addRoom(salon);
////		}
////	
////		// Desk
////		{
////			RoomModel desk = new RoomModel("Office", R.drawable.bg_office);
////			FeatureLightModel light = new FeatureLightModel("Light", R.drawable.bg_light);
////			FeatureTemperatureModel temp = new FeatureTemperatureModel("Temperature", R.drawable.bg_temp);
////			temp.setCurrent(21f);
////			temp.setExpected(24f);
////			
////			desk.addFeature(light);
////			desk.addFeature(temp);
////			RoomService.getInstance().addRoom(desk);
////		}
////
////		// Bed
////		{
////			RoomModel bed = new RoomModel("Bedroom", R.drawable.bg_bedroom);
////			FeatureLightModel light = new FeatureLightModel("Light", R.drawable.bg_light);
////			FeatureTemperatureModel temp = new FeatureTemperatureModel("Temperature", R.drawable.bg_temp);
////			temp.setCurrent(21f);
////			temp.setExpected(24f);
////			
////			bed.addFeature(light);
////			bed.addFeature(temp);
////			RoomService.getInstance().addRoom(bed);
////		}
//
////		// Sdb
////		{
////			RoomModel sdb = new RoomModel("Bathroom", R.drawable.bg_bathroom);
////			SceneLightModel light = new SceneLightModel("Light", R.drawable.bg_light);
////			SceneTemperatureModel temp = new SceneTemperatureModel("Temperature", R.drawable.bg_temp);
////			temp.setCurrent(21f);
////			temp.setExpected(24f);
////			
////			RoomService.getInstance().getRoomList().add(sdb);
////			sdb.addScene(light);
////			sdb.addScene(temp);
////		}
//		
//		// Cuisine
//		{
//			DoomService.getInstance().createRoom("Kitchen", R.drawable.bg_kitchen);
//		}
	}
	
}

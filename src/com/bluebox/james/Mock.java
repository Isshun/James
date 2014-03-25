package com.bluebox.james;

import android.graphics.Color;

import com.bluebox.james.model.ScenarioModel;
import com.bluebox.james.model.DeviceModel;
import com.bluebox.james.model.ProbeModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.SceneLightModel;
import com.bluebox.james.model.SceneSwitchModel;
import com.bluebox.james.model.SceneTemperatureModel;
import com.bluebox.james.service.RoomService;

public class Mock {

	public static void init() {
		
		RoomService.getInstance().addProbe(new ProbeModel("Probe - Living room", 42));
		RoomService.getInstance().addProbe(new ProbeModel("Probe - Ellen bedroom", 43));
		RoomService.getInstance().addProbe(new ProbeModel("Probe - Parents' bedroom", 44));
		RoomService.getInstance().addProbe(new ProbeModel("Probe - Outside", 45));
		RoomService.getInstance().addProbe(new ProbeModel("Probe - Bathroom", 46));

		RoomService.getInstance().addSwitch(new SwitchModel("Living - light 1", 4));
		RoomService.getInstance().addSwitch(new SwitchModel("Living - light 2", 8));
		RoomService.getInstance().addSwitch(new SwitchModel("Living - light 3", 20));
		RoomService.getInstance().addSwitch(new SwitchModel("Living - ampli", 19));

		// Salon
		{
			DeviceModel light1 = new DeviceModel("Light bed", 4);
			DeviceModel light2 = new DeviceModel("Light lounge", 8);
			DeviceModel light3 = new DeviceModel("Light desk", 20);

			RoomModel salon = new RoomModel("Living Room", R.drawable.bg_living);

			SceneLightModel light = new SceneLightModel("Light", R.drawable.bg_light);
			salon.addScene(light);
			
			ScenarioModel a1 = new ScenarioModel("Bright", R.drawable.bg_light);
			a1.setColor(Color.rgb(223, 90, 72));
			a1.addEquipment(light1, 0);
			a1.addEquipment(light2, 100);
			a1.addEquipment(light3, 100);
			light.addAction(a1);

			ScenarioModel a2 = new ScenarioModel("Medium", R.drawable.bg_light);
			a2.setColor(Color.rgb(223, 90, 72));
			a2.addEquipment(light1, 100);
			a2.addEquipment(light2, 0);
			a2.addEquipment(light3, 100);
			light.addAction(a2);

			ScenarioModel a3 = new ScenarioModel("Light off", R.drawable.bg_light);
			a3.setColor(Color.rgb(223, 90, 72));
			a3.addEquipment(light1, 0);
			a3.addEquipment(light2, 0);
			a3.addEquipment(light3, 0);
			light.addAction(a3);

						
			SceneSwitchModel ampli = new SceneSwitchModel("Ampli", R.drawable.bg_switch);

			DeviceModel eAmpli = new DeviceModel("Light desk", 19);

			ScenarioModel ampliA1 = new ScenarioModel("Off", R.drawable.bg_switch);
			ampliA1.setColor(Color.rgb(234, 188, 48));
			ampliA1.addEquipment(eAmpli, 0);
			ampli.addAction(ampliA1);

			ScenarioModel ampliA2 = new ScenarioModel("On", R.drawable.bg_switch);
			ampliA2.setColor(Color.rgb(234, 188, 48));
			ampliA2.addEquipment(eAmpli, 100);
			ampli.addAction(ampliA2);

			{
				DeviceModel tempEquip = new DeviceModel("Sonde 1", 100);
				SceneTemperatureModel temp = new SceneTemperatureModel("Temp. salon", R.drawable.bg_temp);
				ScenarioModel confTempAction = new ScenarioModel("23°", R.drawable.bg_temp);
				confTempAction.addEquipment(tempEquip, 100);
				temp.addAction(confTempAction);
	
				temp.setCurrent(21f);
				temp.setExpected(24f);
				
				salon.addScene(temp);
			}
			
			{
				DeviceModel tempEquip = new DeviceModel("Sonde 2", 101);
				SceneTemperatureModel temp = new SceneTemperatureModel("Temp. terrasse", R.drawable.bg_temp);
				ScenarioModel confTempAction = new ScenarioModel("12°", R.drawable.bg_temp);
				confTempAction.addEquipment(tempEquip, 100);
				temp.addAction(confTempAction);
	
				temp.setCurrent(12f);
				salon.addScene(temp);
			}
			
			
			//EquipmentModel tempEquip = new EquipmentModel("Sonde 1");
			SceneLightModel alarmScene = new SceneLightModel("Alarm", R.drawable.bg_alarm);
			ScenarioModel confAlarmAction = new ScenarioModel("08:30am", R.drawable.bg_alarm);
			confAlarmAction.addEquipment(light1, 100);
			confAlarmAction.setColor(Color.rgb(77, 202, 185));
			alarmScene.addAction(confAlarmAction);
			//temp.addAction(confTempAction);

			
			salon.addScene(alarmScene);
			//
			salon.addScene(ampli);
			RoomService.getInstance().addRoom(salon);
		}
	
		// Desk
		{
			RoomModel desk = new RoomModel("Office", R.drawable.bg_office);
			SceneLightModel light = new SceneLightModel("Light", R.drawable.bg_light);
			SceneTemperatureModel temp = new SceneTemperatureModel("Temperature", R.drawable.bg_temp);
			temp.setCurrent(21f);
			temp.setExpected(24f);
			
			desk.addScene(light);
			desk.addScene(temp);
			RoomService.getInstance().addRoom(desk);
		}

		// Bed
		{
			RoomModel bed = new RoomModel("Bedroom", R.drawable.bg_bedroom);
			SceneLightModel light = new SceneLightModel("Light", R.drawable.bg_light);
			SceneTemperatureModel temp = new SceneTemperatureModel("Temperature", R.drawable.bg_temp);
			temp.setCurrent(21f);
			temp.setExpected(24f);
			
			bed.addScene(light);
			bed.addScene(temp);
			RoomService.getInstance().addRoom(bed);
		}

//		// Sdb
//		{
//			RoomModel sdb = new RoomModel("Bathroom", R.drawable.bg_bathroom);
//			SceneLightModel light = new SceneLightModel("Light", R.drawable.bg_light);
//			SceneTemperatureModel temp = new SceneTemperatureModel("Temperature", R.drawable.bg_temp);
//			temp.setCurrent(21f);
//			temp.setExpected(24f);
//			
//			RoomService.getInstance().getRoomList().add(sdb);
//			sdb.addScene(light);
//			sdb.addScene(temp);
//		}
		
		// Cuisine
		{
			RoomService.getInstance().addRoom(new RoomModel("Kitchen", R.drawable.bg_kitchen));
		}
	}
	
}

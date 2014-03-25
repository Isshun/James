package com.bluebox.james;

import android.graphics.Color;

import com.bluebox.james.model.ActionModel;
import com.bluebox.james.model.EquipmentModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.SceneLightModel;
import com.bluebox.james.model.SceneSwitchModel;
import com.bluebox.james.model.SceneTemperatureModel;
import com.bluebox.james.service.RoomService;

public class Mock {

	public static void init() {

		// Salon
		{
			EquipmentModel light1 = new EquipmentModel("Light bed");
			light1.setUrl(0, 4);
			light1.setUrl(100, 4);
			
			EquipmentModel light2 = new EquipmentModel("Light lounge");
			light2.setUrl(0, 8);
			light2.setUrl(100, 8);
			
			EquipmentModel light3 = new EquipmentModel("Light desk");
			light3.setUrl(0, 20);
			light3.setUrl(100, 20);
			
			RoomModel salon = new RoomModel("Living Room", R.drawable.bg_living);

			SceneLightModel light = new SceneLightModel("Light", R.drawable.bg_light);
			salon.addScene(light);
			
			ActionModel a1 = new ActionModel("Bright", R.drawable.bg_light);
			a1.setColor(Color.rgb(223, 90, 72));
			a1.addEquipment(light1, 0);
			a1.addEquipment(light2, 100);
			a1.addEquipment(light3, 100);
			light.addAction(a1);

			ActionModel a2 = new ActionModel("Medium", R.drawable.bg_light);
			a2.setColor(Color.rgb(223, 90, 72));
			a2.addEquipment(light1, 100);
			a2.addEquipment(light2, 0);
			a2.addEquipment(light3, 100);
			light.addAction(a2);

			ActionModel a3 = new ActionModel("Light off", R.drawable.bg_light);
			a3.setColor(Color.rgb(223, 90, 72));
			a3.addEquipment(light1, 0);
			a3.addEquipment(light2, 0);
			a3.addEquipment(light3, 0);
			light.addAction(a3);

						
			SceneSwitchModel ampli = new SceneSwitchModel("Ampli", R.drawable.bg_switch);

			EquipmentModel eAmpli = new EquipmentModel("Light desk");
			eAmpli.setUrl(0, 19);
			eAmpli.setUrl(100, 19);

			ActionModel ampliA1 = new ActionModel("Off", R.drawable.bg_switch);
			ampliA1.setColor(Color.rgb(234, 188, 48));
			ampliA1.addEquipment(eAmpli, 0);
			ampli.addAction(ampliA1);

			ActionModel ampliA2 = new ActionModel("On", R.drawable.bg_switch);
			ampliA2.setColor(Color.rgb(234, 188, 48));
			ampliA2.addEquipment(eAmpli, 100);
			ampli.addAction(ampliA2);

			
			EquipmentModel tempEquip = new EquipmentModel("Sonde 1");
			SceneTemperatureModel temp = new SceneTemperatureModel("Temperature", R.drawable.bg_temp);
			ActionModel confTempAction = new ActionModel("23°", R.drawable.bg_temp);
			confTempAction.addEquipment(tempEquip, 100);
			temp.addAction(confTempAction);

			temp.setCurrent(21f);
			temp.setExpected(24f);
			
			
			//EquipmentModel tempEquip = new EquipmentModel("Sonde 1");
			SceneLightModel alarmScene = new SceneLightModel("Alarm", R.drawable.bg_alarm);
			ActionModel confAlarmAction = new ActionModel("08:30am", R.drawable.bg_alarm);
			confAlarmAction.addEquipment(light1, 100);
			confAlarmAction.setColor(Color.rgb(77, 202, 185));
			alarmScene.addAction(confAlarmAction);
			temp.addAction(confTempAction);

			
			salon.addScene(alarmScene);
			salon.addScene(temp);
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

		// Sdb
		{
			RoomModel sdb = new RoomModel("Bathroom", R.drawable.bg_bathroom);
			SceneLightModel light = new SceneLightModel("Light", R.drawable.bg_light);
			SceneTemperatureModel temp = new SceneTemperatureModel("Temperature", R.drawable.bg_temp);
			temp.setCurrent(21f);
			temp.setExpected(24f);
			
			RoomService.getInstance().getRoomList().add(sdb);
			sdb.addScene(light);
			sdb.addScene(temp);
		}
		
		// Cuisine
		{
			RoomService.getInstance().addRoom(new RoomModel("Kitchen", R.drawable.bg_kitchen));
		}
	}
	
}

package com.bluebox.james;

import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.SceneLightModel;
import com.bluebox.james.model.SceneModel;
import com.bluebox.james.model.SceneTemperatureModel;
import com.bluebox.james.service.RoomService;

public class Mock {

	public static void init() {

		// Salon
		{
			RoomModel salon = new RoomModel("Salon");
			SceneLightModel light = new SceneLightModel("Lumière");
			light.addUrl("http://192.168.1.2:8080/json.htm?type=command&param=switchscene&idx=4&switchcmd=On");
			light.addUrl("http://192.168.1.2:8080/json.htm?type=command&param=switchscene&idx=2&switchcmd=On");
			light.addUrl("http://192.168.1.2:8080/json.htm?type=command&param=switchscene&idx=3&switchcmd=On");
			SceneModel ampli = new SceneModel("Ampli");
			SceneTemperatureModel temp = new SceneTemperatureModel("Temperature");
			temp.setCurrent(21f);
			temp.setExpected(24f);
			
			salon.addScene(light);
			salon.addScene(temp);
			salon.addScene(ampli);
			RoomService.getInstance().getRoomList().add(salon);
		}
	
		// Desk
		{
			RoomModel desk = new RoomModel("Bureau");
			SceneLightModel light = new SceneLightModel("Lumière");
			SceneTemperatureModel temp = new SceneTemperatureModel("Temperature");
			temp.setCurrent(21f);
			temp.setExpected(24f);
			
			desk.addScene(light);
			desk.addScene(temp);
			RoomService.getInstance().getRoomList().add(desk);
		}

		// Bed
		{
			RoomModel bed = new RoomModel("Chambre");
			SceneLightModel light = new SceneLightModel("Lumière");
			SceneTemperatureModel temp = new SceneTemperatureModel("Temperature");
			temp.setCurrent(21f);
			temp.setExpected(24f);
			
			bed.addScene(light);
			bed.addScene(temp);
			RoomService.getInstance().getRoomList().add(bed);
		}

		// Sdb
		{
			RoomModel sdb = new RoomModel("Salle de bain");
			SceneLightModel light = new SceneLightModel("Lumière");
			SceneTemperatureModel temp = new SceneTemperatureModel("Temperature");
			temp.setCurrent(21f);
			temp.setExpected(24f);
			
			RoomService.getInstance().getRoomList().add(sdb);
			sdb.addScene(light);
			sdb.addScene(temp);
		}
		
		// Cuisine
		{
			RoomService.getInstance().getRoomList().add(new RoomModel("Cuisine"));
		}
	}
	
}

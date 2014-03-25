package com.bluebox.james.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bluebox.james.R;
import com.bluebox.james.model.ActionModel;
import com.bluebox.james.model.EquipmentModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.featureModel;
import com.bluebox.james.service.RoomService;

public class ScenarioEditActivity extends FragmentActivity {

    private RoomModel 	mRoom;
    private featureModel 	mScene;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_scenario);
        
//        final ActionBar ab = getActionBar();
//        ab.setDisplayShowHomeEnabled(false);
//        ab.setDisplayShowTitleEnabled(false);     
//        final LayoutInflater inflater = (LayoutInflater)getSystemService("layout_inflater");
//        View view = inflater.inflate(R.layout.action_bar_edit_mode, null); 
//        ab.setCustomView(view, new ActionBar.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));
//        ab.setDisplayShowCustomEnabled(true);

        Bundle bundle = getIntent().getExtras();
        RoomModel room = RoomService.getInstance().getRoom(bundle.getLong("room_id"));
        featureModel scene = RoomService.getInstance().getFeature(bundle.getLong("scene_id"));
        ActionModel action = RoomService.getInstance().getAction(bundle.getLong("action_id"));

        int i = 0;
        for (EquipmentModel device: action.getEquipments().keySet()) {
        	if (i == 0) {
                EditText editIdAction1 = (EditText)findViewById(R.id.edit_device_1_id);
                editIdAction1.setText(String.valueOf(device.getId()));
                EditText editValueAction1 = (EditText)findViewById(R.id.edit_device_1_value);
                editValueAction1.setText(String.valueOf(action.getEquipments().get(device)));
        	}
        	if (i == 1) {
                EditText editIdAction2 = (EditText)findViewById(R.id.edit_device_2_id);
                editIdAction2.setText(String.valueOf(device.getId()));
                EditText editValueAction2 = (EditText)findViewById(R.id.edit_device_2_value);
                editValueAction2.setText(String.valueOf(action.getEquipments().get(device)));
        	}
        	if (i == 2) {
                EditText editIdAction3 = (EditText)findViewById(R.id.edit_device_3_id);
                editIdAction3.setText(String.valueOf(device.getId()));
                EditText editValueAction3 = (EditText)findViewById(R.id.edit_device_3_value);
                editValueAction3.setText(String.valueOf(action.getEquipments().get(device)));
        	}
        	if (i == 3) {
                EditText editIdAction4 = (EditText)findViewById(R.id.edit_device_4_id);
                editIdAction4.setText(String.valueOf(device.getId()));
                EditText editValueAction4 = (EditText)findViewById(R.id.edit_device_4_value);
                editValueAction4.setText(String.valueOf(action.getEquipments().get(device)));
        	}
        	
			i++;
		}
        
        Utils.hideKeyboard(this);
    }

}

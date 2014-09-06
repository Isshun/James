package org.smallbox.doomotic.activity;
//package org.smallbox.doomotic.activity;
//
//import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.widget.EditText;
//
//import org.smallbox.doomotic.R;
//import org.smallbox.doomotic.Utils;
//import org.smallbox.doomotic.model.DeviceBaseModel;
//import org.smallbox.doomotic.model.FeatureModel;
//import org.smallbox.doomotic.model.RoomModel;
//import org.smallbox.doomotic.model.ScenarioOptionModel;
//import org.smallbox.doomotic.service.DoomService;
//
//public class ScenarioEditActivity extends FragmentActivity {
//
//    private RoomModel 	mRoom;
//    private FeatureModel 	mScene;
//    
//	@Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_scenario);
//        
////        final ActionBar ab = getActionBar();
////        ab.setDisplayShowHomeEnabled(false);
////        ab.setDisplayShowTitleEnabled(false);     
////        final LayoutInflater inflater = (LayoutInflater)getSystemService("layout_inflater");
////        View view = inflater.inflate(R.layout.action_bar_edit_mode, null); 
////        ab.setCustomView(view, new ActionBar.LayoutParams(
////                ViewGroup.LayoutParams.MATCH_PARENT,
////                ViewGroup.LayoutParams.MATCH_PARENT));
////        ab.setDisplayShowCustomEnabled(true);
//
//        Bundle bundle = getIntent().getExtras();
//        RoomModel room = DoomService.getInstance().getRoom(bundle.getLong("room_id"));
//        FeatureModel scene = DoomService.getInstance().getFeature(bundle.getLong("scene_id"));
//        ScenarioOptionModel action = DoomService.getInstance().getScenario(bundle.getLong("action_id"));
//
//        int i = 0;
//        for (DeviceBaseModel device: action.getDevices().keySet()) {
//        	if (i == 0) {
////                EditText editIdAction1 = (EditText)findViewById(R.id.edit_device_1_id);
////                editIdAction1.setText(String.valueOf(device.getId()));
////                EditText editValueAction1 = (EditText)findViewById(R.id.edit_device_1_value);
////                editValueAction1.setText(String.valueOf(action.getDevices().get(device)));
//        	}
//        	if (i == 1) {
//                EditText editIdAction2 = (EditText)findViewById(R.id.edit_device_2_id);
//                editIdAction2.setText(String.valueOf(device.getId()));
//                EditText editValueAction2 = (EditText)findViewById(R.id.edit_device_2_value);
//                editValueAction2.setText(String.valueOf(action.getDevices().get(device)));
//        	}
//        	if (i == 2) {
//                EditText editIdAction3 = (EditText)findViewById(R.id.edit_device_3_id);
//                editIdAction3.setText(String.valueOf(device.getId()));
//                EditText editValueAction3 = (EditText)findViewById(R.id.edit_device_3_value);
//                editValueAction3.setText(String.valueOf(action.getDevices().get(device)));
//        	}
//        	if (i == 3) {
//                EditText editIdAction4 = (EditText)findViewById(R.id.edit_device_4_id);
//                editIdAction4.setText(String.valueOf(device.getId()));
//                EditText editValueAction4 = (EditText)findViewById(R.id.edit_device_4_value);
//                editValueAction4.setText(String.valueOf(action.getDevices().get(device)));
//        	}
//        	
//			i++;
//		}
//        
//        Utils.hideKeyboard(this);
//    }
//
//}

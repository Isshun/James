package com.bluebox.james.activity;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bluebox.james.Application;
import com.bluebox.james.R;
import com.bluebox.james.Utils;
import com.bluebox.james.adapter.ActionAdapter;
import com.bluebox.james.dialog.ColorDialogFragment;
import com.bluebox.james.dialog.EditScenarioDialogFragment;
import com.bluebox.james.dialog.NewScenarioDialogFragment;
import com.bluebox.james.model.ScenarioModel;
import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.service.RoomService;

public class FeatureEditActivity extends FragmentActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_feature);

        Bundle bundle = getIntent().getExtras();
        final RoomModel room = RoomService.getInstance().getRoom(bundle.getLong(Application.ARG_ROOM_ID));
        final FeatureModel feature = room.getFeature(bundle.getLong(Application.ARG_FEATURE_ID));

        // "Scenario name" EditText
        EditText editSceneName = (EditText)findViewById(R.id.edit_scene_name);
        editSceneName.setText(feature.getName());

        // "Feature name" Label
        TextView lbFeature = (TextView)findViewById(R.id.lb_feature);
        lbFeature.setText(feature.getTypeName());

        // "Actions" ListView
        final ListView listAction = (ListView)findViewById(R.id.list_action);
        final ActionAdapter actionsAdapter = new ActionAdapter(feature);
        listAction.setAdapter(actionsAdapter);
        listAction.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				ScenarioModel action = feature.getActions().get(pos);
//				Intent intent = new Intent(FeatureEditActivity.this, ScenarioEditActivity.class);
//				startActivity(intent);
				
				EditScenarioDialogFragment f = new EditScenarioDialogFragment();
				f.setOnCloseListener(new EditScenarioDialogFragment.OnCloseListener() {
					@Override
					public void onClose() {
						actionsAdapter.notifyDataSetChanged();
					}
				});
		    	Bundle args = new Bundle();
				args.putLong(Application.ARG_ROOM_ID, room.getId());
				args.putLong(Application.ARG_FEATURE_ID, feature.getId());
				args.putLong(Application.ARG_ACTION_ID, action.getId());
		        f.setArguments(args);
		        f.show(getFragmentManager().beginTransaction(), "dialog");
			}
		});
        
        // Action on "add actions" button
        findViewById(R.id.bt_new_scenario).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				NewScenarioDialogFragment f = new NewScenarioDialogFragment();
				f.setOnCloseListener(new NewScenarioDialogFragment.OnCloseListener() {
					@Override
					public void onClose() {
						actionsAdapter.notifyDataSetChanged();
					}
				});
		    	Bundle args = new Bundle();
		        args.putLong(Application.ARG_FEATURE_ID, feature.getId());
		        f.setArguments(args);
		        f.show(getFragmentManager().beginTransaction(), "dialog");
			}
		});
        
        // Action on "select color" button
        findViewById(R.id.bt_scene_color).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		    	DialogFragment f = new ColorDialogFragment();
		        f.show(getFragmentManager().beginTransaction(), "dialog");
			}
		});

        Utils.hideKeyboard(this);
    }

}

package com.bluebox.james.activity;

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
import com.bluebox.james.adapter.ScenarioAdapter;
import com.bluebox.james.dialog.BaseDialogFragment.OnCloseListener;
import com.bluebox.james.dialog.EditScenarioDialogFragment;
import com.bluebox.james.dialog.NewScenarioDialogFragment;
import com.bluebox.james.dialog.SelectColorDialogFragment;
import com.bluebox.james.model.FeatureBaseModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.ScenarioModel;
import com.bluebox.james.service.RoomService;

public class FeatureEditActivity extends FragmentActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_feature);

        Bundle bundle = getIntent().getExtras();
        final RoomModel room = RoomService.getInstance().getRoom(bundle.getLong(Application.ARG_ROOM_ID));
        final FeatureBaseModel feature = room.getFeature(bundle.getLong(Application.ARG_FEATURE_ID));

        findViewById(R.id.bt_set_switch).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				feature.setType(FeatureBaseModel.SCENE_LIGHT);
				feature.commit();
			}
		});

        findViewById(R.id.bt_set_temp).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				feature.setType(FeatureBaseModel.SCENE_TEMPERATURE);
				feature.commit();
			}
		});
        
        // "Scenario name" EditText
        EditText editSceneName = (EditText)findViewById(R.id.edit_scene_name);
        editSceneName.setText(feature.getName());

        // "Feature name" Label
        TextView lbFeature = (TextView)findViewById(R.id.lb_feature);
        lbFeature.setText(feature.getTypeName());

        // "Actions" ListView
        final ListView listAction = (ListView)findViewById(R.id.list_action);
        final ScenarioAdapter actionsAdapter = new ScenarioAdapter(feature);
        listAction.setAdapter(actionsAdapter);
        listAction.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				ScenarioModel action = feature.getScenarios().get(pos);
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
				args.putLong(Application.ARG_SCENARIO_ID, action.getId());
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
		findViewById(R.id.bt_scene_color).setBackgroundColor(feature.getColor());
        findViewById(R.id.bt_scene_color).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final SelectColorDialogFragment f = new SelectColorDialogFragment();
		        f.show(getFragmentManager().beginTransaction(), "dialog");
		        f.setOnCloseListener(new OnCloseListener() {
					@Override
					public void onClose() {
						if (f.getColor() != -1) {
							findViewById(R.id.bt_scene_color).setBackgroundColor(f.getColor());
							feature.setColor(f.getColor());
							feature.commit();
						}
					}
				});
			}
		});

        Utils.hideKeyboard(this);
    }

}

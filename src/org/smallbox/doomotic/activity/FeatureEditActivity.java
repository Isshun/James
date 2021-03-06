package org.smallbox.doomotic.activity;

import org.smallbox.doomotic.Application;
import org.smallbox.doomotic.Utils;
import org.smallbox.doomotic.adapter.ScenarioAdapter;
import org.smallbox.doomotic.dialog.ColorPickerDialog;
import org.smallbox.doomotic.dialog.DeviceSelectDialog;
import org.smallbox.doomotic.dialog.IconSelectDialog;
import org.smallbox.doomotic.dialog.ScenarioCreateDialog;
import org.smallbox.doomotic.dialog.ScenarioEditDialog;
import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.model.RoomModel;
import org.smallbox.doomotic.model.scenario.ScenarioCustom;
import org.smallbox.doomotic.model.scenario.ScenarioSwitch;
import org.smallbox.doomotic.model.scenario.ScenarioTemperature;
import org.smallbox.doomotic.service.DoomService;
import org.smallbox.lib.dialog.BaseDialogFragment.OnCloseListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bluebox.james.R;

public class FeatureEditActivity extends FragmentActivity {
	private FeatureModel mFeature;
	private RoomModel mRoom;
	private EditText editSceneName;
	private View btSwitch;
	private View btTemp;
	private View btScenario;
	private ListView listAction;
	private ScenarioAdapter actionsAdapter;
	private IconSelectDialog mSelectIconDialog;
	private ViewGroup mFrameOptions;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_feature);

        Bundle bundle = getIntent().getExtras();
        mRoom = DoomService.getInstance().getRoom(bundle.getInt(Application.ARG_ROOM_ID));
        mFeature = mRoom.getFeature(bundle.getInt(Application.ARG_FEATURE_ID));
        
        // "select icon" dialog
		mSelectIconDialog = new IconSelectDialog();
		mSelectIconDialog.setOnCloseListener(new IconSelectDialog.OnCloseListener() {
			@Override
			public void onClose() {
				if (mSelectIconDialog.hasBeenActivated()) {
					mFeature.setIcon(mSelectIconDialog.getIcon());
			        //imgIcon.setImageResource(mSelectIconDialog.getIcon());
				} else {
					//imgIcon.setImageDrawable(null);
				}
			}
		});
        View btIcon = findViewById(R.id.bt_icon);
        btIcon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mSelectIconDialog.show(getSupportFragmentManager().beginTransaction(), "dialog");
			}
		});

        // Button switch
        btSwitch = findViewById(R.id.bt_type_switch);
        btSwitch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mFeature.setType(FeatureModel.SCENE_SWITCH).commit();
				refresh();
			}
		});
        
        // Button temperature
        btTemp = findViewById(R.id.bt_type_temp);
        btTemp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mFeature.setType(FeatureModel.SCENE_TEMPERATURE).commit();
				refresh();
			}
		});

        // Button custom scenario
        btScenario = findViewById(R.id.bt_type_scenario);
        btScenario.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mFeature.setType(FeatureModel.SCENE_SCENARIO).commit();
				refresh();
			}
		});
        
        // "Scenario name" EditText
        editSceneName = (EditText)findViewById(R.id.edit_scene_name);
        editSceneName.setText(mFeature.getName());

        // "Feature name" Label
        TextView lbFeature = (TextView)findViewById(R.id.lb_feature);
        lbFeature.setText(mFeature.getTypeName());

        // Action on "select color" button
		findViewById(R.id.bt_scene_color).setBackgroundColor(mFeature.getColor());
        findViewById(R.id.bt_scene_color).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final ColorPickerDialog f = new ColorPickerDialog();
		        f.show(getSupportFragmentManager().beginTransaction(), "dialog");
		        f.setOnCloseListener(new OnCloseListener() {
					@Override
					public void onClose() {
						if (f.getColor() != -1) {
							findViewById(R.id.bt_scene_color).setBackgroundColor(f.getColor());
							mFeature.setColor(f.getColor());
							mFeature.commit();
						}
					}
				});
			}
		});

        Utils.hideKeyboard(this);
        
        refresh();
    }

	private void createFromType(int type) {
        switch (type) {
		case FeatureModel.SCENE_SWITCH:
			createSwitchOptions(mFeature.getCurrentScenario().asSwitch(), LayoutInflater.from(this).inflate(R.layout.view_feature_switch_options, null));
			break;
		case FeatureModel.SCENE_SCENARIO:
			createScenarioOptions(mFeature.getCurrentScenario().asCustom(), LayoutInflater.from(this).inflate(R.layout.view_feature_custom_options, null));
			break;
		case FeatureModel.SCENE_TEMPERATURE:
			createTemperatureOptions(mFeature.getCurrentScenario().asTemperature(), LayoutInflater.from(this).inflate(R.layout.view_feature_temperature_options, null));
			break;
		}
	}

	private void createTemperatureOptions(final ScenarioTemperature scenario, View view) {
        mFrameOptions = (ViewGroup)findViewById(R.id.frame_options);
        mFrameOptions.removeAllViews();
        mFrameOptions.addView(view);

        if (scenario != null && scenario.getCurrent() != null && scenario.getCurrent().getDevice() != null) {
            ((TextView)view.findViewById(R.id.lb_temperature)).setText(scenario.getCurrent().getDevice().getValue() + "�");
        } else {
            ((TextView)view.findViewById(R.id.lb_temperature)).setText("No sensor");
        }
	}

	private void createScenarioOptions(final ScenarioCustom scenario, View view) {
        mFrameOptions = (ViewGroup)findViewById(R.id.frame_options);
        mFrameOptions.removeAllViews();
        mFrameOptions.addView(view);

        // Action on "add actions" button
        findViewById(R.id.bt_new_scenario).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ScenarioCreateDialog f = ScenarioCreateDialog.newInstance(mFeature);
				f.setOnCloseListener(new ScenarioCreateDialog.OnCloseListener() {
					@Override
					public void onClose() {
						actionsAdapter.notifyDataSetChanged();
					}
				});
		        f.show(getSupportFragmentManager().beginTransaction(), "dialog");
			}
		});

        createListActions(view);
	}

	private void createSwitchOptions(final ScenarioSwitch scenario, View view) {
        mFrameOptions = (ViewGroup)findViewById(R.id.frame_options);
        mFrameOptions.removeAllViews();
        mFrameOptions.addView(view);

        View btDevice = findViewById(R.id.bt_device);
        btDevice.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DeviceSelectDialog f = new DeviceSelectDialog();
		        f.show(getSupportFragmentManager().beginTransaction(), "dialog");
		        f.setOnCloseListener(new OnCloseListener() {
					@Override
					public void onClose() {
						if (f.hasBeenActivated()) {
							scenario.setDevice(f.getDevice());
							mFeature.commit();
						}
					}
				});
			}
		});

        createListActions(view);
	}

	/**
	 * List all options for scenario, used by switch and custom scenario feature
	 * 
	 * @param view
	 */
	private void createListActions(View view) {
        // "Actions" ListView
        listAction = (ListView)view.findViewById(R.id.list_action);
        actionsAdapter = new ScenarioAdapter(mFeature);
        listAction.setAdapter(actionsAdapter);
        listAction.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				ScenarioEditDialog f = ScenarioEditDialog.newInstance(mRoom, mFeature, pos);
				f.setOnCloseListener(new ScenarioEditDialog.OnCloseListener() {
					@Override
					public void onClose() {
						actionsAdapter.notifyDataSetChanged();
					}
				});
		        f.show(getSupportFragmentManager().beginTransaction(), "dialog");
			}
		});
	}

	protected void refresh() {
        btSwitch.setBackgroundResource(mFeature.isType(FeatureModel.SCENE_SWITCH) ? R.drawable.button_selected : R.drawable.button_resting);
        btScenario.setBackgroundResource(mFeature.isType(FeatureModel.SCENE_SCENARIO) ? R.drawable.button_selected : R.drawable.button_resting);
        btTemp.setBackgroundResource(mFeature.isType(FeatureModel.SCENE_TEMPERATURE) ? R.drawable.button_selected : R.drawable.button_resting);
        
        createFromType(mFeature.getType());

        if (actionsAdapter != null) {
            actionsAdapter.notifyDataSetChanged();
        }
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.feature, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.action_save_feature: {
        	mFeature.mName = editSceneName.getText().toString();
        	mFeature.commit();
        }
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}

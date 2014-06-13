package com.bluebox.james.activity;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import com.bluebox.james.service.DoomService;

public class FeatureEditActivity extends FragmentActivity {
	private FeatureBaseModel mFeature;
	private RoomModel mRoom;
	private EditText editSceneName;
	private View btSwitch;
	private View btTemp;
	private View btScenario;
	private ListView listAction;
	private ScenarioAdapter actionsAdapter;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_feature);

        Bundle bundle = getIntent().getExtras();
        mRoom = DoomService.getInstance().getRoom(bundle.getLong(Application.ARG_ROOM_ID));
        mFeature = mRoom.getFeature(bundle.getLong(Application.ARG_FEATURE_ID));

//        final View overlay = findViewById(R.id.bt_overlay);
        //overlay.setLayoutParams(lp);
//        OnClickListener btTypeListener = new OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				final FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams)overlay.getLayoutParams();
//				lp.width = view.getWidth();
//				Integer fromPos = overlay.getLeft();
////				Integer colorFrom = mAnimFromScenario.getColor();
////				Integer colorTo = mAnimToScenario.getColor();
//				Integer toPos = view.getLeft();
//				ValueAnimator colorAnimation = ValueAnimator.ofObject(new IntEvaluator(), fromPos, toPos);
//				colorAnimation.addUpdateListener(new AnimatorUpdateListener() {
//				    @Override
//				    public void onAnimationUpdate(ValueAnimator animator) {
//				    	//((FrameLayout.LayoutParams)overlay.getLayoutParams()).leftMargin = (Integer)animator.getAnimatedValue();
////				    	overlay.setPadding((Integer)animator.getAnimatedValue(), 0, 0, 0);
//				    	lp.leftMargin = (Integer)animator.getAnimatedValue();
//				    	overlay.setLayoutParams(lp);
//				    }
//				});
//				colorAnimation.start();
//			}
//		};
        
        btSwitch = findViewById(R.id.bt_type_switch);
        btTemp = findViewById(R.id.bt_type_temp);
        btScenario = findViewById(R.id.bt_type_scenario);
        btSwitch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mFeature.setType(FeatureBaseModel.SCENE_SWITCH).commit();
				refresh();
			}
		});
        btTemp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mFeature.setType(FeatureBaseModel.SCENE_TEMPERATURE).commit();
				refresh();
			}
		});
        btScenario.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mFeature.setType(FeatureBaseModel.SCENE_SCENARIO).commit();
				refresh();
			}
		});
        
        // "Scenario name" EditText
        editSceneName = (EditText)findViewById(R.id.edit_scene_name);
        editSceneName.setText(mFeature.getName());

        // "Feature name" Label
        TextView lbFeature = (TextView)findViewById(R.id.lb_feature);
        lbFeature.setText(mFeature.getTypeName());

        // "Actions" ListView
        listAction = (ListView)findViewById(R.id.list_action);
        actionsAdapter = new ScenarioAdapter(mFeature);
        listAction.setAdapter(actionsAdapter);
        listAction.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				ScenarioModel action = mFeature.getScenarios().get(pos);
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
				args.putLong(Application.ARG_ROOM_ID, mRoom.getId());
				args.putLong(Application.ARG_FEATURE_ID, mFeature.getId());
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
		        args.putLong(Application.ARG_FEATURE_ID, mFeature.getId());
		        f.setArguments(args);
		        f.show(getFragmentManager().beginTransaction(), "dialog");
			}
		});
        
        // Action on "select color" button
		findViewById(R.id.bt_scene_color).setBackgroundColor(mFeature.getColor());
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

	protected void refresh() {
        btSwitch.setBackgroundResource(mFeature.isType(FeatureBaseModel.SCENE_SWITCH) ? R.drawable.button_selected : R.drawable.button_resting);
        btScenario.setBackgroundResource(mFeature.isType(FeatureBaseModel.SCENE_SCENARIO) ? R.drawable.button_selected : R.drawable.button_resting);
        btTemp.setBackgroundResource(mFeature.isType(FeatureBaseModel.SCENE_TEMPERATURE) ? R.drawable.button_selected : R.drawable.button_resting);
        actionsAdapter.notifyDataSetChanged();
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

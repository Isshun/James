package com.bluebox.james.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluebox.james.R;
import com.bluebox.james.activity.FeatureEditActivity;
import com.bluebox.james.activity.TemperatureSceneActivity;
import com.bluebox.james.adapter.ScenarioAdapter;
import com.bluebox.james.model.ScenarioModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.FeatureBaseModel;
import com.bluebox.james.service.RoomService;

public class RoomFragment extends Fragment {

    public static final String ARG_ROOM_ID = "room_id";
    public static final String ARG_FEATURE_ID = "feature_id";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final RoomModel room = RoomService.getInstance().getRoom(getArguments().getLong(ARG_ROOM_ID));
    	final View rootView = inflater.inflate(R.layout.fragment_room, container, false);

        ((TextView)rootView.findViewById(R.id.room_name)).setText(room.getName());
        ((ImageView)rootView.findViewById(R.id.room_img)).setImageResource(room.getImgBackground());
    	
        GridView grid = (GridView) rootView.findViewById(R.id.grid_actions);
        
        // Item click
        grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				FeatureBaseModel scene = room.getFeatures().get(pos);
				clickOnTile(view, scene);
			}
		});
        
        // Item long click
        grid.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
				FeatureBaseModel feature = room.getFeatures().get(pos);
				Intent intent = new Intent(RoomFragment.this.getActivity(), FeatureEditActivity.class);
				intent.putExtra(ARG_ROOM_ID, room.getId());
				intent.putExtra(ARG_FEATURE_ID, feature.getId());
				startActivity(intent);
				return true;
			}
		});
        
//        // Item sub click
//        adapter.setOnSubItemClickListener(new OnSubItemClickListener() {
//			@Override
//			public void onSubItemClick(AdapterView<?> parent, View view, View sub, int pos, long id) {
//				SceneModel scene = room.getScenes().get(pos);
//			}
//		});
        ScenarioAdapter adapter = new ScenarioAdapter(room);
        grid.setAdapter(adapter);
        
        return rootView;
    }

	protected void clickOnTile(View view, FeatureBaseModel scene) {
		switch (scene.getType()) {
		case FeatureBaseModel.SCENE_TEMPERATURE:
			Intent intent = new Intent(getActivity(), TemperatureSceneActivity.class);
			startActivity(intent);
			break;
		default:
			nextAction(view, scene);
			break;
		}
	}

	protected void nextAction(View view, FeatureBaseModel scene) {
		ScenarioModel scenario = scene.nextScenario();
		
		if (scenario != null) {
			RoomService.execute(scenario);
			view.findViewById(R.id.frame_scene).setBackgroundResource(scenario.getIcon());
			((TextView)view.findViewById(R.id.lb_scene)).setText(scenario.getName());
		}
	}

	private void move(View view, int x) {
        view.animate()
        	.translationX(x)
        	.setDuration(250);
	}

	private void crossfade(final View from, final View to) {
    	to.setAlpha(0f);
        to.animate()
        	.alpha(1f)
        	.setDuration(250)
        	.setListener(null);
        to.setVisibility(View.VISIBLE);

        from.animate()
        	.alpha(0f)
        	.setDuration(250)
        	.setListener(new AnimatorListenerAdapter() {
        		@Override
        		public void onAnimationEnd(Animator animation) {
        			from.setVisibility(View.GONE);
        		}
        	});
    }
}

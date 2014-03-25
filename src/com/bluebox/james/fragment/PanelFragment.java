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

import com.bluebox.james.R;
import com.bluebox.james.activity.FeatureEditActivity;
import com.bluebox.james.activity.TemperatureSceneActivity;
import com.bluebox.james.adapter.OnSubItemClickListener;
import com.bluebox.james.adapter.ScenarioAdapter;
import com.bluebox.james.model.ScenarioModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.SceneLightModel;
import com.bluebox.james.model.SceneTemperatureModel;
import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.service.RoomService;

public class PanelFragment extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";

    public PanelFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final RoomModel room = RoomService.getInstance().getRoom(getArguments().getInt(ARG_SECTION_NUMBER));
    	final View rootView = inflater.inflate(R.layout.fragment_room, container, false);

        GridView grid = (GridView) rootView.findViewById(R.id.grid_actions);
        
        // Item click
        grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				FeatureModel scene = room.getScenes().get(pos);
				clickOnTile(view, scene);
			}
		});
        
        // Item long click
        grid.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
				FeatureModel scene = room.getScenes().get(pos);
				Intent intent = new Intent(PanelFragment.this.getActivity(), FeatureEditActivity.class);
				intent.putExtra("room_id", room.getId());
				intent.putExtra("scene_id", scene.getId());
				startActivity(intent);
				return false;
			}
		});
        
        // Item sub click
        ScenarioAdapter adapter = new ScenarioAdapter(room);
        adapter.setOnSubItemClickListener(new OnSubItemClickListener() {
			@Override
			public void onSubItemClick(AdapterView<?> parent, View view, View sub, int pos, long id) {
				FeatureModel scene = room.getScenes().get(pos);
			}
		});
        grid.setAdapter(adapter);
        
        return rootView;
    }
    
	protected void clickOnTile(View view, FeatureModel scene) {
		switch (scene.getType()) {
		case FeatureModel.SCENE_UNKNOW:
			toogleScene(view, scene);
			break;
		case FeatureModel.SCENE_LIGHT:
			toogleSceneLight(view, (SceneLightModel)scene);
			break;
		case FeatureModel.SCENE_TEMPERATURE:
			Intent intent = new Intent(getActivity(), TemperatureSceneActivity.class);
			startActivity(intent);
//			toogleSceneTemperature(view, (SceneTemperatureModel)scene);
			break;
		}
	}

	protected void toogleSceneTemperature(View view, SceneTemperatureModel scene) {
//		View hot = view.findViewById(R.id.img_hot);
//		View cold = view.findViewById(R.id.img_cold);
//    	
//        hot.animate()
//        	.alpha(0.5f)
//        	.setDuration(250)
//        	.setListener(null);
	}

	protected void toogleSceneLight(View view, SceneLightModel scene) {
//		View on = view.findViewById(R.id.img_on);
//		View off = view.findViewById(R.id.img_off);
//
//		ActionModel action = scene.nextAction();
//		RoomService.execute(action);
//		
//		if (action.getName() != null) {
//			((TextView)view.findViewById(R.id.lb_scene)).setText(action.getName());
//		}
//		
//		switch (action.getIcon()) {
//		case 0:
//			view.findViewById(R.id.frame_scene).setBackgroundResource(R.drawable.bg_tile_off);
//			crossfade(on, off);
//			break;
//		default:
//			view.findViewById(R.id.frame_scene).setBackgroundResource(R.drawable.bg_tile_on);
//			crossfade(off, on);
//			break;
//		}
	}

	protected void toogleScene(View view, FeatureModel scene) {
		View frame = view.findViewById(R.id.frame_scene);

		ScenarioModel action = scene.nextAction();
		RoomService.execute(action);
		
		frame.setBackgroundResource(action.getIcon());
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
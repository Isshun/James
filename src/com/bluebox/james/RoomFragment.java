package com.bluebox.james;

import com.bluebox.james.adapter.OnSubItemClickListener;
import com.bluebox.james.adapter.RoomSceneAdapter;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.SceneLightModel;
import com.bluebox.james.model.SceneModel;
import com.bluebox.james.model.SceneTemperatureModel;
import com.bluebox.james.service.RoomService;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

public class RoomFragment extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";

    public RoomFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final RoomModel room = RoomService.getInstance().getRoomList().get(getArguments().getInt(ARG_SECTION_NUMBER));
    	final View rootView = inflater.inflate(R.layout.fragment_room, container, false);

        GridView grid = (GridView) rootView.findViewById(R.id.grid_room);
        grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				SceneModel scene = room.getScenes().get(pos);
				switch (view.getId()) {
				case R.id.bt_up:
					increaseTemperature(view, (SceneTemperatureModel)scene);
				case R.id.bt_down:
					decreaseTemperature(view, (SceneTemperatureModel)scene);
				default:
					clickOnTile(view, scene);
				}
			}
		});
        RoomSceneAdapter adapter = new RoomSceneAdapter(room);
        adapter.setOnSubItemClickListener(new OnSubItemClickListener() {
			@Override
			public void onSubItemClick(AdapterView<?> parent, View view, View sub, int pos, long id) {
				SceneModel scene = room.getScenes().get(pos);
				switch (sub.getId()) {
				case R.id.bt_up:
					increaseTemperature(view, (SceneTemperatureModel)scene);
					break;
				case R.id.bt_down:
					decreaseTemperature(view, (SceneTemperatureModel)scene);
					break;
				}
			}
		});
        grid.setAdapter(adapter);
        
        return rootView;
    }

	protected void increaseTemperature(View view, SceneTemperatureModel scene) {
		View hot = view.findViewById(R.id.frame_temp_hot);
		TextView temp = (TextView)view.findViewById(R.id.lb_temp);

		view.findViewById(R.id.frame_temp).getLayoutParams().height += 5;
		
		scene.increase();
		temp.setText("réglé: " + scene.getExpected() + ", actuel: " + scene.getCurent());

		float alpha = Math.max(0f, Math.min(1f, 0.5f - scene.getDifference() / 5f));
    	
		Log.d("alpha", "alpha: " + alpha);
		Log.d("alpha", "diff: " + scene.getDifference());
		
        hot.animate()
        	.alpha(alpha)
        	.setDuration(250)
        	.setListener(null);
	}
    
    protected void decreaseTemperature(View view, SceneTemperatureModel scene) {
		View hot = view.findViewById(R.id.frame_temp_hot);
		TextView temp = (TextView)view.findViewById(R.id.lb_temp);

		view.findViewById(R.id.frame_temp).getLayoutParams().height -= 5;

		scene.decrease();
		temp.setText("réglé: " + scene.getExpected() + ", actuel: " + scene.getCurent());
		
		float alpha = Math.max(0f, Math.min(1f, 0.5f - scene.getDifference() / 5f));
		
		Log.d("alpha", "alpha: " + alpha);

		hot.animate()
    	.alpha(alpha)
    	.setDuration(250)
    	.setListener(null);
	}

	protected void clickOnTile(View view, SceneModel scene) {
		switch (scene.getType()) {
		case SceneModel.SCENE_UNKNOW:
			toogleScene(view, scene);
			break;
		case SceneModel.SCENE_LIGHT:
			toogleSceneLight(view, (SceneLightModel)scene);
			break;
		case SceneModel.SCENE_TEMPERATURE:
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
		View on = view.findViewById(R.id.img_on);
		View off = view.findViewById(R.id.img_off);

		SceneAction action = scene.nextAction();
		RoomService.execute(action);
		
		switch (action.value) {
		case 0:
			view.findViewById(R.id.frame_scene).setBackgroundResource(R.drawable.bg_tile_off);
			crossfade(on, off);
			break;
		default:
			view.findViewById(R.id.frame_scene).setBackgroundResource(R.drawable.bg_tile_on);
			crossfade(off, on);
			break;
		}
	}

	protected void toogleScene(View view, SceneModel scene) {
		// TODO Auto-generated method stub
		
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

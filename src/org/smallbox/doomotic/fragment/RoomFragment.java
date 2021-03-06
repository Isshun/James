package org.smallbox.doomotic.fragment;

import org.smallbox.doomotic.activity.FeatureEditActivity;
import org.smallbox.doomotic.activity.TemperatureSceneActivity;
import org.smallbox.doomotic.adapter.RoomFeatureAdapter;
import org.smallbox.doomotic.adapter.OnScenarioClickListener;
import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.model.RoomModel;
import org.smallbox.doomotic.model.ScenarioOptionModel;
import org.smallbox.doomotic.service.DoomService;

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

public class RoomFragment extends Fragment {
    public static final String ARG_ROOM_ID = "room_id";
    public static final String ARG_FEATURE_ID = "feature_id";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final RoomModel room = DoomService.getInstance().getRoom(getArguments().getInt(ARG_ROOM_ID));
    	final View rootView = inflater.inflate(R.layout.fragment_room, container, false);

        ((TextView)rootView.findViewById(R.id.room_name)).setText(room.getName());
        ((ImageView)rootView.findViewById(R.id.room_img)).setImageResource(room.getImgBackground());
    	
        final RoomFeatureAdapter adapter = new RoomFeatureAdapter(room);

        final GridView grid = (GridView) rootView.findViewById(R.id.grid_actions);
        grid.setAdapter(adapter);
        
        // Item click
        grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				FeatureModel scene = room.getFeatures().get(pos);
				ScenarioOptionModel fromScenario = scene.getCurrentOption();
				clickOnTile(view, scene);
				ScenarioOptionModel toScenario = scene.getCurrentOption();
				adapter.startAnim(fromScenario, toScenario);
				adapter.notifyDataSetChanged();
			}
		});
        
        // Item long click
        grid.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
				FeatureModel feature = room.getFeatures().get(pos);
				Intent intent = new Intent(RoomFragment.this.getActivity(), FeatureEditActivity.class);
				intent.putExtra(ARG_ROOM_ID, room.getId());
				intent.putExtra(ARG_FEATURE_ID, feature.getId());
				startActivity(intent);
				return true;
			}
		});
        
        // Item sub click
        adapter.setOnScenarioClickListener(new OnScenarioClickListener() {
			@Override
			public void onScenarioClick(FeatureModel feature, ScenarioOptionModel scenario) {
				adapter.startAnim(feature.getCurrentOption(), scenario);
				feature.setScenario(scenario);
				DoomService.execute(scenario);
				adapter.notifyDataSetChanged();
			}
		});

        return rootView;
    }
    
	protected void clickOnTile(View view, FeatureModel scene) {
		switch (scene.getType()) {
		case FeatureModel.SCENE_TEMPERATURE:
			Intent intent = new Intent(getActivity(), TemperatureSceneActivity.class);
			startActivity(intent);
			break;
		default:
			nextAction(view, scene);
			break;
		}
	}

	protected void nextAction(View view, FeatureModel scene) {
		ScenarioOptionModel scenario = scene.nextScenario();
		
		if (scenario != null) {
			DoomService.execute(scenario);
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

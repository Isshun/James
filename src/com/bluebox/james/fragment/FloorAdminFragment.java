package com.bluebox.james.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluebox.james.R;
import com.bluebox.james.activity.TemperatureSceneActivity;
import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.FloorModel;
import com.bluebox.james.model.ScenarioOptionModel;
import com.bluebox.james.service.DoomService;

public class FloorAdminFragment extends Fragment {

    public static final String ARG_FLOOR_ID = "floor_id";
	private FloorModel mFloor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFloor = DoomService.getInstance().getFloor(getArguments().getLong(ARG_FLOOR_ID));

        final View view = inflater.inflate(R.layout.fragment_admin_floor, container, false);
    	((TextView)view.findViewById(R.id.lb_floor)).setText(mFloor.getLabel());
    	
        return view;
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

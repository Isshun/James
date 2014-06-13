package com.bluebox.james.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluebox.james.R;
import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.ScenarioModel;

public class ScenarioAdapter extends BaseAdapter {

	private FeatureModel mScene;

	public ScenarioAdapter(FeatureModel feature) {
		mScene = feature;
	}
	
	@Override
	public int getCount() {
		return mScene.getScenarios().size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_entry_scenario, null);
		ScenarioModel scenario = mScene.getScenarios().get(pos);
		
		TextView lbScene = (TextView)view.findViewById(R.id.lb_scene);
		lbScene.setText(scenario.getLabel());
		
		if (scenario.getIcon() != -1) {
			ImageView imgIcon = (ImageView)view.findViewById(R.id.img_icon);
			imgIcon.setImageResource(scenario.getIcon());
		}
		
		return view;
	}

}

package com.bluebox.james.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bluebox.james.R;
import com.bluebox.james.model.ScenarioModel;
import com.bluebox.james.model.FeatureModel;

public class ActionAdapter extends BaseAdapter {

	private FeatureModel mScene;

	public ActionAdapter(FeatureModel scene) {
		mScene = scene;
	}
	
	@Override
	public int getCount() {
		return mScene.getActions().size();
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
		view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_scene_action, null);
		
		ScenarioModel action = mScene.getActions().get(pos);
		
		TextView lbScene = (TextView)view.findViewById(R.id.lb_scene);
		lbScene.setText(action.getName());
		
		return view;
	}

}
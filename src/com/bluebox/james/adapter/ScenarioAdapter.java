package com.bluebox.james.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluebox.james.R;
import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.ScenarioOptionModel;

public class ScenarioAdapter extends BaseAdapter {

	private FeatureModel mFeature;

	public ScenarioAdapter(FeatureModel feature) {
		mFeature = feature;
	}
	
	@Override
	public int getCount() {
		return mFeature.getOptions().size();
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
		ScenarioOptionModel option = mFeature.getOptions().get(pos);
		
		TextView lbScene = (TextView)view.findViewById(R.id.lb_scene);
		lbScene.setText(option.getLabel());
		
		if (option.getIcon() != -1) {
			ImageView imgIcon = (ImageView)view.findViewById(R.id.img_icon);
			imgIcon.setImageResource(option.getIcon());
		}
		
		return view;
	}

}

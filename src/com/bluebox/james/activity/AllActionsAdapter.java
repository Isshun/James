package com.bluebox.james.activity;

import com.bluebox.james.R;
import com.bluebox.james.adapter.SceneActionAdapter;
import com.bluebox.james.model.ActionModel;
import com.bluebox.james.model.featureModel;
import com.bluebox.james.service.RoomService;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AllActionsAdapter extends BaseAdapter  {


//	private SceneModel mScene;

//	public AllActionsAdapter(SceneModel scene) {
//		mScene = scene;
//	}
	
	@Override
	public int getCount() {
		return RoomService.getInstance().getAllActions().size();
	}

	@Override
	public Object getItem(int pos) {
		return RoomService.getInstance().getAllActions().get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return RoomService.getInstance().getAllActions().get(pos).getId();
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_scene_action, null);
		
		ActionModel action = RoomService.getInstance().getAllActions().get(pos);
		
		TextView lbScene = (TextView)view.findViewById(R.id.lb_scene);
		lbScene.setText(action.getName());
		
		return view;
	}

}

//package com.bluebox.james.adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.bluebox.james.R;
//import com.bluebox.james.model.ScenarioOptionModel;
//import com.bluebox.james.service.DoomService;
//
//public class AllActionsAdapter extends BaseAdapter  {
//
//
////	private SceneModel mScene;
//
////	public AllActionsAdapter(SceneModel scene) {
////		mScene = scene;
////	}
//	
//	@Override
//	public int getCount() {
//		return DoomService.getInstance().getAllActions().size();
//	}
//
//	@Override
//	public Object getItem(int pos) {
//		return DoomService.getInstance().getAllActions().get(pos);
//	}
//
//	@Override
//	public long getItemId(int pos) {
//		return DoomService.getInstance().getAllActions().get(pos).getId();
//	}
//
//	@Override
//	public View getView(int pos, View view, ViewGroup parent) {
//		view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_entry_scenario, null);
//		
//		ScenarioOptionModel action = DoomService.getInstance().getAllActions().get(pos);
//		
//		TextView lbScene = (TextView)view.findViewById(R.id.lb_scene);
//		lbScene.setText(action.getLabel());
//		
//		return view;
//	}
//
//}

package com.bluebox.james.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bluebox.james.R;
import com.bluebox.james.model.ActionModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.FeatureModel;

public class ScenarioAdapter extends BaseAdapter {

	private RoomModel mRoom;
	private OnSubItemClickListener mSubItemClickListener;

	public ScenarioAdapter(RoomModel room) {
		mRoom = room;
	}

	@Override
	public int getCount() {
		return mRoom.getScenes().size();
	}

	@Override
	public Object getItem(int pos) {
		return mRoom.getScenes().get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return mRoom.getScenes().get(pos).getId();
	}

	@Override
	public int getItemViewType(int pos) {
		return mRoom.getScenes().get(pos).getType();
	}

	@Override
	public int getViewTypeCount() {
		return FeatureModel.SCENE_COUNT;
	}

	@Override
	public View getView(final int pos, View view, ViewGroup viewgroup) {
		final FeatureModel scene = mRoom.getScenes().get(pos);
		
		view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.view_room_scene, null);
//		switch (scene.getType()) {
//		case SceneModel.SCENE_UNKNOW:
//			break;
//		case SceneModel.SCENE_LIGHT:
//			view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.view_room_scene_light, null);
////			if (scene.isOn()) {
////				view.findViewById(R.id.img_on).setVisibility(View.VISIBLE);
////				view.findViewById(R.id.img_off).setVisibility(View.GONE);
////				view.findViewById(R.id.frame_scene).setBackgroundResource(R.drawable.bg_tile_on);
////			} else {
////				view.findViewById(R.id.img_on).setVisibility(View.GONE);
////				view.findViewById(R.id.img_off).setVisibility(View.VISIBLE);
////				view.findViewById(R.id.frame_scene).setBackgroundResource(R.drawable.bg_tile_off);
////			}
//			break;
//		case SceneModel.SCENE_TEMPERATURE:
//			view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.view_room_scene_temperature, null);
//			final View fView = view;
//			view.findViewById(R.id.bt_up).setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					if (mSubItemClickListener != null) {
//						mSubItemClickListener.onSubItemClick(null, fView, v, pos, scene.getId());
//					}
//				}
//			});
//			view.findViewById(R.id.bt_down).setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					if (mSubItemClickListener != null) {
//						mSubItemClickListener.onSubItemClick(null, fView, v, pos, scene.getId());
//					}
//				}
//			});
//			break;
//		}
		
		((TextView)view.findViewById(R.id.lb_name)).setText(scene.getName());

		ActionModel action = scene.getAction();
		if (action != null) {
			view.findViewById(R.id.frame_scene).setBackgroundResource(action.getIcon());
			((TextView)view.findViewById(R.id.lb_scene)).setText(action.getName());
			if (action.getColor() != -1) {
				((TextView)view.findViewById(R.id.lb_scene)).setTextColor(action.getColor());
			}
		} else {
			view.findViewById(R.id.frame_scene).setBackgroundResource(scene.getIcon());
			((TextView)view.findViewById(R.id.lb_scene)).setText(scene.getName());
		}
		
		return view;
	}

	public void setOnSubItemClickListener(OnSubItemClickListener subItemClickListener) {
		mSubItemClickListener = subItemClickListener;
	}

}

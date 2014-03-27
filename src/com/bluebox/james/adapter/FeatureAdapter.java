package com.bluebox.james.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bluebox.james.R;
import com.bluebox.james.model.ScenarioModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.FeatureBaseModel;

public class FeatureAdapter extends BaseAdapter {

	private RoomModel mRoom;
	private OnSubItemClickListener mSubItemClickListener;

	public FeatureAdapter(RoomModel room) {
		mRoom = room;
	}

	@Override
	public int getCount() {
		return mRoom.getFeatures().size();
	}

	@Override
	public Object getItem(int pos) {
		return mRoom.getFeatures().get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return mRoom.getFeatures().get(pos).getId();
	}

	@Override
	public int getItemViewType(int pos) {
		return mRoom.getFeatures().get(pos).getType();
	}

	@Override
	public int getViewTypeCount() {
		return FeatureBaseModel.SCENE_COUNT;
	}

	@Override
	public View getView(final int pos, View view, ViewGroup viewgroup) {
		final FeatureBaseModel feature = mRoom.getFeatures().get(pos);
		
		view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.list_entry_feature, null);
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
		
		((TextView)view.findViewById(R.id.lb_name)).setText(feature.getName());

		ScenarioModel scenario = feature.getScenario();
		if (scenario != null) {
			view.findViewById(R.id.frame_scene).setBackgroundColor(scenario.getColor());
			((TextView)view.findViewById(R.id.lb_scene)).setText(scenario.getName());
			if (scenario.getColor() != -1) {
				((TextView)view.findViewById(R.id.lb_scene)).setTextColor(scenario.getColor());
			}
		} else {
			view.findViewById(R.id.frame_scene).setBackgroundColor(feature.getColor());
			((TextView)view.findViewById(R.id.lb_scene)).setText(feature.getName());
		}
		
		return view;
	}

	public void setOnSubItemClickListener(OnSubItemClickListener subItemClickListener) {
		mSubItemClickListener = subItemClickListener;
	}

}

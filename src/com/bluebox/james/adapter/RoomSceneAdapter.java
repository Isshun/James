package com.bluebox.james.adapter;

import com.bluebox.james.R;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.SceneLightModel;
import com.bluebox.james.model.SceneModel;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class RoomSceneAdapter extends BaseAdapter {

	private RoomModel mRoom;
	private OnSubItemClickListener mSubItemClickListener;

	public RoomSceneAdapter(RoomModel room) {
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
		return SceneModel.SCENE_COUNT;
	}

	@Override
	public View getView(final int pos, View view, ViewGroup viewgroup) {
		final SceneModel scene = mRoom.getScenes().get(pos);
		
		switch (scene.getType()) {
		case SceneModel.SCENE_UNKNOW:
			view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.view_room_scene, null);
			break;
		case SceneModel.SCENE_LIGHT:
			view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.view_room_scene_light, null);
			if (scene.isOn()) {
				view.findViewById(R.id.img_on).setVisibility(View.VISIBLE);
				view.findViewById(R.id.img_off).setVisibility(View.GONE);
				view.findViewById(R.id.frame_scene).setBackgroundResource(R.drawable.bg_tile_on);
			} else {
				view.findViewById(R.id.img_on).setVisibility(View.GONE);
				view.findViewById(R.id.img_off).setVisibility(View.VISIBLE);
				view.findViewById(R.id.frame_scene).setBackgroundResource(R.drawable.bg_tile_off);
			}
			break;
		case SceneModel.SCENE_TEMPERATURE:
			view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.view_room_scene_temperature, null);
			final View fView = view;
			view.findViewById(R.id.bt_up).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mSubItemClickListener != null) {
						mSubItemClickListener.onSubItemClick(null, fView, v, pos, scene.getId());
					}
				}
			});
			view.findViewById(R.id.bt_down).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mSubItemClickListener != null) {
						mSubItemClickListener.onSubItemClick(null, fView, v, pos, scene.getId());
					}
				}
			});
			break;
		}
		
		((TextView)view.findViewById(R.id.lb_scene)).setText(scene.getName());
		
		return view;
	}

	public void setOnSubItemClickListener(OnSubItemClickListener subItemClickListener) {
		mSubItemClickListener = subItemClickListener;
	}

}

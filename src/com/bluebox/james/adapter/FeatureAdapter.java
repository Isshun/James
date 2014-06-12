package com.bluebox.james.adapter;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluebox.james.R;
import com.bluebox.james.model.FeatureBaseModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.ScenarioModel;

public class FeatureAdapter extends BaseAdapter {

	private RoomModel mRoom;
	private OnScenarioClickListener mScenarioClickListener;

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
		final ScenarioModel currentScenario = feature.getScenario();

		int color = Color.WHITE;
		if (currentScenario != null && currentScenario.getColor() != -1) {
			color = currentScenario.getColor();
		} else if (feature.getColor() != -1) {
			color = feature.getColor();
		}
		
		int icon = -1;
		if (currentScenario != null && currentScenario.getIcon() != -1) {
			icon = currentScenario.getIcon();
		} else if (feature.getIcon() != -1) {
			icon = feature.getIcon();
		}
		
		view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.list_entry_feature, null);
		view.findViewById(R.id.frame_scene).setBackgroundColor(color);
		if (icon != -1) {
			((ImageView)view.findViewById(R.id.img_icon)).setImageResource(icon);
		} else {
			((ImageView)view.findViewById(R.id.img_icon)).setImageDrawable(null);
		}
		((TextView)view.findViewById(R.id.lb_name)).setText(feature.getName());
		
		if (feature.isType(FeatureBaseModel.SCENE_TEMPERATURE)) {
			((TextView)view.findViewById(R.id.lb_value)).setText("23°");
		}
		
		// Options layout
		final LinearLayout optionsLayout = (LinearLayout)view.findViewById(R.id.list_entry_options);
		optionsLayout.setBackgroundColor(Color.argb(100, 255, 255, 255));
		int i = 0;
		for (ScenarioModel scenario: feature.getScenarios()) {
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			lp.weight = 1;
			lp.topMargin = 6;
			if (i++ > 0) {
				lp.leftMargin = 6;
			}
			final ScenarioModel s = scenario;
			final TextView lbOption = new TextView(view.getContext());
			lbOption.setText(scenario.getName());
			lbOption.setTextSize(20);
			lbOption.setTextColor(Color.WHITE);
			lbOption.setGravity(Gravity.CENTER);
			lbOption.setLayoutParams(lp);
			lbOption.setPadding(0, 0, 0, 6);
			if (scenario != currentScenario) {
				lbOption.setBackgroundColor(color);
			}
			lbOption.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mScenarioClickListener.onScenarioClick(feature, s);					
				}
			});
			optionsLayout.addView(lbOption);
		}
		
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
		
		return view;
	}

	public void setOnScenarioClickListener(OnScenarioClickListener scenarioClickListener) {
		mScenarioClickListener = scenarioClickListener;
	}

}

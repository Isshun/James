package com.bluebox.james.adapter;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluebox.james.R;
import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.ScenarioOptionModel;

public class FeatureAdapter extends BaseAdapter {
	private static class ViewHolder {
		public TextView		mName;
		public ImageView 	mIcon;
		public LinearLayout mOptions;
		public View 		mLayout;
		public TextView 	mValue;
	}
	
	private RoomModel 				mRoom;
	private OnScenarioClickListener mScenarioClickListener;
	private ScenarioOptionModel 			mAnimFromScenario;
	private ScenarioOptionModel 			mAnimToScenario;

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
		return FeatureModel.SCENE_COUNT;
	}

	@Override
	public View getView(final int pos, View view, ViewGroup viewgroup) {
		final FeatureModel feature = mRoom.getFeatures().get(pos);
		final ScenarioOptionModel currentScenario = feature.getScenario();
		ViewHolder holder = null;
		
		if (view == null) {
			view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.list_entry_feature, null);
			holder = new ViewHolder();
			holder.mLayout = view.findViewById(R.id.frame_scene);
			holder.mName = ((TextView)view.findViewById(R.id.lb_name));
			holder.mIcon = ((ImageView)view.findViewById(R.id.img_icon));
			holder.mValue = ((TextView)view.findViewById(R.id.lb_value));
			holder.mOptions = (LinearLayout)view.findViewById(R.id.list_entry_options);
			view.setTag(holder);
		} else {
			holder = (ViewHolder)view.getTag();
		}
		
		final int color = currentScenario != null ? currentScenario.getColor() : feature.getColor();

		holder.mLayout.setBackgroundColor(color);
		holder.mName.setText(feature.getName());

		// Set icon
		if (currentScenario != null && currentScenario.getIcon() != -1) {
			holder.mIcon.setImageResource(currentScenario.getIcon());
			holder.mIcon.setVisibility(View.VISIBLE);
		} else if (feature.getIcon() != -1) {
			holder.mIcon.setImageResource(feature.getIcon());
			holder.mIcon.setVisibility(View.VISIBLE);
		} else {
			holder.mIcon.setVisibility(View.GONE);
		}
		
		// Feature is temperature probe
		if (feature.isType(FeatureModel.SCENE_TEMPERATURE)) {
//			FeatureTemperatureModel featureTemperature = (FeatureTemperatureModel)feature;
//			holder.mValue.setText(featureTemperature.getExpected() + "°");
//			holder.mIcon.setVisibility(View.GONE);
		}
		
		// Options layout
		holder.mOptions.removeAllViews();
		final ViewHolder h = holder;
		int i = 0;
		for (ScenarioOptionModel scenario: feature.getOptions()) {
			final ScenarioOptionModel finalScenario = scenario;
			final int optionIndex = i;
			
			// Separator
			if (i++ > 0) {
				View separator = new View(view.getContext());
				int width = (int)view.getContext().getResources().getDimension(R.dimen.list_entry_feature_option_separator);
				separator.setLayoutParams(new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.MATCH_PARENT));
				separator.setBackgroundResource(R.color.list_entry_option);
				holder.mOptions.addView(separator);
			}

			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			lp.weight = 1;
			final TextView lbOption = new TextView(view.getContext());
			lbOption.setText(scenario.getLabel());
			lbOption.setTextSize(20);
			lbOption.setTextColor(Color.WHITE);
			lbOption.setGravity(Gravity.CENTER);
			lbOption.setLayoutParams(lp);
			lbOption.setPadding(0, 0, 0, 6);
			if (feature.isToggleButtons() == false || scenario != currentScenario) {
				lbOption.setBackgroundResource(R.drawable.bg_list_entry_selected_option);
				lbOption.setActivated(true);
			} else {
				lbOption.setBackgroundResource(R.drawable.bg_list_entry_resting_option);
				lbOption.setActivated(false);
			}
			
			lbOption.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (finalScenario.getOnClickListener() != null) {
						finalScenario.getOnClickListener().onClick(v);
					}
					mScenarioClickListener.onScenarioClick(feature, finalScenario);
				}
			});
			final Handler handler = new Handler();
			lbOption.setOnLongClickListener(new OnLongClickListener() {
				@Override
				public boolean onLongClick(final View v) {
					handler.post(new Runnable() {
						@Override
						public void run() {
							if (lbOption.isPressed()) {
								if (finalScenario.getOnClickListener() != null) {
									finalScenario.getOnClickListener().onClick(v);
								}
								if (feature.getFormattedValue() != null)
								h.mValue.setText(feature.getFormattedValue());
								handler.postDelayed(this, 250);
							}
						}
					});
					return false;
				}
			});
			holder.mOptions.addView(lbOption);
		}
		
		// Color change animation
		if (mAnimFromScenario != null && mAnimToScenario != null && currentScenario == mAnimToScenario) {
			final ViewHolder finalHolder = holder;
			Integer colorFrom = mAnimFromScenario.getColor();
			Integer colorTo = mAnimToScenario.getColor();
			ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
			colorAnimation.addUpdateListener(new AnimatorUpdateListener() {
			    @Override
			    public void onAnimationUpdate(ValueAnimator animator) {
			        finalHolder.mLayout.setBackgroundColor((Integer)animator.getAnimatedValue());
			    }
			});
			colorAnimation.start();
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

	public void startAnim(ScenarioOptionModel fromScenario, ScenarioOptionModel toScenario) {
		mAnimFromScenario = fromScenario;
		mAnimToScenario = toScenario;
	}

}

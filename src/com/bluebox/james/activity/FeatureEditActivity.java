package com.bluebox.james.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.bluebox.james.R;
import com.bluebox.james.adapter.SceneActionAdapter;
import com.bluebox.james.model.ActionModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.featureModel;
import com.bluebox.james.service.RoomService;

public class FeatureEditActivity extends FragmentActivity {

    private RoomModel 	mRoom;
    private featureModel 	mScene;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_feature);

        Bundle bundle = getIntent().getExtras();
        mRoom = RoomService.getInstance().getRoom(bundle.getLong("room_id"));
        mScene = mRoom.getScene(bundle.getLong("feature_id"));

        // "Scenario name" EditText
        EditText editSceneName = (EditText)findViewById(R.id.edit_scene_name);
        editSceneName.setText(mScene.getName());

        // "Actions" ListView
        final ListView listAction = (ListView)findViewById(R.id.list_action);
        final SceneActionAdapter actionsAdapter = new SceneActionAdapter(mScene);
        listAction.setAdapter(actionsAdapter);
        listAction.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				ActionModel action = mScene.getActions().get(pos);
				Intent intent = new Intent(FeatureEditActivity.this, ScenarioEditActivity.class);
				intent.putExtra("room_id", mRoom.getId());
				intent.putExtra("scene_id", mScene.getId());
				intent.putExtra("action_id", action.getId());
				startActivity(intent);
			}
		});
        
        // Action on "add actions" button
        findViewById(R.id.bt_add_action).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ListActionsDialogFragment f = new ListActionsDialogFragment();
				f.setOnCloseListener(new ListActionsDialogFragment.OnCloseListener() {
					@Override
					public void onClose() {
						actionsAdapter.notifyDataSetChanged();
					}
				});
		    	Bundle args = new Bundle();
		        args.putLong("scene_id", mScene.getId());
		        f.setArguments(args);
		        f.show(getFragmentManager().beginTransaction(), "dialog");
			}
		});
        
        // Action on "select color" button
        findViewById(R.id.bt_scene_color).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		    	DialogFragment f = new ColorDialogFragment();
		        f.show(getFragmentManager().beginTransaction(), "dialog");
			}
		});

        Utils.hideKeyboard(this);
    }

	@Override
    protected void onResume() {
		super.onResume();
		Log.i("gg", "RESUME");
	}
    
    public static class ColorDialogFragment extends DialogFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_dialog_color, container, false);
            return v;
        }
    }

    public static class ListActionsDialogFragment extends DialogFragment {
    	
		private OnCloseListener mOnCloseListener;

    	public static interface OnCloseListener {
    		void onClose();
    	}

        public static ListActionsDialogFragment newInstance(int title) {
        	ListActionsDialogFragment frag = new ListActionsDialogFragment();
            Bundle args = new Bundle();
            args.putInt("title", title);
            frag.setArguments(args);
            return frag;
        }

        public void setOnCloseListener(OnCloseListener onCloseListener) {
        	mOnCloseListener = onCloseListener;
		}

		@Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final long featureId = getArguments().getLong("feature_id");
            final featureModel scene = RoomService.getInstance().getFeature(featureId);
        	final View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_create_scenario, null, false);
        	
        	final EditText editActionName = (EditText)view.findViewById(R.id.edit_scenario_name);

            return new AlertDialog.Builder(getActivity())
                    .setTitle("Add new scenario")
                    .setView(view)
                    .setPositiveButton("Create",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            	RoomService.getInstance().addAction(scene, new ActionModel(editActionName.getText().toString(), R.drawable.ic_alarm));
                            	mOnCloseListener.onClose();
                            }
                        }
                    )
                    .setNegativeButton("Cancel", null)
                    .create();
        }
    }

    
//    public static class ListActionsDialogFragment extends DialogFragment {
//
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            View v = inflater.inflate(R.layout.fragment_dialog_actions, container, false);
//
//            getDialog().setTitle("Add new action");
//            
//            final long sceneId = getArguments().getLong("scene_id");
//            final SceneModel scene = RoomService.getInstance().getScene(sceneId);
//            
////            final ListAdapter adapter = new AllActionsAdapter();
////            ListView listAction = (ListView)v.findViewById(R.id.list_actions);
////            listAction.setAdapter(adapter);
////            listAction.setOnItemClickListener(new OnItemClickListener() {
////				@Override
////				public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
////					ActionModel action = (ActionModel)adapter.getItem(pos);
////					scene.addAction(action);
////					mOnCloseListener.onClose();
////					dismiss();
////				}
////			});
//            
//            return v;
//        }
//
//		public void setOnCloseListener(OnCloseListener onCloseListener) {
//			mOnCloseListener = onCloseListener;
//		}
//
//    }
}

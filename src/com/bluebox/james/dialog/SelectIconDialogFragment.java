package com.bluebox.james.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.bluebox.james.Application;
import com.bluebox.james.R;
import com.bluebox.james.adapter.IconAdapter;
import com.bluebox.james.model.ScenarioModel;
import com.bluebox.james.service.RoomService;

public class SelectIconDialogFragment extends DialogFragment {
	
	private OnCloseListener mOnCloseListener;
	protected Integer mIcon = -1;

	public static interface OnCloseListener {
		void onClose();
	}

    public void setOnCloseListener(OnCloseListener onCloseListener) {
    	mOnCloseListener = onCloseListener;
	}
    
    public int getIcon() {
    	return mIcon;
	}
    
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final ScenarioModel scenario = RoomService.getInstance().getScenario(getArguments().getLong(Application.ARG_SCENARIO_ID));
        final ListAdapter adapter = new IconAdapter();
        final GridView grid = new GridView(getActivity());
		grid.setNumColumns(3);
		grid.setAdapter(adapter);
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				mIcon = (Integer)adapter.getItem(pos);
				mOnCloseListener.onClose();
				dismiss();
			}
		});

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.title_dialog_select_icon)
                .setView(grid)
//                .setPositiveButton("Create",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int whichButton) {
//                        	mOnCloseListener.onClose();
//                        }
//                    }
//                )
                .setNegativeButton("Cancel", null)
                .create();
    }
}
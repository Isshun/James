package com.bluebox.james.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.AdapterView.OnItemClickListener;

import com.bluebox.james.Application;
import com.bluebox.james.R;
import com.bluebox.james.adapter.IconAdapter;
import com.bluebox.james.dialog.SelectIconDialogFragment.OnCloseListener;
import com.bluebox.james.model.ScenarioModel;
import com.bluebox.james.service.RoomService;

public class SelectColorDialogFragment extends DialogFragment {
	
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
    	final View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_create_feature, null, false);
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.title_dialog_select_color)
                .setView(view)
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
//
//public class SelectColorDialogFragment extends DialogFragment {
//
//	protected int mColor = -1;
//	
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_dialog_color, container, false);
//        return v;
//    }
//
//	public int getColor() {
//		return mColor;
//	}
//}


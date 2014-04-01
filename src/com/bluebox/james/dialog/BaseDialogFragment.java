package com.bluebox.james.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public abstract class BaseDialogFragment extends DialogFragment {

	private OnCloseListener	mOnCloseListener;
	private int				mTitle = -1;
	private int 			mNegativeLabel = -1;
	private int 			mPositiveLabel = -1;
	private View 			mView;
	
	protected abstract void onCreateDialog();
	protected abstract void onCancel();
	protected abstract void onSave();

	public static interface OnCloseListener {
		void onClose();
	}

	public void setOnCloseListener(OnCloseListener onCloseListener) {
    	mOnCloseListener = onCloseListener;
	}

	public void setTitle(int title) {
		mTitle = title;
	}

	public void setView(View view) {
		mView = view;
	}

	public void setNegativeButton(int negativeLabel) {
		mNegativeLabel = negativeLabel;
	}
	
	public void setPositiveButton(int positiveLabel) {
		mPositiveLabel = positiveLabel;
	}
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		onCreateDialog();

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		// Title
		if (mTitle != -1) {
			builder.setTitle(mTitle);
		}
		
		// Content view
		if (mView != null) {
            builder.setView(mView);
		}

		// Positive button
		if (mPositiveLabel != -1) {
            builder.setPositiveButton("Save",
            		new DialogInterface.OnClickListener() {
                    	public void onClick(DialogInterface dialog, int whichButton) {
                    		onSave();
                    		if (mOnCloseListener != null) {
                    			mOnCloseListener.onClose();
                    		}
                    	}
                	}
            );
		}
		
		// Negative button
		if (mNegativeLabel != -1) {
            builder.setNegativeButton("Cancel", 
            		new DialogInterface.OnClickListener() {
            			public void onClick(DialogInterface dialog, int whichButton) {
            				onCancel();
            			}
            		}
            );
		}

		return builder.create();
    }

	protected void save() {
		onSave();
		if (mOnCloseListener != null) {
			mOnCloseListener.onClose();
		}
		dismiss();
	}

	protected void cancel() {
		onCancel();
		dismiss();
	}

}

package com.bluebox.james.dialog;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.bluebox.james.R;
import com.bluebox.james.adapter.IconAdapter;

public class SelectIconDialogFragment extends BaseDialogFragment {
	
	protected Integer mIcon = -1;

    public int getIcon() {
    	return mIcon;
	}
    
	@Override
	protected void onCreateDialog() {
        final ListAdapter adapter = new IconAdapter();

        // Cutomize grid
        final GridView grid = new GridView(getActivity());
		grid.setNumColumns(3);
		grid.setAdapter(adapter);
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				mIcon = (Integer)adapter.getItem(pos);
				save();
			}
		});
		
		setTitle(R.string.title_dialog_select_icon);
		setView(grid);
		setNegativeButton(R.string.bt_dialog_cancel);
    }

	@Override
	protected void onCancel() {
	}

	@Override
	protected void onSave() {
	}
}
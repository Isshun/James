package com.bluebox.james.dialog;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.bluebox.james.R;
import com.bluebox.james.adapter.ColorAdapter;

public class SelectColorDialogFragment extends BaseDialogFragment {
	
	protected int mColor = -1;

	@Override
	protected void onCreateDialog() {
        final ListAdapter adapter = new ColorAdapter();
        final GridView grid = new GridView(getActivity());
		grid.setColumnWidth(110);
        grid.setVerticalSpacing(10);
		grid.setNumColumns(GridView.AUTO_FIT);
		grid.setAdapter(adapter);
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				mColor = (Integer)adapter.getItem(pos);
				mHasBeenActivated = true;
				save();
			}
		});
		
		setTitle(R.string.title_dialog_select_color);
		setView(grid);
		setNegativeButton(R.string.bt_dialog_cancel);
    }

	public int getColor() {
		return mColor;
	}

	@Override
	protected void onCancel() {
	}

	@Override
	protected void onSave() {
	}
}

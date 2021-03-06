package org.smallbox.doomotic.dialog;

import org.smallbox.doomotic.adapter.ColorAdapter;
import org.smallbox.lib.dialog.BaseDialogFragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.bluebox.james.R;

public class ColorPickerDialog extends BaseDialogFragment {
	
	protected int mColor = -1;

	@Override
	protected void onCreateDialog() {
        final ListAdapter adapter = new ColorAdapter();
        final GridView grid = new GridView(getActivity());
		grid.setColumnWidth(70);
        grid.setVerticalSpacing(0);
//		grid.setNumColumns(GridView.AUTO_FIT);
		grid.setNumColumns(9);
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
		setContentView(grid);
		setNegativeButton(R.string.bt_dialog_cancel);
    }

	public int getColor() {
		return mColor;
	}

	@Override
	protected void onCancel() {
	}

	@Override
	protected void onConfirm() {
	}


}

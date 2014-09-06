package org.smallbox.doomotic.dialog;

import org.smallbox.doomotic.adapter.IconAdapter;
import org.smallbox.lib.dialog.BaseDialogFragment;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.bluebox.james.R;

public class IconSelectDialog extends BaseDialogFragment {
	protected Integer mIcon = -1;

    public int getIcon() {
    	return mIcon;
	}
    
	@Override
	protected void onCreateDialog() {
		setTitle(R.string.title_dialog_select_icon);
		setNegativeButton(R.string.bt_dialog_cancel);

		final ListAdapter adapter = new IconAdapter();

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
        
        // Cutomize grid
        final GridView grid = new GridView(getActivity());
		grid.setNumColumns(3);
		grid.setAdapter(adapter);
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				mIcon = (Integer)adapter.getItem(pos);
				mHasBeenActivated = true;
				save();
			}
		});
        layout.addView(grid);
        
        // Button remove
        Button btRemove = new Button(getActivity());
        btRemove.setText("Remove");
		btRemove.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mIcon = -1;
				mHasBeenActivated = true;
				save();
			}
		});
        layout.addView(btRemove);
    }

	@Override
	protected void onCancel() {
	}

	@Override
	protected void onConfirm() {
	}
}
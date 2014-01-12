package com.bluebox.james.adapter;

import android.view.View;
import android.widget.AdapterView;

public interface OnSubItemClickListener {
	public void onSubItemClick(AdapterView<?> parent, View view, View sub, int pos, long id);
}

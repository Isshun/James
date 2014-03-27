package com.bluebox.james.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bluebox.james.R;

public class IconAdapter extends BaseAdapter {

	@Override
	public int getCount() {
		return 4;
	}

	@Override
	public Object getItem(int pos) {
		switch (pos) {
		case 0: return R.drawable.ic_light_1;
		case 1: return R.drawable.ic_light_2;
		case 2: return R.drawable.ic_light_3;
		case 3: return R.drawable.ic_temp;
		}
		return -1;
	}

	@Override
	public long getItemId(int pos) {
		return 0;
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		ImageView image = new ImageView(parent.getContext());
		image.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, 200));
		image.setImageResource((Integer)getItem(pos));
		return image;
	}

}

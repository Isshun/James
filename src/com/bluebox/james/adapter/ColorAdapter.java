package com.bluebox.james.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

public class ColorAdapter extends BaseAdapter {

	@Override
	public int getCount() {
		return 16;
	}

	@Override
	public Object getItem(int pos) {
		switch (pos) {
		case 0: return Color.rgb(64, 138, 191);
		case 1: return Color.rgb(164, 189, 24);
		case 2: return Color.rgb(100, 50, 0);
		case 3: return Color.rgb(150, 50, 0);
		case 4: return Color.rgb(200, 50, 0);
		case 5: return Color.rgb(240, 192, 0);
		case 6: return Color.rgb(58, 61, 182);
		case 7: return Color.rgb(58, 137, 182);
		case 8: return Color.rgb(64, 64, 130);
		case 9: return Color.rgb(130, 189, 200);
		case 10: return Color.rgb(80, 189, 100);
		case 11: return Color.rgb(100, 80, 240);
		case 12: return Color.rgb(20, 189, 80);
		case 13: return Color.rgb(164, 80, 200);
		case 14: return Color.rgb(200, 189, 50);
		case 15: return Color.rgb(164, 60, 24);
		case 16: return Color.rgb(255, 189, 130);
		}
		return -1;
	}

	@Override
	public long getItemId(int pos) {
		return 0;
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		View v = new View(parent.getContext());
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(100, 100);
		v.setLayoutParams(lp);
		v.setBackgroundColor((Integer)getItem(pos));
		return v;
	}

}

package org.smallbox.doomotic.adapter;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

public class ColorAdapter extends BaseAdapter {
	private static class ColorStep {
		int r;
		int g;
		int b;
		public ColorStep(int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}
	}
	
	private static final int INTERVAL = 10;
	private List<Integer>	mColors;
	private ColorStep[] mSteps = {
			new ColorStep(255, 0, 0),
			new ColorStep(255, 102, 0),
			new ColorStep(255, 153, 0),
			new ColorStep(255, 191, 0),
			new ColorStep(255, 224, 0),
			new ColorStep(237, 225, 0),
			new ColorStep(158, 237, 0),
			// 175
			new ColorStep(45, 214, 0),
			// 200
			new ColorStep(0, 168, 117),
			// 212
			new ColorStep(89, 145, 150),
			// 225
			new ColorStep(10, 96, 164),
			// 250
			new ColorStep(22, 40, 178),
			// 275
			new ColorStep(66, 18, 178),
			// 300
			new ColorStep(112, 10, 171),
			// 325
			new ColorStep(193, 0, 130),
			// 350
			new ColorStep(237, 0, 51),

			new ColorStep(255, 0, 0),
	};

	public ColorAdapter() {
		mColors = new ArrayList<Integer>();
		
		int i = 0;
		for (ColorStep step: mSteps) {
			if (mSteps.length > i + 1) {
				ColorStep nextStep = mSteps[i + 1];
				for (double j = 0; j <= 1; j += 2) {
					int diffR = nextStep.r - step.r;
					int diffG = nextStep.g - step.g;
					int diffB = nextStep.b - step.b;
					addColor((int)(step.r + diffR * j)+50, (int)(step.g + diffG * j)+50, (int)(step.b + diffB * j)+50);
					addColor((int)(step.r + diffR * j)+40, (int)(step.g + diffG * j)+40, (int)(step.b + diffB * j)+40);
					addColor((int)(step.r + diffR * j)+30, (int)(step.g + diffG * j)+30, (int)(step.b + diffB * j)+30);
					addColor((int)(step.r + diffR * j)+20, (int)(step.g + diffG * j)+20, (int)(step.b + diffB * j)+20);
					addColor((int)(step.r + diffR * j)+10, (int)(step.g + diffG * j)+10, (int)(step.b + diffB * j)+10);
					addColor((int)(step.r + diffR * j)+00, (int)(step.g + diffG * j)+00, (int)(step.b + diffB * j)+00);
					addColor((int)(step.r + diffR * j)-10, (int)(step.g + diffG * j)-10, (int)(step.b + diffB * j)-10);
					addColor((int)(step.r + diffR * j)-20, (int)(step.g + diffG * j)-20, (int)(step.b + diffB * j)-20);
					addColor((int)(step.r + diffR * j)-30, (int)(step.g + diffG * j)-30, (int)(step.b + diffB * j)-30);
				}
				i++;
			}
		}

		
//		for (int i = 0; i < 255; i += INTERVAL) {
//			mColors.add(Color.rgb(Math.max(0, Math.min(255, r)), Math.max(0, Math.min(255, g)), Math.max(0, Math.min(255, b))));
//			g += INTERVAL;
//		}
//
//		for (int i = 0; i < 255; i += INTERVAL) {
//			mColors.add(Color.rgb(Math.max(0, Math.min(255, r)), Math.max(0, Math.min(255, g)), Math.max(0, Math.min(255, b))));
//			g -= INTERVAL * 0.2;
//			r -= INTERVAL;
//		}
//
//		for (int i = 0; i < 255; i += INTERVAL) {
//			mColors.add(Color.rgb(Math.max(0, Math.min(255, r)), Math.max(0, Math.min(255, g)), Math.max(0, Math.min(255, b))));
//			g -= INTERVAL * 0.8;
//			r += INTERVAL * 0.2;
//			b += INTERVAL * 0.7;
//		}
//
//		for (int i = 0; i < 255; i += INTERVAL) {
//			mColors.add(Color.rgb(Math.max(0, Math.min(255, r)), Math.max(0, Math.min(255, g)), Math.max(0, Math.min(255, b))));
//			r += INTERVAL * 0.8;
//			b -= INTERVAL * 0.7;
//		}

	}
	
	private void addColor(int r, int g, int b) {
		mColors.add(Color.rgb(Math.max(0, Math.min(r, 255)), Math.max(0, Math.min(g, 255)), Math.max(0, Math.min(b, 255))));
	}

	@Override
	public int getCount() {
		return mColors.size();
	}

	@Override
	public Object getItem(int pos) {
		return mColors.get(pos);
//		switch (pos) {
//		case 0: return Color.rgb(64, 138, 191);
//		case 1: return Color.rgb(164, 189, 24);
//		case 2: return Color.rgb(100, 50, 0);
//		case 3: return Color.rgb(150, 50, 0);
//		case 4: return Color.rgb(200, 50, 0);
//		case 5: return Color.rgb(240, 192, 0);
//		case 6: return Color.rgb(58, 61, 182);
//		case 7: return Color.rgb(58, 137, 182);
//		case 8: return Color.rgb(64, 64, 130);
//		case 9: return Color.rgb(130, 189, 200);
//		case 10: return Color.rgb(80, 189, 100);
//		case 11: return Color.rgb(100, 80, 240);
//		case 12: return Color.rgb(20, 189, 80);
//		case 13: return Color.rgb(164, 80, 200);
//		case 14: return Color.rgb(200, 189, 50);
//		case 15: return Color.rgb(164, 60, 24);
//		case 16: return Color.rgb(255, 189, 130);
//		}
//		return -1;
	}

	@Override
	public long getItemId(int pos) {
		return 0;
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		View v = new View(parent.getContext());
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(200, 70);
		v.setLayoutParams(lp);
		v.setBackgroundColor((Integer)getItem(pos));
		return v;
	}

}

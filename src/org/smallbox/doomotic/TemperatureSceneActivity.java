package org.smallbox.doomotic;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnGenericMotionListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;

import com.bluebox.james.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewDataInterface;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;

public class TemperatureSceneActivity extends Activity {

	private class GraphViewData implements GraphViewDataInterface {
		private int mI;
		private double mD;

		public GraphViewData(int i, double d) {
			mI = i;
			mD = d;
		}

		@Override
		public double getX() {
			return mI;
		}

		@Override
		public double getY() {
			return mD;
		}
	}

	protected int mTouchPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_temperature);

        GraphViewSeries exampleSeries = new GraphViewSeries(
        	"Current: 20",
        	new GraphViewSeriesStyle(Color.rgb(50, 50, 220), 2),
        	new GraphViewData[] {
        	      new GraphViewData(1, 22)
        	      , new GraphViewData(2, 22.5)
        	      , new GraphViewData(3, 23.2)
        	      , new GraphViewData(4, 24)
        	      , new GraphViewData(5, 24)
        	      , new GraphViewData(6, 24.2)
        	      , new GraphViewData(7, 23.8)
        	      , new GraphViewData(8, 24)
        	      , new GraphViewData(9, 23.8)
        	      , new GraphViewData(10, 23.6)
        	      , new GraphViewData(11, 23.4)
        	      , new GraphViewData(12, 23.2)
        	      , new GraphViewData(13, 23.2)
        	      , new GraphViewData(14, 23.1)
        	      , new GraphViewData(15, 23.1)
        	      , new GraphViewData(16, 23.0)
        	      , new GraphViewData(17, 23.0)
        	});

        final GraphViewData from = new GraphViewData(0, 24);
        final GraphViewData to = new GraphViewData(24, 24);
        final GraphViewSeries exampleSeries2 = new GraphViewSeries(
        		"Expected: 20",
        		new GraphViewSeriesStyle(Color.rgb(220, 220, 50), 2),
        		new GraphViewData[] {
      	      from, to
        	});
        
        final GraphView graphView = new LineGraphView(this, "example");
        graphView.setShowLegend(true);
        graphView.setOnDragListener(new OnDragListener() {
			@Override
			public boolean onDrag(View v, DragEvent event) {
				Log.d(Application.TAG, "drag");
				// TODO Auto-generated method stub
				return false;
			}
		});
        graphView.setOnGenericMotionListener(new OnGenericMotionListener() {
			@Override
			public boolean onGenericMotion(View v, MotionEvent event) {
				Log.d(Application.TAG, "motion");
				return false;
			}
		});
        graphView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
			    switch (event.getAction() & MotionEvent.ACTION_MASK) {
		        case MotionEvent.ACTION_DOWN:
		        	mTouchPos = (int)event.getRawY();
		            break;
		        case MotionEvent.ACTION_UP:
					Log.d(Application.TAG, "move: " + ((int)event.getRawY() - mTouchPos));
					break;
	        	case MotionEvent.ACTION_MOVE:
	        		int y = 50 - (24 + ((int)event.getRawY() - 250) / 24);
	        		exampleSeries2.setDescription("Expected: " + y);
	                exampleSeries2.resetData(new GraphViewData[] {
	                		new GraphViewData(0, y),
	                		new GraphViewData(24, y)
	                	});
					Log.d(Application.TAG, "move: " + (int)event.getRawY());
	        		break;
			    }

				return true;
			}
		});
        graphView.addSeries(exampleSeries2);
        graphView.addSeries(exampleSeries);
        String[] hours = new String[12];
        for (int i = 0; i < 12; i++) {
			hours[i] = (i*2) + "h";        	
		}
        String[] temps = new String[12];
        for (int i = 0; i < 12; i++) {
			temps[11-i] = (i*2+10) + "°";
		}
        graphView.setHorizontalLabels(hours);
        graphView.setVerticalLabels(temps);
        graphView.setManualYAxisBounds(32, 10);
//        graphView.setLayoutParams(new FrameLayout.LayoutParams(800, 400));
        graphView.setBackgroundColor(Color.rgb(200, 200, 250));
//        graphView.setCustomLabelFormatter(new CustomLabelFormatter() {
//           @Override
//           public String formatLabel(double value, boolean isValueX) {
//              if (isValueX) {
//                 if (value < 5) {
//                    return "small";
//                 } else if (value < 15) {
//                    return "middle";
//                 } else {
//                    return "big";
//                 }
//              }
//              return null; // let graphview generate Y-axis label for us
//           }
//        });
        graphView.getGraphViewStyle().setGridColor(Color.argb(100, 83, 136, 255));
        graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.YELLOW);
        graphView.getGraphViewStyle().setVerticalLabelsColor(Color.RED);
        graphView.getGraphViewStyle().setNumHorizontalLabels(5);
        graphView.getGraphViewStyle().setNumVerticalLabels(4);
        graphView.getGraphViewStyle().setLegendWidth(200);
        
        ((FrameLayout)findViewById(R.id.graph)).addView(graphView);
    }
}

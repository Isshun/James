package org.smallbox.doomotic.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluebox.james.R;

public class TempPanelFragment extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";

    public TempPanelFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	final View rootView = inflater.inflate(R.layout.wear_panel_temp, container, false);

        return rootView;
    }

}

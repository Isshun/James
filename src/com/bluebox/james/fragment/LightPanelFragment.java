package com.bluebox.james.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluebox.james.R;

public class LightPanelFragment extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";

    public LightPanelFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	final View rootView = inflater.inflate(R.layout.view_panel_light, container, false);

        return rootView;
    }

}
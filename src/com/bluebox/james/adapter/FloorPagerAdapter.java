package com.bluebox.james.adapter;

import java.util.Locale;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;

import com.bluebox.james.fragment.FloorAdminFragment;
import com.bluebox.james.service.DoomService;

public class FloorPagerAdapter extends FragmentPagerAdapter {
    public FloorPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        Fragment fragment = new FloorAdminFragment();
        Bundle args = new Bundle();
        args.putLong(FloorAdminFragment.ARG_FLOOR_ID, DoomService.getInstance().getFloors().get(pos).getId());
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public long getItemId(int pos) {
    	if (DoomService.getInstance().getRooms().size() > pos) {
        	return DoomService.getInstance().getFloors().get(pos).getId();
    	}
    	return -1;
    }

    @Override
    public int getCount() {
        return DoomService.getInstance().getFloors().size();
    }

    @Override
    public CharSequence getPageTitle(int pos) {
        Locale l = Locale.getDefault();
        return DoomService.getInstance().getFloors().get(pos).getLabel().toUpperCase(l);
    }
}

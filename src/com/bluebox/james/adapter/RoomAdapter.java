package com.bluebox.james.adapter;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bluebox.james.fragment.RoomFragment;
import com.bluebox.james.service.DoomService;

public class RoomAdapter extends FragmentPagerAdapter {

    public RoomAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        Fragment fragment = new RoomFragment();
        Bundle args = new Bundle();
        args.putLong(RoomFragment.ARG_ROOM_ID, DoomService.getInstance().getRooms().get(pos).getId());
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public long getItemId(int pos) {
    	if (DoomService.getInstance().getRooms().size() > pos) {
        	return DoomService.getInstance().getRooms().get(pos).getId();
    	}
    	return -1;
    }

    @Override
    public int getCount() {
        return DoomService.getInstance().getRooms().size();
    }

    @Override
    public CharSequence getPageTitle(int pos) {
        Locale l = Locale.getDefault();
        return DoomService.getInstance().getRooms().get(pos).getName().toUpperCase(l);
    }
}

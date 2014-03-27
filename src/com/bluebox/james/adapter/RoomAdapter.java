package com.bluebox.james.adapter;

import java.util.Locale;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;

import com.bluebox.james.fragment.RoomFragment;
import com.bluebox.james.service.RoomService;

public class RoomAdapter extends FragmentPagerAdapter {

    public RoomAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        Fragment fragment = new RoomFragment();
        Bundle args = new Bundle();
        args.putLong(RoomFragment.ARG_ROOM_ID, RoomService.getInstance().getRooms().get(pos).getId());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return RoomService.getInstance().getRooms().size();
    }

    @Override
    public CharSequence getPageTitle(int pos) {
        Locale l = Locale.getDefault();
        return RoomService.getInstance().getRooms().get(pos).getName().toUpperCase(l);
    }
}

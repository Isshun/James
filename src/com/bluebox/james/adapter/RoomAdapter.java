package com.bluebox.james.adapter;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bluebox.james.fragment.RoomFragment;
import com.bluebox.james.service.RoomService;

public class RoomAdapter extends FragmentPagerAdapter {

    public RoomAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new RoomFragment();
        Bundle args = new Bundle();
        args.putInt(RoomFragment.ARG_ROOM_ID, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return RoomService.getInstance().getCount();
    }

    @Override
    public CharSequence getPageTitle(int index) {
        Locale l = Locale.getDefault();
        return RoomService.getInstance().getRoom(index).getName().toUpperCase(l);
    }
}

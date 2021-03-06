package org.smallbox.doomotic.adapter;

import java.util.Locale;

import org.smallbox.doomotic.fragment.RoomFragment;
import org.smallbox.doomotic.model.RoomModel;
import org.smallbox.doomotic.service.DoomService;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class RoomAdapter extends FragmentPagerAdapter {

    public RoomAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        Fragment fragment = new RoomFragment();
        Bundle args = new Bundle();
        args.putInt(RoomFragment.ARG_ROOM_ID, DoomService.getInstance().getRooms().get(pos).getId());
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

	public RoomModel getRoom(int pos) {
		return DoomService.getInstance().getRooms().get(pos);
	}
}

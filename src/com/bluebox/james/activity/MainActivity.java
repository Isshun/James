package com.bluebox.james.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bluebox.james.Application;
import com.bluebox.james.JSONUtils;
import com.bluebox.james.R;
import com.bluebox.james.adapter.RoomAdapter;
import com.bluebox.james.dialog.NewFeatureDialogFragment;

public class MainActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    RoomAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

	private DrawerLayout mDrawerLayout;

	private ActionBarDrawerToggle mDrawerToggle;

	private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        String array[] = {"1", "2"};
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, array));
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, android.R.drawable.ic_delete, R.string.action_settings, R.string.action_settings) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new RoomAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.action_save:
        	JSONUtils.saveDevices();
        	JSONUtils.saveRooms();
        	return true;
        case R.id.action_all_probe: {
			Intent intent = new Intent(MainActivity.this, AdminProbesActivity.class);
			startActivity(intent);
            return true;
        }
        case R.id.action_all_switch: {
			Intent intent = new Intent(MainActivity.this, AdminSwitchsActivity.class);
			startActivity(intent);
            return true;
        }
        case R.id.action_settings: {
			Intent intent = new Intent(MainActivity.this, AdminActivity.class);
			startActivity(intent);
            return true;
        }
        case R.id.action_add_feature: {
			NewFeatureDialogFragment f = new NewFeatureDialogFragment();
			f.setOnCloseListener(new NewFeatureDialogFragment.OnCloseListener() {
				@Override
				public void onClose() {
					mSectionsPagerAdapter.notifyDataSetChanged();
					//adapter.notifyDataSetChanged();
				}
			});
	    	Bundle args = new Bundle();
	    	long roomId = mSectionsPagerAdapter.getItemId(mViewPager.getCurrentItem());
	        args.putLong(Application.ARG_ROOM_ID, roomId);
	        f.setArguments(args);
	        f.show(getFragmentManager().beginTransaction(), "dialog");
        }
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}

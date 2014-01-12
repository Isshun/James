package com.bluebox.james.service;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.bluebox.james.Application;
import com.bluebox.james.SceneAction;
import com.bluebox.james.model.RoomModel;

public class RoomService {

	private static RoomService sRoomService;
	private List<RoomModel> mRooms;

	private RoomService() {
		mRooms = new ArrayList<RoomModel>();
	}

	public static RoomService getInstance() {
		if (sRoomService == null) {
			sRoomService = new RoomService();
		}
		return sRoomService;
	}

	public int getCount() {
		return mRooms.size();
	}

	public List<RoomModel> getRoomList() {
		return mRooms;
	}

	public static void execute(SceneAction action) {
		AQuery aquery = new AQuery(Application.getContext());
		aquery.ajax(action.url, String.class, new AjaxCallback<String>() {
			@Override
	        public void callback(String url, String html, AjaxStatus status) {
				Log.d("CALLBACK", "callback: " + status.getCode());
	        }
		});
	}
	
	
	
}

package com.bluebox.james.model;

public class FloorModel {

	private String mLabel;
	private long mId;

	public FloorModel(long id, String label) {
		mLabel = label;
		mId = id;
	}

	public String getLabel() {
		return mLabel;
	}

	public long getId() {
		return mId;
	}

}

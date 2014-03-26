package com.bluebox.james.model;

public abstract class DBModel {
	protected long mDbId;

	public long getId() {
		return mDbId;
	}
	
	public void setId(long dbId) {
		mDbId = dbId;
	}

}

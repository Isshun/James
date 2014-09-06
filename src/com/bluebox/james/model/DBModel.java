package com.bluebox.james.model;

public abstract class DBModel {
	private static int 	sCount;
	private int 		mDbId;

	public long getId() {
		return mDbId;
	}
	
	public DBModel() {
		mDbId = ++sCount;
	}

	public DBModel(int id) {
		sCount = (int) Math.max(sCount, id + 1);
		mDbId = id > 0 ? id : ++sCount;
	}
}

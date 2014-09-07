package org.smallbox.doomotic.model;

public abstract class DBModel {
	private static int 	sCount;
	private int 		mId;

	public int getId() {
		return mId;
	}
	
	public DBModel() {
		mId = ++sCount;
	}

	public DBModel(int id) {
		sCount = (int) Math.max(sCount, id + 1);
		mId = id > 0 ? id : ++sCount;
	}
}

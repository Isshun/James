package org.smallbox.doomotic.model;

public class FloorModel extends DBModel {
	private String 	mLabel;

	public FloorModel(int id, String label) {
		super(id);
		mLabel = label;
	}

	public String getLabel() {
		return mLabel;
	}

}

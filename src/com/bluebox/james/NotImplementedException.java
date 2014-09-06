package com.bluebox.james;

public class NotImplementedException extends RuntimeException {
	private static final long serialVersionUID = 604795620826958203L;
	
	public NotImplementedException() {
		super("Not implemented");
	}
}

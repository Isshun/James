package com.bluebox.james.model.scenario;

public class Log {
	public static int LEVEL_FATAL = 0;
	public static int LEVEL_ERROR = 1;
	public static int LEVEL_WARNING = 2;
	public static int LEVEL_INFO = 3;
	public static int LEVEL_DEBUG = 4;
	public static int LEVEL = LEVEL_INFO;
	
	public static void debug(String str) {
		if (LEVEL < LEVEL_DEBUG) return;
		
		if (str != null) {
			android.util.Log.d("", str);
		}
	}

	public static void error(String str) {
		if (LEVEL < LEVEL_ERROR) return;

		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		android.util.Log.e("", "Error occured \"" + str + "\"");
		for (int i = 2; i < elements.length; i++) {
			android.util.Log.e("Log", "          " + elements[i].toString());
		}
	}

	public static void info(String str) {
		if (LEVEL < LEVEL_INFO) return;
		
		if (str != null) {
			android.util.Log.i("Log", str);
		}
	}

	public static void warning(String str) {
		if (LEVEL < LEVEL_WARNING) return;

		if (str != null) {
			android.util.Log.w("Log", str);
		}
	}
}

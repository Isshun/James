package com.bluebox.james;

import android.app.Activity;
import android.view.WindowManager;

public class Utils {

	public static void hideKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}

}

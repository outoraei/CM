package cm.demo.control.activitymanager;

import android.content.Context;

public class CMActivityManager {

	private CMActivityManager() {}

	private static CMActivityManager instance = new CMActivityManager();

	public static CMActivityManager getInstance(Context context) {
		return instance;
	}

	public static boolean forwardChange() {
		return true;
	};

	public static boolean backwardChange() {
		return true;
	}

	public static boolean GoBackToMainActivity() {
		return true;
	}
}

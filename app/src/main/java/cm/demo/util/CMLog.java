package cm.demo.util;

import android.util.Log;

public class CMLog {

	public static String CMTAG = "JIANQIANLE";

	public static int LogD(String msg) {
		return Log.d(CMTAG, msg);
	}

	public static int LogE(String msg) {
		return Log.e(CMTAG, msg);
	}

	public static int LogW(String msg) {
		return Log.w(CMTAG, msg);
	}
}

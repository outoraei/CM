package cm.demo.util;

import android.util.Log;

import java.util.Date;

public class CMLog {

    public static String CMTAG = "JIANQIANLE";

    public static int LogD(String msg) {
        StackTraceElement element = getTraceElement();
        if (null != element) {
            msg = new Date() + " , " + msg + " , " + element.getMethodName() + " , line:" + element.getLineNumber() + " , " + element.getClassName();
        }
        return Log.d(CMTAG, msg);
    }

    public static int LogE(String msg) {
        StackTraceElement element = getTraceElement();
        if (null != element) {
            msg = new Date() + " , " + msg + " , " + element.getMethodName() + " , line:" + element.getLineNumber() + " , " + element.getClassName();
        }
        return Log.e(CMTAG, msg);
    }

    public static int LogW(String msg) {
        StackTraceElement element = getTraceElement();
        if (null != element) {
            msg = new Date() + " , " + msg + " , " + element.getMethodName() + " , line:" + element.getLineNumber() + " , " + element.getClassName();
        }
        return Log.w(CMTAG, msg);
    }

    public static StackTraceElement getTraceElement() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        if (elements.length < 5) {
            return null;
        }
        return elements[4];
    }
}

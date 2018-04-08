package cm.demo.model;


import android.content.Context;

import java.util.Map;

import cm.demo.connect.network.AnsynHttpRequest;
import cm.demo.connect.network.NetworkCommon;
import cm.demo.connect.network.ObserverCallBack;
import cm.demo.util.CMLog;

public abstract class CMBaseModel {
    CMBaseModel(Context context) {
        mContext = context;
    }

    abstract protected String makeStrRequestURL();

    abstract protected Map<String, String> makeMapRequestURL();

    protected String sendRequest() {
        AnsynHttpRequest.requestByGet(mContext, NetworkCommon.CM_SERVER_URL + NetworkCommon.CM_USER_SERVLET,
                callbackData, 123, makeMapRequestURL(), true, true);
        return "";
    }

    private ObserverCallBack callbackData = new ObserverCallBack() {
        public void back(String data, int url) {
            CMLog.LogD("data : " + data + " url: " + url);
        }
    };
    private Context mContext;
}

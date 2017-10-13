package cm.demo.view.main;

import android.os.AsyncTask;
import android.os.Bundle;

import java.util.Hashtable;
import java.util.Map;

import cm.demo.connect.network.AnsynHttpRequest;
import cm.demo.connect.network.CMNetworkManager;
import cm.demo.connect.network.NetworkCommon;
import cm.demo.connect.network.ObserverCallBack;
import cm.demo.util.CMLog;
import cm.demo.view.base.CMBaseActivity;

public class SettingActivity extends CMBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        new CMAsyncTask().execute();
        Map<String, String> map = new Hashtable<String, String>();
        map.put("username", "123");
        map.put("password", "root");
        map.put("start", "1");
        AnsynHttpRequest.requestByGet(this, NetworkCommon.CM_SERVER_URL + NetworkCommon.CM_USER_SERVLET, callbackData, 123, map, true, true);
    }

    class CMAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            return CMNetworkManager.sendRequest(NetworkCommon.CM_SERVER_URL + NetworkCommon.CM_USER_SERVLET);
        }
    }

    private ObserverCallBack callbackData = new ObserverCallBack() {
        public void back(String data, int url) {
            CMLog.LogD("data : " + data + " url: " + url);
        }
    };
}

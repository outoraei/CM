package cm.demo.view.main;

import android.os.AsyncTask;
import android.os.Bundle;

import cm.demo.connect.network.CMNetworkManager;
import cm.demo.connect.network.NetworkCommon;
import cm.demo.view.base.CMBaseActivity;

public class SettingActivity extends CMBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CMAsyncTask().execute();
    }

    class CMAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            return CMNetworkManager.sendRequest(NetworkCommon.CM_SERVER_URL + NetworkCommon.CM_USER_SERVLET);
        }
    }
}

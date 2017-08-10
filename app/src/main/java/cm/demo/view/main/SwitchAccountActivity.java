package cm.demo.view.main;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cm.demo.R;
import cm.demo.dbmanager.ServerLogic;
import cm.demo.model.RegisterModel;
import cm.demo.util.CMLog;
import cm.demo.view.base.CMBaseActivity;

public class SwitchAccountActivity extends CMBaseActivity {

    private EditText phone_edittext;
    private EditText password_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch_account_layout);

        phone_edittext = (EditText) findViewById(R.id.input_phone);
        password_edittext = (EditText) findViewById(R.id.input_password);
    }

    public void login(View view) {
        CMLog.LogD("login");
        String phoneNum = "";
        String password = "";
        if (phone_edittext != null) {
            phoneNum = phone_edittext.getText().toString();
        }
        if (password_edittext != null) {
            password = password_edittext.getText().toString();
        }

        int ret = ServerLogic.getInstance().login(new RegisterModel(phoneNum, password));
        if (ServerLogic.LOGIN_OK == ret) {
            CMLog.LogD("switch account activity finish");
            this.finish();
        }
    }
}

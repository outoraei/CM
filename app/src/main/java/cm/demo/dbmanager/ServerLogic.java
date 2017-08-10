package cm.demo.dbmanager;

import cm.demo.model.RegisterModel;
import cm.demo.util.CMLog;

public class ServerLogic {

    public static final int LOGIN_OK = 0;
    public static final int LOGIN_ERROR_MASK = 1;
    public static final int LOGIN_ERROR_NULL_MODEL = 2;

    private ServerLogic() {
    }

    private static ServerLogic instance = new ServerLogic();

    public static ServerLogic getInstance() {
        return instance;
    }

    public int login(RegisterModel model) {
        if (null == model) {
            return LOGIN_ERROR_NULL_MODEL;
        }
        String phoneNo = model.getPhoneNo();
        String password = model.getPassword();
        CMLog.LogD("login OK phoneNo : " + phoneNo + " password : " + password);
        return LOGIN_OK;
    }
}

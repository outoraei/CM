package cm.demo.model;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class RegisterModel extends CMBaseModel {

    @Override
    protected String makeStrRequestURL() {
        StringBuilder sb = new StringBuilder();
        sb.append("username").append("=").append(name).append("&").append("password").append("=").append(password);
        return sb.toString();
    }

    @Override
    protected Map<String, String> makeMapRequestURL() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", name);
        map.put("password", password);
        return map;
    }

    enum REGISTER_TYPE {
        REGISTER_TYPE_NORMAL, REGISTER_TYPE_WECHAT, REGISTER_TYPE_QQ,
    }

    private String name;
    private String phoneNo;
    private String confirmCodeFromMobile;
    private String password;
    private REGISTER_TYPE registerType;

    public RegisterModel(Context context, String phoneNo, String password) {
        super(context);
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getConfirmCodeFromMobile() {
        return confirmCodeFromMobile;
    }

    public void setConfirmCodeFromMobile(String confirmCodeFromMobile) {
        this.confirmCodeFromMobile = confirmCodeFromMobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public REGISTER_TYPE getRegisterType() {
        return registerType;
    }

    public void setRegisterType(REGISTER_TYPE registerType) {
        this.registerType = registerType;
    }

}

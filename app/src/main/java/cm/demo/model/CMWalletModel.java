package cm.demo.model;

import android.content.Context;

import java.util.Map;

public class CMWalletModel extends CMBaseModel {
    String name;
    String detail;

    public CMWalletModel(Context context) {
        super(context);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    protected String makeStrRequestURL() {
        return null;
    }

    @Override
    protected Map<String, String> makeMapRequestURL() {
        return null;
    }
}

package cm.demo.model;

import android.content.Context;

import java.util.Map;

public class MySettingModel extends CMBaseModel {
    private int itemIcon;
    private String itemName;
    private int itemNameID;
    private int nextscreen;

    public MySettingModel(Context context) {
        super(context);
    }

    public int getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(int itemIcon) {
        this.itemIcon = itemIcon;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getNextscreen() {
        return nextscreen;
    }

    public void setNextscreen(int nextscreen) {
        this.nextscreen = nextscreen;
    }

    public int getItemNameID() {
        return itemNameID;
    }

    public void setItemNameID(int itemNameID) {
        this.itemNameID = itemNameID;
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

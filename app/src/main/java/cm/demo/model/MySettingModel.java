package cm.demo.model;

public class MySettingModel extends CMBaseModel {
    private int itemIcon;
    private String itemName;
    private int itemNameID;
    private int nextscreen;

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

}

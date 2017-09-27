package cm.demo.model;

public class ItemModel extends CMBaseModel {

    private int itemID;
    private String name;
    private String description;
    private int reserve_num;
    private int item_num;
    private long value;
    private String image_uri;

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setImageURI(String image_uri) {
        this.image_uri = image_uri;
    }

    public String getImageURI() {
        return image_uri;
    }

    public int getReserve_num() {
        return reserve_num;
    }

    public void setReserve_num(int reserve_num) {
        this.reserve_num = reserve_num;
    }


    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }
}

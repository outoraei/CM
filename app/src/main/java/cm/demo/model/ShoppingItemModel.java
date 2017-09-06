package cm.demo.model;

public class ShoppingItemModel extends CMBaseModel {
    private int itemID;
    private String image_uri;
    private String name;
    private String description;
    private long eachValue;
    private int num;
//    private long totalValue;

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

    public void setEachValue(long eachValue) {
        this.eachValue = eachValue;
    }

    public long getEachValue() {
        return eachValue;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

//    public void setTotalValue(long totalValue) {
//        this.totalValue = totalValue;
//    }
//
//    public long getTotalValue() {
//        return totalValue;
//    }

    public void setImageURI(String image_uri) {
        this.image_uri = image_uri;
    }

    public String getImageURI() {
        return image_uri;
    }
}

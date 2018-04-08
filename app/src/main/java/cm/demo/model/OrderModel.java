package cm.demo.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cm.demo.util.CMLog;

public class OrderModel extends CMBaseModel {

    public OrderModel(Context context) {
        super(context);
    }

    public String getSearch_logistics_code() {
        return search_logistics_code;
    }

    public void setSearch_logistics_code(String search_logistics_code) {
        this.search_logistics_code = search_logistics_code;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getTotalNum() {
        if (orderList == null) {
            CMLog.LogE("order list is null");
            return 0;
        }
        int num_count = 0;
        for (ItemModel model : orderList) {
            num_count += model.getItem_num();
        }
        return num_count;
    }

    public float getTotalValue() {
        if (orderList == null) {
            CMLog.LogE("order list is null");
            return 0;
        }
        int totalValue = 0;
        for (ItemModel model : orderList) {
            totalValue += model.getItem_num() * model.getValue();
        }
        return totalValue;
    }

    public List<ItemModel> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<ItemModel> orderList) {
        this.orderList = orderList;
    }

    private String orderCode;
    private String shopCode;
    private String shopName;
    private String search_logistics_code;
    private int orderStatus;
    private List<ItemModel> orderList = new ArrayList<ItemModel>();

    public static final int ORDER_STATUS_UNKNOWN = -1;
    public static final int ORDER_STATUS_WAITFOR_PAY = 0;
    public static final int ORDER_STATUS_WAITFOR_DELIVERY = 1;
    public static final int ORDER_STATUS_WAITFOR_RECEIPT = 2;
    public static final int ORDER_STATUS_WAITFOR_COMMENTS = 3;

    @Override
    protected String makeStrRequestURL() {
        return null;
    }

    @Override
    protected Map<String, String> makeMapRequestURL() {
        return null;
    }
}

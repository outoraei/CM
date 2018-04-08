package cm.demo.model;

import android.content.Context;

import java.util.Map;

public class BonusModel extends CMBaseModel {
    public static final int BONUS_TYPE_MASK = 0;
    public static final int BONUS_TYPE_AVG = BONUS_TYPE_MASK + 1;
    public static final int BONUS_TYPE_RANDOM = BONUS_TYPE_MASK + 2;

    BonusModel(Context context) {
        super(context);
    }

    public int getBonusType() {
        return bonusType;
    }

    public void setBonusType(int bonusType) {
        this.bonusType = bonusType;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getRemainPrice() {
        return remainPrice;
    }

    public void setRemainPrice(int remainPrice) {
        this.remainPrice = remainPrice;
    }

    public int getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(int remainNum) {
        this.remainNum = remainNum;
    }

    int bonusType;
    float totalPrice;
    int totalNum;
    int remainPrice;
    int remainNum;

    @Override
    protected String makeStrRequestURL() {
        return null;
    }

    @Override
    protected Map<String, String> makeMapRequestURL() {
        return null;
    }
}

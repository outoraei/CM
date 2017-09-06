package cm.demo.view.main;

import android.os.Bundle;

import cm.demo.R;
import cm.demo.view.base.CMBaseActivity;

public class MyCouponActivity extends CMBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.my_wallet_item_coupon);
    }
}

package cm.demo.view.main;

import android.os.Bundle;

import cm.demo.R;
import cm.demo.view.base.CMBaseActivity;

public class MyCheckoutActivity extends CMBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.shoppingcart_checkout_title);
    }
}

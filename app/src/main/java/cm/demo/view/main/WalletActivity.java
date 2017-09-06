package cm.demo.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cm.demo.R;
import cm.demo.model.CMWalletModel;
import cm.demo.util.CMLog;
import cm.demo.view.base.CMBaseActivity;
import cm.demo.view.commoncontrol.CMListAdapter;

public class WalletActivity extends CMBaseActivity {

    ListView myWalletListView = null;
    List<CMWalletModel> walletModelList = null;
    MyWalletListAdapter adapter = null;
    private int[] myWalletNames = {R.string.my_wallet_item_balance, R.string.my_wallet_item_bonus,
            R.string.my_wallet_item_coupon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.my_wallet_title);

        setContentView(R.layout.my_wallet_layout);

        initWalletListData();

        myWalletListView = (ListView) findViewById(R.id.my_wallet_list);
        adapter = new MyWalletListAdapter();
        myWalletListView.setAdapter(adapter);
        myWalletListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        goToBalanceScreen();
                        break;
                    }
                    case 1: {
                        goToBonusScreen();
                        break;
                    }
                    case 2: {
                        goToCouponScreen();
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        });
    }

    private void initWalletListData() {
        CMLog.LogD("initSquareListData");
        walletModelList = new ArrayList<CMWalletModel>();
        String[] details = new String[]{getBalance(), getBonus(), getCoupon()};
        CMWalletModel model;
        for (int i = 0; i < myWalletNames.length; i++) {
            model = new CMWalletModel();
            model.setName(getString(myWalletNames[i]));
            model.setDetail(details[i]);
            walletModelList.add(model);
        }
    }

    private String getBalance() {
        return "21" + getString(R.string.my_wallet_item_money_unit);
    }

    private String getBonus() {
        return "0.85" + getString(R.string.my_wallet_item_money_unit);
    }

    private String getCoupon() {
        return "1" + getString(R.string.my_wallet_item_coupon_unit);
    }

    public void goToBalanceScreen() {
        CMLog.LogD("goToBalance");
        Intent intent = new Intent();
        intent.setClass(this, MyBalanceActivity.class);
        startActivity(intent);
    }

    public void goToBonusScreen() {
        CMLog.LogD("goToBonusScreen");
        Intent intent = new Intent();
        intent.setClass(this, MyBonusActivity.class);
        startActivity(intent);
    }

    public void goToCouponScreen() {
        CMLog.LogD("goToCouponScreen");
        Intent intent = new Intent();
        intent.setClass(this, MyCouponActivity.class);
        startActivity(intent);
    }

    final class ViewHolder {
        TextView name;
        TextView detail;
        ImageView goNextScreen;
    }

    class MyWalletListAdapter extends CMListAdapter {

        LayoutInflater inflater;

        public MyWalletListAdapter() {
            inflater = (LayoutInflater) getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return walletModelList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.my_wallet_list_row, null);
                holder.name = (TextView) convertView.findViewById(R.id.my_wallet_item_name);
                holder.detail = (TextView) convertView
                        .findViewById(R.id.my_wallet_item_detail);
                holder.goNextScreen = (ImageView) convertView
                        .findViewById(R.id.my_wallet_item_go_next_screen);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            CMWalletModel item = walletModelList.get(position);

            holder.name.setText(item.getName());
            holder.detail.setText(item.getDetail());

            return convertView;
        }
    }

}

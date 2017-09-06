package cm.demo.view.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cm.demo.R;
import cm.demo.model.ShoppingItemModel;
import cm.demo.util.CMLog;
import cm.demo.view.base.CMBaseActivity;
import cm.demo.view.commoncontrol.CMListAdapter;

public class ShoppingCartActivity extends CMBaseActivity {

    ListView shoppingCartListView = null;
    List<ShoppingItemModel> shoppingList = null;
    ShoppingListAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shoppingcart_layout);

        Button checkout_button = (Button) findViewById(R.id.shopping_checkout);
        checkout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCheckOutScreen();
            }
        });

        initShoppingData();

        shoppingCartListView = (ListView) findViewById(R.id.shoppingcart_list);
        adapter = new ShoppingListAdapter();
        shoppingCartListView.setAdapter(adapter);
        shoppingCartListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        shoppingCartListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    private void gotoCheckOutScreen() {
        CMLog.LogD("goToBalance");
        Intent intent = new Intent();
        intent.setClass(this, MyCheckoutActivity.class);
        startActivity(intent);
    }

    private void initShoppingData() {
        CMLog.LogD("initSquareListData");
        shoppingList = new ArrayList<ShoppingItemModel>();
        ShoppingItemModel model;
        for (int i = 0; i < 10; i++) {
            model = new ShoppingItemModel();
            model.setItemID(i);
            model.setName("Name " + i);
            model.setDescription("Description" + i);
            model.setEachValue(i);
            model.setImageURI("");
            model.setNum(i);
            shoppingList.add(model);
        }
    }

    private View.OnClickListener onPlusClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button btn = (Button)v;
            int pos = (Integer) btn.getTag();
            int currentNum = shoppingList.get(pos).getNum();
            if (currentNum - 1 <= 0) {
                return;
            }
            shoppingList.get(pos).setNum(currentNum - 1);
            adapter.notifyDataSetChanged();
        }
    };

    private View.OnClickListener onMinusClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button btn = (Button)v;
            int pos = (Integer) btn.getTag();
            shoppingList.get(pos).setNum(shoppingList.get(pos).getNum() + 1);
            adapter.notifyDataSetChanged();
        }
    };

    final class ViewHolder {
        ImageView thumb_image;
        TextView name;
        TextView description;
        TextView eachvalue;
        TextView num;
        Button plus_num;
        Button minus_num;
        TextView totalvalue;
    }

    class ShoppingListAdapter extends CMListAdapter {

        LayoutInflater inflater;

        public ShoppingListAdapter() {
            inflater = (LayoutInflater) getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return shoppingList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.shoppingcart_list_row, null);
                holder.thumb_image = (ImageView) convertView
                        .findViewById(R.id.shopping_item_image);
                holder.name = (TextView) convertView.findViewById(R.id.shopping_item_name);
                holder.description = (TextView) convertView
                        .findViewById(R.id.shopping_item_description);
                holder.eachvalue = (TextView) convertView
                        .findViewById(R.id.shopping_item_eachvalue);
                holder.num = (TextView) convertView
                        .findViewById(R.id.shopping_item_num);
                holder.plus_num = (Button) convertView
                        .findViewById(R.id.shopping_item_num_plus);
                holder.minus_num = (Button) convertView
                        .findViewById(R.id.shopping_item_num_minus);
                holder.totalvalue = (TextView) convertView
                        .findViewById(R.id.shopping_item_totalvalue);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ShoppingItemModel item = shoppingList.get(position);

            holder.name.setText(item.getName());
            holder.description.setText(item.getDescription());
            holder.eachvalue.setText("" + item.getEachValue());

            holder.num.setText("" + item.getNum());
            holder.totalvalue.setText("" + item.getNum() * item.getEachValue());

            holder.minus_num.setTag(position);
            holder.minus_num.setOnClickListener(onPlusClickListener);

            holder.plus_num.setTag(position);
            holder.plus_num.setOnClickListener(onMinusClickListener);


            String uri = item.getImageURI();
            if ("".equals(uri)) {
                holder.thumb_image.setImageResource(R.drawable.ic_launcher);
            } else {
                holder.thumb_image.setImageURI(Uri.parse(uri));
            }

            return convertView;
        }
    }
}

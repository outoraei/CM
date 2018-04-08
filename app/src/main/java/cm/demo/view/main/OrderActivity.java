package cm.demo.view.main;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cm.demo.R;
import cm.demo.model.ItemModel;
import cm.demo.model.OrderModel;
import cm.demo.util.CMLog;
import cm.demo.view.base.CMBaseActivity;
import cm.demo.view.customview.OrderAdapter;

public class OrderActivity extends CMBaseActivity {

    private ListView listView;
    private OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_layout);

        listView = (ListView) findViewById(R.id.order_list);

        List<OrderModel> orderModelList = new ArrayList<OrderModel>();

        OrderModel orderItem;
        for (int i = 1; i <= 5; i++) {

            List<ItemModel> itemModelList = new ArrayList<ItemModel>();

            double itemNum = (Math.random() * 10) + 1;
//            double itemNum = 3;
            ItemModel item;
            for (int index = 1; index <= itemNum; index++) {
                item = new ItemModel(this);
                item.setName("item " + i + " , " + index);
                item.setValue(index);
                item.setDescription("desc " + i + " , " + index);
                item.setItem_num(index);
                itemModelList.add(item);
            }

            orderItem = new OrderModel(this);
            orderItem.setOrderCode("ordercode " + i);
            orderItem.setShopName("shop " + i);
            orderItem.setOrderStatus(i - 2);
            orderItem.setOrderList(itemModelList);

            orderModelList.add(orderItem);
        }

        adapter = new OrderAdapter(orderModelList, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CMLog.LogD("order list click : " + position);
            }
        });
    }
}

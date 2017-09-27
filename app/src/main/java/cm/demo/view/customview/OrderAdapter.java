package cm.demo.view.customview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import cm.demo.R;
import cm.demo.model.OrderModel;
import cm.demo.util.CMLog;

public class OrderAdapter extends BaseAdapter implements ListAdapter {
    private Context context;
    private OrderItemAdapter orderItemAdapter;
    private LayoutInflater inflater;
    private List<OrderModel> list;
    private String totalnum_str;
    private String replacenum_str;
    private String totalvalue_str;

    final class ViewHolder {
        TextView order_code;
        TextView order_shop_name;
        TextView total_num;
        TextView total_value;
        TextView order_status;

        TextView order_oper_extend_receipt;
        TextView order_oper_search_logistics;
        TextView order_oper_confirm_receipt;
        TextView order_oper_delete_order;
        TextView order_oper_appraise;

        TextView order_oper_cancel_order;
        TextView order_oper_pay_immediately;
        TextView order_oper_reminder_delivery;

        OrderItemListView listview;
    }

    public OrderAdapter(List<OrderModel> list, Context context) {
        inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.list = list;
        replacenum_str = context.getString(R.string.order_item_replacenum);
        totalnum_str = context.getText(R.string.order_item_totalnum).toString();
        totalvalue_str = context.getText(R.string.order_item_total_value).toString();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.order_list_row, null);
            holder.total_num = (TextView) convertView.findViewById(R.id.order_item_totalnum);
            holder.total_value = (TextView) convertView.findViewById(R.id.order_item_totalvalue);
            holder.listview = (OrderItemListView) convertView.findViewById(R.id.order_list);

            holder.order_code = (TextView) convertView.findViewById(R.id.order_item_ordercode);
            holder.order_shop_name = (TextView) convertView.findViewById(R.id.order_shop_name);
            holder.order_status = (TextView) convertView.findViewById(R.id.order_status);
            holder.order_oper_extend_receipt = (TextView) convertView.findViewById(R.id.order_oper_extend_receipt);
            holder.order_oper_search_logistics = (TextView) convertView.findViewById(R.id.order_oper_search_logistics);
            holder.order_oper_confirm_receipt = (TextView) convertView.findViewById(R.id.order_oper_confirm_receipt);
            holder.order_oper_delete_order = (TextView) convertView.findViewById(R.id.order_oper_delete_order);
            holder.order_oper_appraise = (TextView) convertView.findViewById(R.id.order_oper_appraise);
            holder.order_oper_cancel_order = (TextView) convertView.findViewById(R.id.order_oper_cancel_order);
            holder.order_oper_pay_immediately = (TextView) convertView.findViewById(R.id.order_oper_pay_immediately);
            holder.order_oper_reminder_delivery = (TextView) convertView.findViewById(R.id.order_oper_reminder_delivery);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.total_num.setText(totalnum_str.replace(replacenum_str, "" + list.get(position).getTotalNum()));
        holder.total_value.setText(totalvalue_str + list.get(position).getTotalValue());

        holder.order_shop_name.setText("shop name : " + list.get(position).getShopName());
        holder.order_code.setText(list.get(position).getOrderCode());

        int statusStrID = 0;
        int orderStatus = list.get(position).getOrderStatus();
        switch (orderStatus) {
            case OrderModel.ORDER_STATUS_UNKNOWN: {
                statusStrID = R.string.order_status_unknown;
                holder.order_oper_extend_receipt.setVisibility(View.GONE);
                holder.order_oper_search_logistics.setVisibility(View.GONE);
                holder.order_oper_confirm_receipt.setVisibility(View.GONE);
                holder.order_oper_delete_order.setVisibility(View.GONE);
                holder.order_oper_appraise.setVisibility(View.GONE);
                holder.order_oper_cancel_order.setVisibility(View.GONE);
                holder.order_oper_pay_immediately.setVisibility(View.GONE);
                holder.order_oper_reminder_delivery.setVisibility(View.GONE);
                break;
            }
            case OrderModel.ORDER_STATUS_WAITFOR_PAY: {
                statusStrID = R.string.order_status_waitfor_pay;
                holder.order_oper_extend_receipt.setVisibility(View.GONE);
                holder.order_oper_search_logistics.setVisibility(View.GONE);
                holder.order_oper_confirm_receipt.setVisibility(View.GONE);
                holder.order_oper_delete_order.setVisibility(View.GONE);
                holder.order_oper_appraise.setVisibility(View.GONE);
                holder.order_oper_cancel_order.setVisibility(View.VISIBLE);
                holder.order_oper_pay_immediately.setVisibility(View.VISIBLE);
                holder.order_oper_reminder_delivery.setVisibility(View.GONE);
                break;
            }
            case OrderModel.ORDER_STATUS_WAITFOR_DELIVERY: {
                statusStrID = R.string.order_status_waitfor_delivery;
                holder.order_oper_extend_receipt.setVisibility(View.GONE);
                holder.order_oper_search_logistics.setVisibility(View.GONE);
                holder.order_oper_confirm_receipt.setVisibility(View.GONE);
                holder.order_oper_delete_order.setVisibility(View.GONE);
                holder.order_oper_appraise.setVisibility(View.GONE);
                holder.order_oper_cancel_order.setVisibility(View.GONE);
                holder.order_oper_pay_immediately.setVisibility(View.GONE);
                holder.order_oper_reminder_delivery.setVisibility(View.VISIBLE);
                break;
            }
            case OrderModel.ORDER_STATUS_WAITFOR_RECEIPT: {
                statusStrID = R.string.order_status_waitfor_receipt;
                holder.order_oper_extend_receipt.setVisibility(View.VISIBLE);
                holder.order_oper_search_logistics.setVisibility(View.VISIBLE);
                holder.order_oper_confirm_receipt.setVisibility(View.VISIBLE);
                holder.order_oper_delete_order.setVisibility(View.GONE);
                holder.order_oper_appraise.setVisibility(View.GONE);
                holder.order_oper_cancel_order.setVisibility(View.GONE);
                holder.order_oper_pay_immediately.setVisibility(View.GONE);
                holder.order_oper_reminder_delivery.setVisibility(View.GONE);
                break;
            }
            case OrderModel.ORDER_STATUS_WAITFOR_COMMENTS: {
                statusStrID = R.string.order_status_waitfor_comments;
                holder.order_oper_extend_receipt.setVisibility(View.GONE);
                holder.order_oper_search_logistics.setVisibility(View.GONE);
                holder.order_oper_confirm_receipt.setVisibility(View.GONE);
                holder.order_oper_delete_order.setVisibility(View.VISIBLE);
                holder.order_oper_appraise.setVisibility(View.VISIBLE);
                holder.order_oper_cancel_order.setVisibility(View.GONE);
                holder.order_oper_pay_immediately.setVisibility(View.GONE);
                holder.order_oper_reminder_delivery.setVisibility(View.GONE);
                break;
            }
            default: {

            }
        }
        holder.order_status.setText(statusStrID);

        holder.order_oper_extend_receipt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                extendReceiptByOrderCode(holder.order_code.getText().toString());
            }
        });

        holder.order_oper_search_logistics.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                searchLogisticsByOrderCode(holder.order_code.getText().toString());
            }
        });

        holder.order_oper_confirm_receipt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                confirmReceiptByOrderCode(holder.order_code.getText().toString());
            }
        });

        holder.order_oper_delete_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteByOrderCode(holder.order_code.getText().toString());
            }
        });

        holder.order_oper_appraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appraiseByOrderCode(holder.order_code.getText().toString());
            }
        });

        holder.order_oper_cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelByOrderCode(holder.order_code.getText().toString());
            }
        });

        holder.order_oper_pay_immediately.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payByOrderCode(holder.order_code.getText().toString());
            }
        });

        holder.order_oper_reminder_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminderDeliveryByOrderCode(holder.order_code.getText().toString());
            }
        });

        orderItemAdapter = new OrderItemAdapter(context, list.get(position).getOrderList());
        holder.listview.setAdapter(orderItemAdapter);
        holder.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int itemnum, long id) {
                CMLog.LogD("order item click : " + holder.order_code.getText());
            }
        });

        return convertView;
    }

    private void searchLogisticsByOrderCode(String order_code) {
        CMLog.LogD("search LC code : " + order_code);
    }

    private void confirmReceiptByOrderCode(String order_code) {
        CMLog.LogD("confirmReceipt code : " + order_code);
    }

    private void extendReceiptByOrderCode(String order_code) {
        CMLog.LogD("extend receipt code : " + order_code);
    }

    private void deleteByOrderCode(String order_code) {
        CMLog.LogD("delete order code : " + order_code);
    }

    private void appraiseByOrderCode(String order_code) {
        CMLog.LogD("appraise code : " + order_code);
    }

    private void cancelByOrderCode(String order_code) {
        CMLog.LogD("cancel code : " + order_code);
    }

    private void payByOrderCode(String order_code) {
        CMLog.LogD("pay code : " + order_code);
    }

    private void reminderDeliveryByOrderCode(String order_code) {
        CMLog.LogD("reminder delivery code : " + order_code);
    }
}

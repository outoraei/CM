package cm.demo.view.customview;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cm.demo.R;
import cm.demo.model.ItemModel;

public class OrderItemAdapter extends BaseAdapter {
    private List<ItemModel> list;
    private LayoutInflater inflater;
    private Context context;

    final class ViewHolder {
        ImageView icon;
        TextView name;
        TextView description;
        TextView value;
        TextView num;
    }

    public OrderItemAdapter(Context context, List<ItemModel> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.order_item_row, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.order_item_image);
            holder.name = (TextView) convertView.findViewById(R.id.order_item_name);
            holder.description = (TextView) convertView
                    .findViewById(R.id.order_item_description);
            holder.value = (TextView) convertView
                    .findViewById(R.id.order_item_value);
            holder.num = (TextView) convertView
                    .findViewById(R.id.order_item_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ItemModel item = list.get(position);
        holder.name.setText(context.getText(R.string.order_item_name).toString() + item.getName());
        holder.description.setText(context.getText(R.string.order_item_description).toString() + item.getDescription());
        holder.value.setText(context.getText(R.string.order_item_eachvalue).toString() + item.getValue());
        holder.num.setText(context.getText(R.string.order_item_num).toString() + item.getItem_num());

        String uri = item.getImageURI();
        if (null == uri || "".equals(uri)) {
            holder.icon.setImageResource(R.drawable.ic_launcher);
        } else {
            holder.icon.setImageURI(Uri.parse(uri));
        }

        return convertView;
    }
}

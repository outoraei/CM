package cm.demo.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cm.demo.R;
import cm.demo.model.CMInfoModel;
import cm.demo.model.MySettingModel;
import cm.demo.util.CMLog;
import cm.demo.view.base.BaseFragment;
import cm.demo.view.base.CMFragmentManager.CMFragmentType;
import cm.demo.view.commoncontrol.CMImageLoader;
import cm.demo.view.commoncontrol.CMListAdapter;

public class MyFragment extends BaseFragment {

    private int[] mySettingIcon = {R.drawable.my_selected, R.drawable.favorite_selected, R.drawable.square_selected};
    private String[] mySettingName = {"Wallet", "Save", "Setting"};

    private ImageView myImage;
    private TextView myText;
    private ListView settingList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CMLog.LogD("onCreateView");
        View myLayout = inflater.inflate(R.layout.my_layout, container, false);
        return myLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CMLog.LogD("onActivityCreated");

        myImage = (ImageView) getActivity().findViewById(R.id.my_image);
        myText = (TextView) getActivity().findViewById(R.id.my_text);

        myImage.setImageResource(R.drawable.my_selected);
        myText.setTextColor(getResources().getColor(R.color.select));

        myImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setTabSelection(CMFragmentType.CMFRAGMENT_MY);
            }
        });
        setTabSelection(CMFragmentType.CMFRAGMENT_MY);


        List<MySettingModel> dataList = new ArrayList<>();
        MySettingModel model;
        for (int i = 0; i < mySettingIcon.length; i++) {
            model = new MySettingModel();
            model.setItemIcon(mySettingIcon[i]);
            model.setItemName(mySettingName[i]);
            model.setNextscreen(R.drawable.ic_launcher);
            dataList.add(i, model);
        }

        settingList = (ListView) getActivity().findViewById(R.id.my_setting_list);
        settingList.setAdapter(new MySettingListAdapter(dataList));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        CMLog.LogD("hidden is " + hidden);
        if (myImage == null) {
            CMLog.LogD("messageImage is " + myImage);
            return;
        }
        if (myText == null) {
            CMLog.LogD("messageText is " + myText);
            return;
        }

        myImage.setImageResource(hidden ? R.drawable.my_unselected
                : R.drawable.my_selected);
        myText.setTextColor(getResources().getColor(
                hidden ? R.color.unselect : R.color.select));
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onPause() {
        CMLog.LogD("onPause");
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        CMLog.LogD("onDestroyView");
        myImage = null;
        myText = null;
        super.onDestroyView();
    }

    private final class ViewHolder {
        ImageView icon;
        TextView content;
        ImageView go;
    }

    class MySettingListAdapter extends CMListAdapter {

        private List<MySettingModel> mySettingData = null;
        private LayoutInflater inflater;
        private MySettingModel item;

        public MySettingListAdapter(List<MySettingModel> data) {
            inflater = (LayoutInflater) getActivity().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            mySettingData = data;
        }

        @Override
        public int getCount() {
            if (mySettingData == null) return 0;
            return mySettingData.size();
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
            MyFragment.ViewHolder holder;
            if (convertView == null) {
                holder = new MyFragment.ViewHolder();
                convertView = inflater.inflate(R.layout.my_setting_list_row, null);
                holder.icon = (ImageView) convertView.findViewById(R.id.my_setting_image_icon);
                holder.content = (TextView) convertView.findViewById(R.id.my_setting_text_content);
                holder.go = (ImageView) convertView.findViewById(R.id.my_setting_go);
                convertView.setTag(holder);
            } else {
                holder = (MyFragment.ViewHolder) convertView.getTag();
            }

            item = mySettingData.get(position);

            holder.icon.setImageResource(item.getItemIcon());
            holder.content.setText(item.getItemName());
            holder.go.setImageResource(item.getNextscreen());
            return convertView;
        }
    }
}
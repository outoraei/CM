package cm.demo.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import cm.demo.model.MySettingModel;
import cm.demo.util.CMLog;
import cm.demo.view.base.BaseFragment;
import cm.demo.view.base.CMFragmentManager.CMFragmentType;
import cm.demo.view.commoncontrol.CMListAdapter;
import cm.demo.view.main.CustomServiceActivity;
import cm.demo.view.main.IntroductionActivity;
import cm.demo.view.main.MyInfoActivity;
import cm.demo.view.main.OrderActivity;
import cm.demo.view.main.SettingActivity;
import cm.demo.view.main.ShoppingCartActivity;
import cm.demo.view.main.SwitchAccountActivity;
import cm.demo.view.main.VersionUpdateActivity;
import cm.demo.view.main.WalletActivity;

public class MyFragment extends BaseFragment {

    private int[] mySettingNameID = {
            R.string.my_wallet_title,
//            R.string.my_order_title,
//            R.string.my_shoppingcart_title,
            R.string.my_setting_title,
            R.string.my_versionupdate_title,
            R.string.my_introduction_title,
            R.string.my_customservice_title
    };

    private int[] mySettingIcon = {
            R.drawable.my_selected,
//            R.drawable.favorite_selected,
//            R.drawable.square_selected,
            R.drawable.favorite_unselected,
            R.drawable.my_unselected,
            R.drawable.square_unselected,
            R.drawable.ic_launcher
    };

    private ImageView myImage;
    private TextView myText;
    private ListView settingList;
    private ImageView myInfoImage;
    private TextView switchAccountTextView;

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
        myText.setTextColor(Color.BLUE);

        myImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setTabSelection(CMFragmentType.CMFRAGMENT_MY);
            }
        });
        setTabSelection(CMFragmentType.CMFRAGMENT_MY);

        myInfoImage = (ImageView) getActivity().findViewById(R.id.user_photo);
        myInfoImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToMyInfoScreen();
            }
        });

        switchAccountTextView = (TextView) getActivity().findViewById(R.id.switch_account);
        switchAccountTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToSwitchAccountScreen();
            }
        });


        List<MySettingModel> dataList = new ArrayList<MySettingModel>();
        MySettingModel model;
        for (int i = 0; i < mySettingIcon.length; i++) {
            model = new MySettingModel((Context)getActivity());
            model.setItemIcon(mySettingIcon[i]);
            model.setItemNameID(mySettingNameID[i]);
            model.setNextscreen(R.drawable.ic_launcher);
            dataList.add(i, model);
        }

        settingList = (ListView) getActivity().findViewById(R.id.my_setting_list);
        settingList.setAdapter(new MySettingListAdapter(dataList));
        settingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        goToWalletScreen();
                        break;
                    }
//                    case 1: {
//                        goToOrderScreen();
//                        break;
//                    }
//                    case 2: {
//                        goToShoppingCart();
//                        break;
//                    }
                    case 1: {
                        goToSettingScreen();
                        break;
                    }
                    case 2: {
                        goToVersionUpdateScreen();
                        break;
                    }
                    case 3: {
                        goToIntroductionScreen();
                        break;
                    }
                    case 4: {
                        goToCustomServiceScreen();
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        });
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
        myText.setTextColor(hidden ? Color.GRAY : Color.BLUE);
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
            holder.content.setText(item.getItemNameID());
            holder.go.setImageResource(item.getNextscreen());
            return convertView;
        }
    }

    public void goToMyInfoScreen() {
        CMLog.LogD("goToMyInfoScreen");
        Intent intent = new Intent();
        intent.setClass(getActivity(), MyInfoActivity.class);
        startActivity(intent);
    }

    public void goToWalletScreen() {
        CMLog.LogD("goToWalletScreen");
        Intent intent = new Intent();
        intent.setClass(getActivity(), WalletActivity.class);
        startActivity(intent);
    }

    public void goToOrderScreen() {
        CMLog.LogD("goToOrderScreen");
        Intent intent = new Intent();
        intent.setClass(getActivity(), OrderActivity.class);
        startActivity(intent);
    }

    public void goToShoppingCart() {
        CMLog.LogD("goToShoppingCart");
        Intent intent = new Intent();
        intent.setClass(getActivity(), ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void goToSettingScreen() {
        CMLog.LogD("goToSettingScreen");
        Intent intent = new Intent();
        intent.setClass(getActivity(), SettingActivity.class);
        startActivity(intent);
    }

    public void goToVersionUpdateScreen() {
        CMLog.LogD("goToVersionUpdateScreen");
        Intent intent = new Intent();
        intent.setClass(getActivity(), VersionUpdateActivity.class);
        startActivity(intent);
    }

    public void goToIntroductionScreen() {
        CMLog.LogD("goToIntroductionScreen");
        Intent intent = new Intent();
        intent.setClass(getActivity(), IntroductionActivity.class);
        startActivity(intent);
    }

    public void goToCustomServiceScreen() {
        CMLog.LogD("goToCustomServiceScreen");
        Intent intent = new Intent();
        intent.setClass(getActivity(), CustomServiceActivity.class);
        startActivity(intent);
    }

    public void goToSwitchAccountScreen() {
        CMLog.LogD("goToSwitchAccountScreen");
        Intent intent = new Intent();
        intent.setClass(getActivity(), SwitchAccountActivity.class);
        startActivity(intent);
    }

}
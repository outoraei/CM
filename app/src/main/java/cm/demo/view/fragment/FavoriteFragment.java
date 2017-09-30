package cm.demo.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cm.demo.R;
import cm.demo.model.NavigateModel;
import cm.demo.util.CMLog;
import cm.demo.view.base.BaseFragment;
import cm.demo.view.base.CMFragmentManager.CMFragmentType;
import cm.demo.view.main.VideoPlayActivity;

public class FavoriteFragment extends BaseFragment {

    private ImageView favoriteImage;
    private TextView favoriteText;
    private View favoriteLayout;
    private LinearLayout favoriteTab;
    private TextView favorite_waiting_message;
    private TextView[] tabItem = null;
    private GridView favorite_gridview = null;
    private List<Map<String, Object>> favorite_items = new ArrayList<Map<String, Object>>();
    private final String GRIDVIEW_ITEM_BG = "gridview_item_background";
    private final String GRIDVIEW_ITEM_INTRO_CONTENT = "gridview_item_intro_content";
    private final String GRIDVIEW_ITEM_DURATION = "gridview_item_duration";

    private final int TIMEOUT = 1000;

    private Handler handler = new Handler();
    private Runnable run = new Runnable() {
        @Override
        public void run() {
            if (favorite_waiting_message != null) {
                favorite_waiting_message.setVisibility(View.GONE);
            }
//            updateGridViewData();
            if (favorite_gridview != null) {
                favorite_gridview.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        favoriteLayout = inflater.inflate(R.layout.favorite_layout, container,
                false);
        return favoriteLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        favoriteImage = (ImageView) getActivity().findViewById(
                R.id.favorite_image);
        favoriteText = (TextView) getActivity()
                .findViewById(R.id.favorite_text);

        favoriteImage.setImageResource(R.drawable.favorite_unselected);
        favoriteText.setTextColor(Color.GRAY);

        favoriteImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setTabSelection(CMFragmentType.CMFRAGMENT_FAVORITE);
            }
        });

        favorite_gridview = (GridView) getActivity().findViewById(R.id.favorite_gridview);

        updateGridViewData();

        String[] from = {GRIDVIEW_ITEM_BG, GRIDVIEW_ITEM_INTRO_CONTENT, GRIDVIEW_ITEM_DURATION};
        int[] to = {R.id.gridview_item_background, R.id.gridview_item_intro_content, R.id.gridview_item_duration};
        favorite_gridview.setAdapter(new SimpleAdapter(getActivity(), favorite_items, R.layout.favorite_gridview_item, from, to));
        favorite_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), VideoPlayActivity.class);
                startActivity(intent);
            }
        });
        favorite_gridview.setVisibility(View.GONE);

        favoriteTab = (LinearLayout) getActivity().findViewById(R.id.favorite_tab);
        initTabItems();

        favorite_waiting_message = (TextView) getActivity().findViewById(R.id.favorite_waiting_message);
        if (favorite_waiting_message != null) {
            favorite_waiting_message.setVisibility(View.GONE);
        }
    }

    private void initTabItems() {
        List<NavigateModel.Node> list = NavigateModel.getInstance().getNavigateList();
        List<NavigateModel.Node> levelOneList = new ArrayList<>();

        for (int index = 0; index < list.size(); index++) {
            NavigateModel.Node item = list.get(index);
            if (NavigateModel.NAVIGATE_LEVEL.LEVEL1 == item.type) {
                levelOneList.add(item);
            }
        }

        TextView tv = null;
        tabItem = new TextView[levelOneList.size()];
        for (int index = 0; index < levelOneList.size(); index++) {
            tv = new TextView(getActivity());
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            tv.setTag(levelOneList.get(index).code);
            tv.setText("   " + levelOneList.get(index).content + "   ");
            tv.setGravity(Gravity.CENTER);
            tv.setOnClickListener(tabItemClick);
            if (favoriteTab != null) {
                favoriteTab.addView(tv);
            }
            tabItem[index] = tv;
        }
        resetTabItems();
    }

    View.OnClickListener tabItemClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            updateTabItemListByCode(v);
        }
    };

    private void updateTabItemListByCode(View v) {
        CMLog.LogD("updateTabItemListByCode , code : " + (String) v.getTag());
        resetTabItems();
        ((TextView) v).setTextColor(Color.BLACK);
        if (favorite_waiting_message != null) {
            favorite_waiting_message.setVisibility(View.VISIBLE);
        }
        if (favorite_gridview != null) {
            favorite_gridview.setVisibility(View.GONE);
        }
        handler.postDelayed(run, TIMEOUT);
    }

    private void resetTabItems() {
        for (int index = 0; index < tabItem.length; index++) {
            tabItem[index].setTextColor(Color.GRAY);
        }
    }

    private void updateGridViewData() {
        Map<String, Object> map = null;
        for (int i = 0; i < 30; i++) {
            map = new HashMap<>();
            map.put(GRIDVIEW_ITEM_BG, R.drawable.tab_bg);
            map.put(GRIDVIEW_ITEM_INTRO_CONTENT, "TestTitle" + i);
            map.put(GRIDVIEW_ITEM_DURATION, i + ":00");
            favorite_items.add(map);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (favoriteImage == null)
            return;
        if (favoriteText == null)
            return;
        favoriteImage.setImageResource(hidden ? R.drawable.favorite_unselected
                : R.drawable.favorite_selected);
        favoriteText.setTextColor(hidden ? Color.GRAY : Color.BLUE);
        if (!hidden) {
            updateTabItemListByCode(tabItem[0]);
        }
        super.onHiddenChanged(hidden);
    }
}
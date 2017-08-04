package cm.demo.view.fragment;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cm.demo.R;
import cm.demo.view.base.BaseFragment;
import cm.demo.view.base.CMFragmentManager.CMFragmentType;

public class FavoriteFragment extends BaseFragment {

    private ImageView favoriteImage;
    private TextView favoriteText;
    private View favoriteLayout;
    private LinearLayout favoriteTab;
    private TextView favorite_waiting_message;
    private TextView[] tabItem = null;

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
        favoriteText.setTextColor(getResources().getColor(R.color.unselect));

        favoriteImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setTabSelection(CMFragmentType.CMFRAGMENT_FAVORITE);
            }
        });

        favoriteTab = (LinearLayout) getActivity().findViewById(R.id.favorite_tab);
        initTabItems();

        favorite_waiting_message = (TextView) getActivity().findViewById(R.id.favorite_waiting_message);
        favorite_waiting_message.setVisibility(View.GONE);
    }

    private void initTabItems() {
        int[] tabItemString = new int[]{R.string.category_favorite_my, R.string.sex_favorite_my, R.string.age_favorite_my};

        TextView tv = null;
        tabItem = new TextView[tabItemString.length];
        for (int index = 0; index < tabItemString.length; index++) {
            tv = new TextView(getActivity());
            tv.setText(tabItemString[index]);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetTabItems();
                    ((TextView)v).setTextColor(Color.BLACK);
                    favorite_waiting_message.setVisibility(View.VISIBLE);
                }
            });
            favoriteTab.addView(tv);
            tabItem[index] = tv;
        }
        resetTabItems();
    }

    private void resetTabItems() {
        for (int index = 0; index < tabItem.length; index++) {
            tabItem[index].setTextColor(Color.GRAY);
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
        favoriteText.setTextColor(getResources().getColor(
                hidden ? R.color.unselect : R.color.select));
        super.onHiddenChanged(hidden);
    }
}
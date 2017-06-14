package cm.demo.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cm.demo.R;
import cm.demo.view.base.BaseFragment;
import cm.demo.view.base.CMFragmentManager.CMFragmentType;

public class FavoriteFragment extends BaseFragment {

    private ImageView favoriteImage;
    private TextView favoriteText;
    View favoriteLayout;

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
package cm.demo.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cm.demo.R;
import cm.demo.view.base.BaseFragment;

public class EntryIntroTwoFragment extends BaseFragment {

    private ImageView favoriteImage;
    private TextView favoriteText;
    View favoriteLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.entry_intro_two_layout, container,
                false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
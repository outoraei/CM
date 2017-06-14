package cm.demo.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cm.demo.R;
import cm.demo.util.CMLog;
import cm.demo.view.base.BaseFragment;
import cm.demo.view.base.CMFragmentManager.CMFragmentType;

public class MyFragment extends BaseFragment {

    private ImageView myImage;
    private TextView myText;

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
}
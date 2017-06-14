package cm.demo.view.base;

import android.app.Fragment;
import android.os.Bundle;

import cm.demo.util.CMLog;
import cm.demo.view.base.CMFragmentManager.CMFragmentType;

public class BaseFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setTabSelection(CMFragmentType type) {
        CMFragmentManager.getInstatnce().setTabSelection(getActivity(), type);
    }

    protected void updateContent() {
        CMLog.LogD("updateContent CMFragment parent");
    }
}

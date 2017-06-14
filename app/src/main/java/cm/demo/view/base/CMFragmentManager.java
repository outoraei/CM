package cm.demo.view.base;

import android.app.Activity;
import android.app.FragmentTransaction;

import cm.demo.R;
import cm.demo.util.CMLog;
import cm.demo.view.fragment.FavoriteFragment;
import cm.demo.view.fragment.MyFragment;
import cm.demo.view.fragment.SquareFragment;

public class CMFragmentManager {

    public enum CMFragmentType {
        CMFRAGMENT_MY, CMFRAGMENT_FAVORITE, CMFRAGMENT_SQUARE,
    }

    private static CMFragmentManager instance = new CMFragmentManager();

    public static CMFragmentManager getInstatnce() {
        return instance;
    }


    public void initMainActivityFragment(Activity context) {
        FragmentTransaction transaction = context.getFragmentManager()
                .beginTransaction();

        createFragment(CMFragmentType.CMFRAGMENT_SQUARE);
        createFragment(CMFragmentType.CMFRAGMENT_FAVORITE);
        createFragment(CMFragmentType.CMFRAGMENT_MY);

        transaction.add(R.id.content, squareFragment);
        transaction.add(R.id.content, favoriteFragment);
        transaction.add(R.id.content, myFragment);

        transaction.hide(squareFragment);
        transaction.hide(favoriteFragment);
        transaction.hide(myFragment);

        transaction.commit();
    }

    public void setTabSelection(Activity context, CMFragmentType type) {
        FragmentTransaction transaction = context.getFragmentManager()
                .beginTransaction();

        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }

        BaseFragment fragment = CMFragmentManager.getInstatnce()
                .checkFragmentExist(type);
        if (null == fragment) {
            fragment = CMFragmentManager.getInstatnce().createFragment(type);
            transaction.add(R.id.content, fragment);
        } else {
            transaction.show(fragment);
            fragment.updateContent();
        }
        currentFragment = fragment;
        transaction.commit();
    }

    public BaseFragment checkFragmentExist(CMFragmentType type) {
        switch (type) {
            case CMFRAGMENT_FAVORITE:
                return favoriteFragment;
            case CMFRAGMENT_MY:
                return myFragment;
            case CMFRAGMENT_SQUARE:
                return squareFragment;
            default:
                break;
        }
        return null;
    }

    public BaseFragment createFragment(CMFragmentType type) {
        switch (type) {
            case CMFRAGMENT_FAVORITE:
                return favoriteFragment = new FavoriteFragment();
            case CMFRAGMENT_MY:
                return myFragment = new MyFragment();
            case CMFRAGMENT_SQUARE:
                return squareFragment = new SquareFragment();
            default:
                break;
        }
        CMLog.LogE("Not support Fragment is create, type = " + type);
        return new BaseFragment();
    }

    private FavoriteFragment favoriteFragment = null;
    private MyFragment myFragment = null;
    private SquareFragment squareFragment = null;
    private BaseFragment currentFragment = null;
}

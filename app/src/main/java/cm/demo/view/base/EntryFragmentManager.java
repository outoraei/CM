package cm.demo.view.base;

import android.app.Activity;
import android.app.FragmentTransaction;

import cm.demo.R;
import cm.demo.util.CMLog;
import cm.demo.view.fragment.EntryIntroOneFragment;
import cm.demo.view.fragment.EntryIntroThreeFragment;
import cm.demo.view.fragment.EntryIntroTwoFragment;

public class EntryFragmentManager {

    public enum EntryFragmentType {
        ENTRYFRAGMENT_INTRO_NONE, ENTRYFRAGMENT_INTRO_ONE, ENTRYFRAGMENT_INTRO_TWO, ENTRYFRAGMENT_INTRO_THREE,
    }

    private static EntryFragmentManager instance = new EntryFragmentManager();

    public static EntryFragmentManager getInstatnce() {
        return instance;
    }

    public void initEntryActivityFragment(Activity context) {
        FragmentTransaction transaction = context.getFragmentManager()
                .beginTransaction();

        createFragment(EntryFragmentType.ENTRYFRAGMENT_INTRO_ONE);
        createFragment(EntryFragmentType.ENTRYFRAGMENT_INTRO_TWO);
        createFragment(EntryFragmentType.ENTRYFRAGMENT_INTRO_THREE);

        transaction.add(R.id.entry_content, entryIntroOneFragment);
        transaction.add(R.id.entry_content, entryIntroTwoFragment);
        transaction.add(R.id.entry_content, entryIntroThreeFragment);

        transaction.hide(entryIntroOneFragment);
        transaction.hide(entryIntroTwoFragment);
        transaction.hide(entryIntroThreeFragment);

        transaction.commit();
    }

    public void flingEntryFragments(Activity context, EntryFragmentType type) {
        FragmentTransaction transaction = context.getFragmentManager()
                .beginTransaction();

        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }

        BaseFragment fragment = EntryFragmentManager.getInstatnce()
                .checkFragmentExist(type);
        if (null == fragment) {
            fragment = EntryFragmentManager.getInstatnce().createFragment(type);
            transaction.add(R.id.entry_content, fragment);
        } else {
            transaction.show(fragment);
            fragment.updateContent();
        }
        currentFragment = fragment;
        transaction.commit();
    }

    public void flingNextFragment(Activity context) {
        CMLog.LogD("flingNextFragment");
        EntryFragmentType fragmentType = EntryFragmentType.ENTRYFRAGMENT_INTRO_NONE;
        if (currentFragment instanceof EntryIntroOneFragment) {
            fragmentType = EntryFragmentType.ENTRYFRAGMENT_INTRO_ONE;
        } else if (currentFragment instanceof EntryIntroTwoFragment) {
            fragmentType = EntryFragmentType.ENTRYFRAGMENT_INTRO_TWO;
        } else if (currentFragment instanceof EntryIntroThreeFragment) {
            fragmentType = EntryFragmentType.ENTRYFRAGMENT_INTRO_THREE;
        } else {
            CMLog.LogD("error currentFragment none instanceof EntryFragment");
        }

        CMLog.LogD("currentFragment type is : " + fragmentType);

        if (EntryFragmentType.ENTRYFRAGMENT_INTRO_NONE != fragmentType && EntryFragmentType.ENTRYFRAGMENT_INTRO_THREE != fragmentType) {
            if (EntryFragmentType.ENTRYFRAGMENT_INTRO_ONE == fragmentType) {
                CMLog.LogD("flingNextFragment INTRO_TWO");
                flingEntryFragments(context, EntryFragmentType.ENTRYFRAGMENT_INTRO_TWO);
            } else if (EntryFragmentType.ENTRYFRAGMENT_INTRO_TWO == fragmentType) {
                CMLog.LogD("flingNextFragment INTRO_THREE");
                flingEntryFragments(context, EntryFragmentType.ENTRYFRAGMENT_INTRO_THREE);
            }
        }
    }

    public void flingPreFragment(Activity context) {
        CMLog.LogD("flingPreFragment");
        EntryFragmentType fragmentType = EntryFragmentType.ENTRYFRAGMENT_INTRO_NONE;
        if (currentFragment instanceof EntryIntroOneFragment) {
            fragmentType = EntryFragmentType.ENTRYFRAGMENT_INTRO_ONE;
        } else if (currentFragment instanceof EntryIntroTwoFragment) {
            fragmentType = EntryFragmentType.ENTRYFRAGMENT_INTRO_TWO;
        } else if (currentFragment instanceof EntryIntroThreeFragment) {
            fragmentType = EntryFragmentType.ENTRYFRAGMENT_INTRO_THREE;
        } else {
            CMLog.LogD("error currentFragment none instanceof EntryFragment");
        }

        CMLog.LogD("currentFragment type is : " + fragmentType);

        if (EntryFragmentType.ENTRYFRAGMENT_INTRO_NONE != fragmentType && EntryFragmentType.ENTRYFRAGMENT_INTRO_ONE != fragmentType) {
            if (EntryFragmentType.ENTRYFRAGMENT_INTRO_TWO == fragmentType) {
                CMLog.LogD("flingPreFragment INTRO_ONE");
                flingEntryFragments(context, EntryFragmentType.ENTRYFRAGMENT_INTRO_ONE);
            } else if (EntryFragmentType.ENTRYFRAGMENT_INTRO_THREE == fragmentType) {
                CMLog.LogD("flingPreFragment INTRO_TWO");
                flingEntryFragments(context, EntryFragmentType.ENTRYFRAGMENT_INTRO_TWO);
            }
        }
    }

    public BaseFragment checkFragmentExist(EntryFragmentType type) {
        switch (type) {
            case ENTRYFRAGMENT_INTRO_ONE:
                return entryIntroOneFragment;
            case ENTRYFRAGMENT_INTRO_TWO:
                return entryIntroTwoFragment;
            case ENTRYFRAGMENT_INTRO_THREE:
                return entryIntroThreeFragment;
            default:
                break;
        }
        return null;
    }

    public BaseFragment createFragment(EntryFragmentType type) {
        switch (type) {
            case ENTRYFRAGMENT_INTRO_ONE:
                return entryIntroOneFragment = new EntryIntroOneFragment();
            case ENTRYFRAGMENT_INTRO_TWO:
                return entryIntroTwoFragment = new EntryIntroTwoFragment();
            case ENTRYFRAGMENT_INTRO_THREE:
                return entryIntroThreeFragment = new EntryIntroThreeFragment();
            default:
                break;
        }
        CMLog.LogE("Not support Fragment is create, type = " + type);
        return new BaseFragment();
    }

    private EntryIntroOneFragment entryIntroOneFragment = null;
    private EntryIntroTwoFragment entryIntroTwoFragment = null;
    private EntryIntroThreeFragment entryIntroThreeFragment = null;
    private BaseFragment currentFragment = null;
}

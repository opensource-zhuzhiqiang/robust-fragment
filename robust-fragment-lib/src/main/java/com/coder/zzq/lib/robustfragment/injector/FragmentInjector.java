package com.coder.zzq.lib.robustfragment.injector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.coder.zzq.lib.robustfragment.RobustFragmentRetriever;
import com.coder.zzq.lib.robustfragment.Utils;

public class FragmentInjector {
    private FragmentOptions mFragmentOptions;

    protected boolean mAddToBackStack;
    protected String mBackStackEntryName;
    protected String mTransformedBackStackEntryName;

    public FragmentInjector() {

    }

    public FragmentInjector fragment(IFragmentOption fragmentOptions) {
        mFragmentOptions = (FragmentOptions) fragmentOptions;
        return this;
    }

    private String getTransformedBackStackEntryName() {
        if (mTransformedBackStackEntryName == null) {
            mTransformedBackStackEntryName = Utils.parseRobustFragmentBackEntryName(mFragmentOptions.getFragmentClass(),
                    mFragmentOptions.getContainerId(),
                    mFragmentOptions.getTag(),
                    mBackStackEntryName);
        }
        return mTransformedBackStackEntryName;
    }

    public FragmentInjector addToBackStack(boolean addToBackStack) {
        mAddToBackStack = addToBackStack;
        return this;
    }

    public FragmentInjector backStackEntryName(String backStackEntryName) {
        mBackStackEntryName = (backStackEntryName == null) ? "" : backStackEntryName.trim();
        mTransformedBackStackEntryName = null;
        return this;
    }


    public int addInto(AppCompatActivity activity) {
        return addInto(activity.getSupportFragmentManager());
    }

    public int addInto(Fragment fragment) {
        return addInto(fragment.getChildFragmentManager());
    }

    private int addInto(FragmentManager fragmentManager) {
        int backStackId = -1;
        Fragment addedFragment = RobustFragmentRetriever.createOrRetrieveFragment(fragmentManager, mFragmentOptions);

        if (!mFragmentOptions.isAlreadyAddedToFragmentManager()) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (mAddToBackStack) {
                transaction.addToBackStack(getTransformedBackStackEntryName());
            }
            backStackId = transaction
                    .add(mFragmentOptions.getContainerId(), addedFragment, mFragmentOptions.getTag())
                    .commit();
        } else if (mAddToBackStack) {
            for (int index = 0; index < fragmentManager.getBackStackEntryCount(); index++) {
                FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(index);
                if (entry.getName().equals(getTransformedBackStackEntryName())) {
                    backStackId = entry.getId();
                    break;
                }
            }
        }

        return backStackId;
    }
}

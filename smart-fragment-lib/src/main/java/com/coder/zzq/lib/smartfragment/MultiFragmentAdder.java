package com.coder.zzq.lib.smartfragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MultiFragmentAdder {

    private FragmentWrapper[] mFragmentWrappers;
    @IdRes
    private int mContainerId = View.NO_ID;

    public MultiFragmentAdder fragments(FragmentWrapper... wrappers) {
        mFragmentWrappers = wrappers;
        return this;
    }

    public Fragment[] addInto(AppCompatActivity activity, Bundle bundleOfActivity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        Fragment[] fragments = new Fragment[mFragmentWrappers.length];
        for (int index = 0; index < mFragmentWrappers.length; index++) {
            Fragment fragment = null;
            FragmentWrapper fragmentWrapper = mFragmentWrappers[index];
            if (bundleOfActivity != null) {
                fragment = fragmentManager.findFragmentByTag(fragmentWrapper.getTag());
            } else {
                try {
                    fragment = fragmentWrapper.getFragmentClass().newInstance();
                    fragment.setArguments(fragmentWrapper.getFragmentArgs());
                    fragments[index] = fragment;
                    fragmentManager.beginTransaction()
                            .add(mContainerId, fragment, fragmentWrapper.getTag())
                            .commit();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return fragments;
    }
}

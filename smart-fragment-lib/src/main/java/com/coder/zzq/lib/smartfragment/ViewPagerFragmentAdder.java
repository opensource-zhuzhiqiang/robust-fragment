package com.coder.zzq.lib.smartfragment;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;

public class ViewPagerFragmentAdder {
    private FragmentWrapper[] mFragmentWrappers;
    private int mViewPagerId = View.NO_ID;

    public ViewPagerFragmentAdder() {

    }

    public ViewPagerFragmentAdder fragments(FragmentWrapper... wrappers) {
        mFragmentWrappers = wrappers;
        return this;
    }

    public ViewPagerFragmentAdder viewPagerId(@IdRes int viewPagerId) {
        mViewPagerId = viewPagerId;
        return this;
    }

    public Fragment getItem(int position) {
        Fragment fragment = null;
        try {
            fragment = mFragmentWrappers[position].getFragmentClass().newInstance();
            fragment.setArguments(mFragmentWrappers[position].getFragmentArgs());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fragment;
    }
}

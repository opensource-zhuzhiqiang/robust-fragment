package com.coder.zzq.lib.robustfragment;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

public class ViewPagerFragmentAdder {
    private FragmentInjector[] mFragmentInjectors;
    private int mViewPagerId = View.NO_ID;

    public ViewPagerFragmentAdder() {

    }

    public ViewPagerFragmentAdder fragments(FragmentInjector... wrappers) {
        mFragmentInjectors = wrappers;
        return this;
    }

    public ViewPagerFragmentAdder viewPagerId(@IdRes int viewPagerId) {
        mViewPagerId = viewPagerId;
        return this;
    }

    public Fragment getItem(int position) {
        Fragment fragment = null;
//        try {
//            fragment = mSingleFragmentInjectors[position].getFragmentClass().newInstance();
//            fragment.setArguments(mSingleFragmentInjectors[position].getFragmentArgs());
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
        return fragment;
    }
}

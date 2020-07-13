package com.coder.zzq.lib.robustfragment.viewpager;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class RobustFragmentStatePageAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragmentList;

    public RobustFragmentStatePageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        ensureFragmentListCreated(position);
        mFragmentList.set(position, fragment);
        return fragment;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        ensureFragmentListCreated(position);
        mFragmentList.set(position, null);
    }

    public void ensureFragmentListCreated(int position) {
        if (mFragmentList == null) {
            mFragmentList = new ArrayList<>(getCount());
        }

        while (mFragmentList.size() <= position) {
            mFragmentList.add(null);
        }
    }
}

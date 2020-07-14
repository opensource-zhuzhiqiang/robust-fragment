package com.coder.zzq.lib.robustfragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.coder.zzq.lib.robustfragment.viewpager.IRobustPageAdapter;

public final class FragmentMaster {
    public static FragmentOperations operations(FragmentManager fragmentManager) {
        return new FragmentOperations(fragmentManager);
    }

    public static Fragment getFragmentItemFromViewPager(ViewPager viewPager, int position) {
        return ((IRobustPageAdapter) viewPager.getAdapter()).getFragmentItem(position);
    }

    public static Fragment getCurrentFragmentItemFromViewPager(ViewPager viewPager) {
        return getFragmentItemFromViewPager(viewPager, viewPager.getCurrentItem());
    }
}

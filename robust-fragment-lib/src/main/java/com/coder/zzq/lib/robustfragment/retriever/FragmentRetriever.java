package com.coder.zzq.lib.robustfragment.retriever;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.coder.zzq.lib.robustfragment.viewpager.IRobustPagerAdapter;


@SuppressWarnings("unchecked")
public class FragmentRetriever {


    public static <F extends Fragment> F retrieveFrom(AppCompatActivity hostActivity, @IdRes int containerId) {
        return (F) hostActivity.getSupportFragmentManager().findFragmentById(containerId);
    }

    public static <F extends Fragment> F retrieveFrom(AppCompatActivity hostActivity, String tag) {
        return (F) hostActivity.getSupportFragmentManager().findFragmentByTag(tag);
    }

    public static <F extends Fragment> F retrieveFrom(Fragment parentFragment, @IdRes int containerId) {
        return (F) parentFragment.getChildFragmentManager().findFragmentById(containerId);
    }

    public static <F extends Fragment> F retrieveFrom(Fragment parentFragment, String tag) {
        return (F) parentFragment.getChildFragmentManager().findFragmentByTag(tag);
    }

    private static <F extends Fragment> F retrieveFrom(FragmentManager manager, @IdRes int containerId) {
        return (F) manager.findFragmentById(containerId);
    }

    private static <F extends Fragment> F retrieveFrom(FragmentManager manager, String tag) {
        return (F) manager.findFragmentByTag(tag);
    }

    public static <F extends Fragment> F retrieveFrom(IRobustPagerAdapter robustPageAdapter, int position) {
        return (F) robustPageAdapter.getFragmentItem(position);
    }

    public static <F extends Fragment> F retrieveFrom(ViewPager viewPager, int position) {

        if (viewPager.getAdapter() instanceof IRobustPagerAdapter) {
            retrieveFrom((IRobustPagerAdapter) viewPager.getAdapter(), position);
        }

        throw new IllegalStateException("the adapter of the view page is not valid,cause it don't implement IRobustPageAdapter interface");
    }
}

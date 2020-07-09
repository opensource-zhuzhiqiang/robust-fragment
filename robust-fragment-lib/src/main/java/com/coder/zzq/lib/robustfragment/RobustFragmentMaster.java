package com.coder.zzq.lib.robustfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public final class RobustFragmentMaster {
    public static FragmentInjector singleInjector() {
        return new FragmentInjector();
    }

    private static <F extends Fragment> F findFragmentById(FragmentManager fragmentManager, int id) {
        return (F) fragmentManager.findFragmentById(id);
    }

    private static <F extends Fragment> F findFragmentByTag(FragmentManager fragmentManager, String tag) {
        return (F) fragmentManager.findFragmentByTag(tag);
    }


    public static <F extends Fragment> F findFragmentById(AppCompatActivity activity, int id) {
        return findFragmentById(activity.getSupportFragmentManager(), id);
    }

    public static <F extends Fragment> F findFragmentByTag(AppCompatActivity activity, String tag) {
        return findFragmentByTag(activity.getSupportFragmentManager(), tag);
    }


    public static <F extends Fragment> F findFragmentById(Fragment fragment, int id) {
        return findFragmentById(fragment.getChildFragmentManager(), id);
    }

    public static <F extends Fragment> F findFragmentByTag(Fragment fragment, String tag) {
        return findFragmentByTag(fragment.getChildFragmentManager(), tag);
    }

}

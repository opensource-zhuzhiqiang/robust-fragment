package com.coder.zzq.lib.robustfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.coder.zzq.lib.robustfragment.injector.FragmentOptions;

public class RobustFragmentRetriever {
    public static Fragment createOrRetrieveFragment(FragmentManager fragmentManager, FragmentOptions fragmentOptions) {
        Fragment fragment = fragmentOptions.getTag().isEmpty()
                ? fragmentManager.findFragmentById(fragmentOptions.getContainerId())
                : fragmentManager.findFragmentByTag(fragmentOptions.getTag());
        fragmentOptions.setAlreadyAddedToFragmentManager(fragment != null);
        if (fragment == null) {
            try {
                fragment = fragmentOptions.getFragmentClass().newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            fragment.setArguments(fragmentOptions.getArguments());
        }

        return fragment;
    }

    public static Fragment createOrRetrieveFragment(AppCompatActivity activity, FragmentOptions fragmentOptions) {
        return createOrRetrieveFragment(activity.getSupportFragmentManager(), fragmentOptions);
    }

    public static Fragment createOrRetrieveFragment(Fragment fragment, FragmentOptions fragmentOptions) {
        return createOrRetrieveFragment(fragment, fragmentOptions);
    }

}

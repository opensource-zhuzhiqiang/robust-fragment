package com.coder.zzq.lib.robustfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentInjector {
    private static final String ROBUST_FRAGMENT_ARGUMENT_BACK_ENTRY_ID = "robust_fragment_argument_back_entry_id";
    private FragmentOptions mFragmentOptions;

    public FragmentInjector() {

    }

    public FragmentInjector fragment(FragmentOptions fragmentOptions) {
        mFragmentOptions = fragmentOptions;
        return this;
    }

    public int addInto(AppCompatActivity activity) {
        return addInto(activity.getSupportFragmentManager());
    }


    public int addInto(Fragment fragment) {
        return addInto(fragment.getChildFragmentManager());
    }


    private int addInto(@NonNull FragmentManager fragmentManager) {
        int backStackEntryId = -1;
        Fragment fragment = mFragmentOptions.isUniqueOwnerOfContainer()
                ? fragmentManager.findFragmentById(mFragmentOptions.getContainerId())
                : fragmentManager.findFragmentByTag(mFragmentOptions.getTag());
        if (fragment == null) {
            try {
                fragment = mFragmentOptions.getFragmentClass().newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (mFragmentOptions.isAddToBackStack()) {
                transaction.addToBackStack(Utils.parseRobustFragmentBackEntryName(mFragmentOptions.getFragmentClass(), mFragmentOptions.getBackEntryName()));
            }

            fragment.setArguments(mFragmentOptions.getArguments());

            backStackEntryId = transaction.add(mFragmentOptions.getContainerId(), fragment, mFragmentOptions.getTag()).commit();

            fragment.getArguments().putInt(ROBUST_FRAGMENT_ARGUMENT_BACK_ENTRY_ID, backStackEntryId);

        } else {
            backStackEntryId = fragment.getArguments().getInt(ROBUST_FRAGMENT_ARGUMENT_BACK_ENTRY_ID);
        }


        return backStackEntryId;
    }
}

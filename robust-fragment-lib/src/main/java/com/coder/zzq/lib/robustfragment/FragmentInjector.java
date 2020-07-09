package com.coder.zzq.lib.robustfragment;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentInjector {
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
                : fragmentManager.findFragmentByTag(Utils.parseRobustFragmentTag(mFragmentOptions.getFragmentClass(),mFragmentOptions.getTag()));
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
                transaction.addToBackStack(mFragmentOptions.getBackEntryName());
            }

            fragment.setArguments(mFragmentOptions.getArguments());


            String fragmentTag = Utils.parseRobustFragmentTag(mFragmentOptions.getFragmentClass(), mFragmentOptions.getTag());
            backStackEntryId = transaction.add(mFragmentOptions.getContainerId(), fragment, fragmentTag).commit();

            fragment.getArguments().putInt("backEntryId", backStackEntryId);

        } else {
            backStackEntryId = fragment.getArguments().getInt("backEntryId");
            Log.d("test","entryid#" + backStackEntryId);
        }


        return backStackEntryId;
    }
}

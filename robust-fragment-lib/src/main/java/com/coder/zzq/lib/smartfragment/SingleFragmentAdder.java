package com.coder.zzq.lib.smartfragment;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class SingleFragmentAdder<F extends Fragment> {
    private Class<F> mFragmentClass;
    private Bundle mFragmentArgs;
    @IdRes
    private int mContainerId;

    public SingleFragmentAdder() {

    }

    public SingleFragmentAdder<F> fragmentClass(@NonNull Class<F> fragmentClass) {
        mFragmentClass = fragmentClass;
        return this;
    }

    public SingleFragmentAdder<F> args(Bundle fragmentArgs) {
        mFragmentArgs = fragmentArgs;
        return this;
    }

    public SingleFragmentAdder<F> containerId(@IdRes int containerId) {
        mContainerId = containerId;
        return this;
    }


    public F addInto(AppCompatActivity activity, Bundle bundleOfActivity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        F fragment = null;
        if (bundleOfActivity != null) {
            fragment = (F) fragmentManager.findFragmentById(mContainerId);
            return fragment;
        }

        try {
            fragment = mFragmentClass.newInstance();
            fragmentManager.beginTransaction()
                    .add(mContainerId, fragment)
                    .commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return fragment;
    }
}

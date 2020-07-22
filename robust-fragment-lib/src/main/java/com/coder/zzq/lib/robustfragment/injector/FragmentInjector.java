package com.coder.zzq.lib.robustfragment.injector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.coder.zzq.lib.robustfragment.options.FragmentOptions;
import com.coder.zzq.lib.robustfragment.options.IFragmentOptions;

public class FragmentInjector {
    private FragmentOptions mFragmentOptions;
    private OnCreateFragmentCallback mOnCreateFragmentCallback;

    public FragmentInjector(OnCreateFragmentCallback onCreateFragmentCallback) {
        mOnCreateFragmentCallback = onCreateFragmentCallback;
    }

    public FragmentInjector options(IFragmentOptions fragmentOptions) {
        mFragmentOptions = (FragmentOptions) fragmentOptions;
        return this;
    }

    public void addInto(AppCompatActivity activity) {
        addInto(activity.getSupportFragmentManager());
    }

    public void addInto(Fragment parentFragment) {
        addInto(parentFragment.getChildFragmentManager());
    }

    private void addInto(FragmentManager fragmentManager) {
        Fragment fragment = mOnCreateFragmentCallback.onCreate();
        fragment.setArguments(mFragmentOptions.getArguments());
        fragmentManager.beginTransaction()
                .add(mFragmentOptions.getContainerId(), fragment, mFragmentOptions.getTag())
                .commit();
        mFragmentOptions = null;
        mOnCreateFragmentCallback = null;
    }
}

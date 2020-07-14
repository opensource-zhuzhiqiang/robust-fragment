package com.coder.zzq.lib.robustfragment.options;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

import com.coder.zzq.lib.robustfragment.RobustUtils;

public final class FragmentOptions implements IFragmentOptions {
    private Class<? extends Fragment> mFragmentClass;
    private Bundle mArguments;
    @IdRes
    private int mContainerId;
    private String mTag;
    private String mTransformedTag;
    private boolean alreadyAddedToFragmentManager;

    private FragmentOptions() {

    }

    public static IFragmentOptions create() {
        return new FragmentOptions();
    }

    @Override
    public FragmentOptions fragmentClass(Class<? extends Fragment> fragmentClass) {
        mFragmentClass = com.coder.zzq.toolkit.Utils.requireNonNull(fragmentClass, "the fragment class must be not null");
        return this;
    }

    @Override
    public FragmentOptions intArgument(String argName, int argValue) {
        ensureArgumentsBundleCreated();
        mArguments.putInt(argName, argValue);
        return this;
    }

    @Override
    public FragmentOptions stringArgument(String argName, String argValue) {
        ensureArgumentsBundleCreated();
        mArguments.putString(argName, argValue);
        return this;
    }

    @Override
    public FragmentOptions arguments(Bundle arguments) {
        ensureArgumentsBundleCreated();
        mArguments.putAll(arguments);
        return this;
    }

    private void ensureArgumentsBundleCreated() {
        if (mArguments == null) {
            mArguments = new Bundle();
        }
    }

    @Override
    public FragmentOptions containerId(@IdRes int containerId) {
        mContainerId = containerId;
        return this;
    }

    @Override
    public FragmentOptions tag(String tag) {
        mTag = (tag == null) ? "" : tag.trim();
        mTransformedTag = null;
        return this;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return mFragmentClass;
    }

    public Bundle getArguments() {
        return mArguments;
    }

    public int getContainerId() {
        return mContainerId;
    }

    public String getTag() {
        return mTag;
    }

    public String getTransformedTag() {
        if (mTransformedTag == null) {
            mTransformedTag = RobustUtils.parseRobustFragmentTag(mFragmentClass, mContainerId, mTag);
        }

        return mTransformedTag;
    }


    public void setAlreadyAddedToFragmentManager(boolean alreadyAddedToFragmentManager) {
        this.alreadyAddedToFragmentManager = alreadyAddedToFragmentManager;
    }

    public boolean isAlreadyAddedToFragmentManager() {
        return alreadyAddedToFragmentManager;
    }
}

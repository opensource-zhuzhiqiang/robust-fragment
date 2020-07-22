package com.coder.zzq.lib.robustfragment.options;

import android.os.Bundle;

import androidx.annotation.IdRes;

public final class FragmentOptions implements IFragmentOptions {
    private Bundle mArguments;
    @IdRes
    private int mContainerId;
    private String mTag;
    private boolean alreadyAddedToFragmentManager;

    private FragmentOptions() {

    }

    public static IFragmentOptions create() {
        return new FragmentOptions();
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
    public IFragmentOptions booleanArgument(String argName, boolean argValue) {
        ensureArgumentsBundleCreated();
        mArguments.putBoolean(argName, argValue);
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
        return this;
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


    public void setAlreadyAddedToFragmentManager(boolean alreadyAddedToFragmentManager) {
        this.alreadyAddedToFragmentManager = alreadyAddedToFragmentManager;
    }

    public boolean isAlreadyAddedToFragmentManager() {
        return alreadyAddedToFragmentManager;
    }
}

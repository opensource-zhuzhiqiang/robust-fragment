package com.coder.zzq.lib.robustfragment;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

public class FragmentOptions {
    private Class<? extends Fragment> mFragmentClass;
    private Bundle mArguments;
    @IdRes
    private int mContainerId;
    boolean mUniqueOwnerOfContainer = true;
    private String mTag;
    private String mTransformedTag;
    private boolean mAddToBackStack = false;
    private String mBackEntryName;

    public static FragmentOptions create() {
        return new FragmentOptions();
    }

    public FragmentOptions fragmentClass(Class<? extends Fragment> fragmentClass) {
        mFragmentClass = fragmentClass;
        return this;
    }

    public FragmentOptions intArgument(String argName, int argValue) {
        ensureArgumentsBundleCreated();
        mArguments.putInt(argName, argValue);
        return this;
    }

    public FragmentOptions stringArgument(String argName, String argValue) {
        ensureArgumentsBundleCreated();
        mArguments.putString(argName, argValue);
        return this;
    }

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

    public FragmentOptions container(@IdRes int containerId, boolean uniqueOwnerOfContainer) {
        mContainerId = containerId;
        mUniqueOwnerOfContainer = uniqueOwnerOfContainer;
        return this;
    }

    public FragmentOptions tag(String tag) {
        mTag = tag;
        mTransformedTag = null;
        return this;
    }

    public FragmentOptions addToBackStack(boolean addToBackStack, String backEntryName) {
        mAddToBackStack = addToBackStack;
        mBackEntryName = backEntryName;
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
        if (mTransformedTag == null) {
            mTransformedTag = Utils.parseRobustFragmentTag(mFragmentClass, mContainerId, mTag);
        }

        return mTransformedTag;
    }

    public boolean isAddToBackStack() {
        return mAddToBackStack;
    }

    public String getBackEntryName() {
        return mBackEntryName;
    }

    public boolean isUniqueOwnerOfContainer() {
        return mUniqueOwnerOfContainer;
    }
}

package com.coder.zzq.lib.robustfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class FragmentWrapper {
    private Class<? extends Fragment> mFragmentClass;
    private Bundle mFragmentArgs;
    private String mTag;

    public Class<? extends Fragment> getFragmentClass() {
        return mFragmentClass;
    }

    public FragmentWrapper setFragmentClass(Class<? extends Fragment> fragmentClass) {
        mFragmentClass = fragmentClass;
        return this;
    }

    public Bundle getFragmentArgs() {
        return mFragmentArgs;
    }

    public FragmentWrapper setFragmentArgs(Bundle fragmentArgs) {
        mFragmentArgs = fragmentArgs;
        return this;
    }

    public String getTag() {
        return mTag;
    }

    public FragmentWrapper setTag(String tag) {
        mTag = tag;
        return this;
    }
}

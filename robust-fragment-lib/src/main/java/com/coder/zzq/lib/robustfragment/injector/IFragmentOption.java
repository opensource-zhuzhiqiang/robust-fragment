package com.coder.zzq.lib.robustfragment.injector;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

public interface IFragmentOption {

    IFragmentOption fragmentClass(Class<? extends Fragment> fragmentClass);

    IFragmentOption intArgument(String argName, int argValue);

    IFragmentOption stringArgument(String argName, String argValue);

    IFragmentOption arguments(Bundle arguments);


    IFragmentOption container(@IdRes int containerId);

    IFragmentOption tag(String tag);
}

package com.coder.zzq.lib.robustfragment.options;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

public interface IFragmentOptions {

    IFragmentOptions fragmentClass(Class<? extends Fragment> fragmentClass);

    IFragmentOptions intArgument(String argName, int argValue);

    IFragmentOptions stringArgument(String argName, String argValue);

    IFragmentOptions arguments(Bundle arguments);


    IFragmentOptions containerId(@IdRes int containerId);

    IFragmentOptions tag(String tag);
}

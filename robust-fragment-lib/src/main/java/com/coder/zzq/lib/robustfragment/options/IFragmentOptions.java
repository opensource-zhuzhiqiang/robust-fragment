package com.coder.zzq.lib.robustfragment.options;

import android.os.Bundle;

import androidx.annotation.IdRes;

public interface IFragmentOptions {

    IFragmentOptions intArgument(String argName, int argValue);

    IFragmentOptions stringArgument(String argName, String argValue);

    IFragmentOptions booleanArgument(String argName, boolean argValue);

    IFragmentOptions arguments(Bundle arguments);


    IFragmentOptions containerId(@IdRes int containerId);

    IFragmentOptions tag(String tag);
}

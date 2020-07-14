package com.coder.zzq.lib.robustfragment;

import androidx.fragment.app.Fragment;

public class RobustUtils {
    public static String parseRobustFragmentTag(Class<? extends Fragment> fragmentClass, int containerId, String tag) {
        return "Robust#FragTag#" + fragmentClass.getName() + "#" + containerId + "#" + tag;
    }

    public static String parseRobustFragmentBackEntryName(Class<? extends Fragment> fragmentClass, int containerId, String tag, String name) {
        return "Robust#BackStackId#add#" + fragmentClass.getName() + "#" + containerId + "#" + tag + "#" + name;
    }
}

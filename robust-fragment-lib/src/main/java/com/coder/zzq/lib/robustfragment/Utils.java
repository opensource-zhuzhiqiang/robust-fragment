package com.coder.zzq.lib.robustfragment;

import androidx.fragment.app.Fragment;

public class Utils {
    public static String parseRobustFragmentTag(Class<? extends Fragment> fragmentClass, String tag) {
        if (tag == null) {
            tag = "";
        }
        return "Robust#FragTag#" + fragmentClass.getName() + "#" + tag.trim();
    }

    public static String parseRobustFragmentBackEntryName(Class<? extends Fragment> fragmentClass, String name) {
        if (name == null) {
            name = "";
        }
        return "Robust#BackEntryName#" + fragmentClass.getName() + "#" + name.trim();
    }
}

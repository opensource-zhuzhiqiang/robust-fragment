package com.coder.zzq.lib.robustfragment;

import com.coder.zzq.lib.robustfragment.injector.FragmentBatchInjector;
import com.coder.zzq.lib.robustfragment.injector.FragmentInjector;

public final class RobustFragmentMaster {
    public static FragmentInjector injector() {
        return new FragmentInjector();
    }

    public static FragmentBatchInjector batchInjector() {
        return new FragmentBatchInjector();
    }
}

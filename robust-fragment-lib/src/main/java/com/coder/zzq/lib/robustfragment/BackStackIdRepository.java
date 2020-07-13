package com.coder.zzq.lib.robustfragment;

import android.util.SparseIntArray;

public class BackStackIdRepository {
    private static SparseIntArray sBackStackIds;

    private static SparseIntArray getBackStackIds() {
        if (sBackStackIds == null) {
            sBackStackIds = new SparseIntArray();
        }
        return sBackStackIds;
    }

    public static void storeId(int key, int id) {
        getBackStackIds().put(key, id);
    }

    public static void retrieve(int key) {
        getBackStackIds().get(key, -1);
    }
}

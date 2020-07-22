package com.coder.zzq.lib.robustfragment;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.coder.zzq.lib.robustfragment.options.FragmentOptions;
import com.coder.zzq.lib.robustfragment.options.IFragmentOptions;


public class FragmentOperations {
    private FragmentManager mFragmentManager;
    private FragmentTransaction mCurrentTransaction;
    protected boolean mAddToBackStack;
    protected String mBackStackEntryName;
    protected String mTransformedBackStackEntryName;

    public FragmentOperations(FragmentManager fragmentManager) {
//        mFragmentManager = Utils.requireNonNull(fragmentManager, "the fragment manager can not be null!");
    }

    private FragmentTransaction getCurrentTransaction() {
        if (mCurrentTransaction == null) {
            mCurrentTransaction = mFragmentManager.beginTransaction();
        }
        return mCurrentTransaction;
    }

    public FragmentOperations add(IFragmentOptions fragmentOption) {
        FragmentOptions options = (FragmentOptions) fragmentOption;
        Fragment fragment = null;
        if (!options.isAlreadyAddedToFragmentManager()) {
            getCurrentTransaction().add(options.getContainerId(), fragment, options.getTag());
        }
        return this;
    }

    public FragmentOperations remove(Fragment fragment) {
        if (fragment != null) {
            getCurrentTransaction().remove(fragment);
        }
        return this;
    }


    public FragmentOperations remove(@IdRes int id) {
        return remove(mFragmentManager.findFragmentById(id));
    }

    public FragmentOperations remove(String tag) {
        return remove(mFragmentManager.findFragmentByTag(tag));
    }

    public FragmentOperations hide(Fragment fragment) {
        if (fragment != null) {
            getCurrentTransaction().hide(fragment);
        }
        return this;
    }

    public FragmentOperations hide(@IdRes int id) {
        return hide(mFragmentManager.findFragmentById(id));
    }


    public FragmentOperations hide(String tag) {
        return hide(mFragmentManager.findFragmentByTag(tag));
    }


    public FragmentOperations show(Fragment fragment) {
        if (fragment != null) {
            getCurrentTransaction().show(fragment);
        }
        return this;
    }

    public FragmentOperations show(@IdRes int id) {
        return show(mFragmentManager.findFragmentById(id));
    }


    public FragmentOperations show(String tag) {
        return show(mFragmentManager.findFragmentByTag(tag));
    }

    public FragmentOperations detach(Fragment fragment) {
        getCurrentTransaction().detach(fragment);
        return this;
    }

    public FragmentOperations detach(@IdRes int id) {
        return detach(mFragmentManager.findFragmentById(id));
    }


    public FragmentOperations detach(String tag) {
        return detach(mFragmentManager.findFragmentByTag(tag));
    }

    FragmentOperations attach(Fragment fragment) {
        getCurrentTransaction().attach(fragment);
        return this;
    }

    public FragmentOperations replace(FragmentOptions fragmentOptions) {
//        getCurrentTransaction().replace(fragmentOptions.getContainerId(), FragmentRetriever.createOrRetrieveFragment(mFragmentManager, fragmentOptions), fragmentOptions.getTag());
        return this;
    }


    public FragmentOperations addToBackStack(String backStackEntryName) {
        mAddToBackStack = true;
        mBackStackEntryName = backStackEntryName;
        return this;
    }

    public int complete() {
        if (mCurrentTransaction != null) {
            return getCurrentTransaction().commit();
        }
        return -1;
    }
}

package com.coder.zzq.lib.robustfragment.injector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.coder.zzq.lib.robustfragment.RobustFragmentRetriever;
import com.coder.zzq.toolkit.Utils;

import java.util.Arrays;
import java.util.List;

public class FragmentBatchInjector {
    private List<FragmentOptions> mFragmentOptionsList;

    public FragmentBatchInjector fragments(List<FragmentOptions> fragmentOptionsList) {
        mFragmentOptionsList = fragmentOptionsList;
        return this;
    }

    public FragmentBatchInjector fragments(FragmentOptions[] fragmentOptionsArray) {
        mFragmentOptionsList = Arrays.asList(fragmentOptionsArray);
        return this;
    }


    public void addInto(AppCompatActivity activity) {
        addInto(Utils.requireNonNull(activity, "the activity can not be null ").getSupportFragmentManager());
    }

    public void addInto(Fragment fragment) {
        addInto(Utils.requireNonNull(fragment, "the fragment can not be null").getChildFragmentManager());
    }

    private void addInto(FragmentManager fragmentManager) {
        if (mFragmentOptionsList == null || mFragmentOptionsList.isEmpty()) {
            return;
        }
        FragmentTransaction transaction = null;
        for (int index = 0; index < mFragmentOptionsList.size(); index++) {
            FragmentOptions fragmentOptions = mFragmentOptionsList.get(index);
            Fragment addedFragment = RobustFragmentRetriever.createOrRetrieveFragment(fragmentManager, fragmentOptions);
            if (!fragmentOptions.isAlreadyAddedToFragmentManager()) {
                if (transaction == null) {
                    transaction = fragmentManager.beginTransaction();
                }
                transaction.add(fragmentOptions.getContainerId(), addedFragment, fragmentOptions.getTag());
            }
        }

        if (transaction != null) {
            transaction.commit();
        }
    }
}

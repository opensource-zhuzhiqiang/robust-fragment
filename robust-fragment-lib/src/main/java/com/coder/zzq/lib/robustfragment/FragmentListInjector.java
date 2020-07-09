package com.coder.zzq.lib.robustfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class FragmentListInjector {
    private List<FragmentOptions> mFragmentOptionsList;

    public FragmentListInjector() {

    }

    private void ensureFragmentOptionsListCreated(int size) {
        if (mFragmentOptionsList == null) {
            mFragmentOptionsList = new ArrayList<>(size);
        }
    }



    public FragmentListInjector fragments(List<FragmentOptions> optionsList) {
        ensureFragmentOptionsListCreated(optionsList.size());
        mFragmentOptionsList.addAll(optionsList);
        return this;
    }

    public FragmentListInjector fragments(FragmentOptions[] optionsArray) {
        ensureFragmentOptionsListCreated(optionsArray.length);
        for (int index = 0; index < optionsArray.length; index++) {
            mFragmentOptionsList.add(optionsArray[index]);
        }
        return this;
    }

    public void addInto(AppCompatActivity activity) {
        addInto(activity.getSupportFragmentManager());
    }


    public void addInto(Fragment fragment) {
        addInto(fragment.getChildFragmentManager());
    }


    private void addInto(@NonNull FragmentManager fragmentManager) {
        FragmentTransaction fragmentTransaction = null;
        for (int index = 0; index < mFragmentOptionsList.size(); index++) {
            Fragment fragment = mFragmentOptionsList.get(index).isUniqueOwnerOfContainer()
                    ? fragmentManager.findFragmentById(mFragmentOptionsList.get(index).getContainerId())
                    : fragmentManager.findFragmentByTag(mFragmentOptionsList.get(index).getTag());
            if (fragment == null) {
                try {
                    fragment = mFragmentOptionsList.get(index).getFragmentClass().newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                fragment.setArguments(mFragmentOptionsList.get(index).getArguments());

                if (mFragmentOptionsList.get(index).isAddToBackStack()) {
                    fragmentManager.beginTransaction().addToBackStack(mFragmentOptionsList.get(index).getBackEntryName()).commit();
                } else {
                    if (fragmentTransaction == null) {
                        fragmentTransaction = fragmentManager.beginTransaction();
                    }
                    fragmentTransaction.add(mFragmentOptionsList.get(index).getContainerId(), fragment, mFragmentOptionsList.get(index).getTag());
                }
            }
        }

        if (fragmentTransaction != null) {
            fragmentTransaction.commit();
        }
    }
}

package com.coder.zzq.lib.robustfragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.coder.zzq.toolkit.Utils;

public class RobustDialog<NestedDialog extends AppCompatDialog> extends Fragment implements DialogInterface.OnCancelListener, IRobustDialog {
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
    private static final String IS_MENU_VISIBLE_TAG = "isMenuVisible";


    NestedDialog mDialog;
    boolean mDialogDismissed;
    boolean mDialogHidden;
    boolean mMenuVisible = true;
    boolean mOnStarted;
    boolean mAddedToFm;
    int mOnCreateCalled;
    int mOnCreateViewCalled;
    String mFragmentTag;


    public RobustDialog() {

    }


    public void showInActivity(AppCompatActivity activity, String businessTag) {
        show(activity.getSupportFragmentManager(), businessTag);
    }

    public void showInFragment(@NonNull Fragment fragment, @NonNull String businessTag) {
        show(fragment.getChildFragmentManager(), businessTag);
    }

    public static void show(@NonNull FragmentManager manager, @NonNull String businessTag) {
        if (Utils.isEmpty(businessTag)) {
            throw new IllegalArgumentException("the business tag for the dialog can not be empty or null");
        }

        RobustDialog fragment = (RobustDialog) manager.findFragmentByTag(businessTag);
        if (fragment == null) {
            fragment = new RobustDialog();

            manager.beginTransaction()
                    .add(fragment, businessTag)
                    .commitNow();
            fragment.mAddedToFm = true;
            return;
        }
        fragment.mDialogDismissed = false;

        if (fragment.mOnStarted && !fragment.mDialog.isShowing()) {
            fragment.mDialog.show();
        }
    }


    public void dismiss() {
        mDialogDismissed = true;
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mDialog == null) {
            mDialog = onCreateDialog(savedInstanceState);
            mDialog.setOwnerActivity(getActivity());
            mDialog.setOnCancelListener(this);
        }

        if (savedInstanceState != null) {
            Bundle dialogState = savedInstanceState.getBundle(SAVED_DIALOG_STATE_TAG);
            if (dialogState != null) {
                mDialog.onRestoreInstanceState(dialogState);
            }

            mDialogDismissed = !mDialog.isShowing();
            mMenuVisible = savedInstanceState.getBoolean("isMenuVisible", true);
        }

        mDialogHidden = isHidden() || !mMenuVisible || !getUserVisibleHint();
        mOnCreateCalled++;
    }


    @NonNull
    public NestedDialog onCreateDialog(@Nullable Bundle savedInstanceState){
        return (NestedDialog) new AlertDialog.Builder(getActivity())
                .setMessage("1234")
                .create();
    }


    protected void onResetDialog(NestedDialog dialog) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mOnCreateViewCalled++;
        if (mOnCreateViewCalled > mOnCreateCalled) {
            onResetDialog(mDialog);
            mOnCreateViewCalled--;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();
        mOnStarted = true;
        if (mDialogDismissed) {
            mDialog.dismiss();
        } else {
            mDialog.show();
        }

        if (mDialogHidden) {
            mDialog.hide();
        } else {
            mDialog.getWindow().getDecorView().setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mOnStarted = false;
        if (mDialog != null) {
            mDialog.hide();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        controlDialogUiVisibility(!hidden);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        controlDialogUiVisibility(isVisibleToUser);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        mMenuVisible = menuVisible;
        controlDialogUiVisibility(menuVisible);
    }

    private void controlDialogUiVisibility(boolean visible) {
        mDialogHidden = !visible;
        if (mDialog == null) {
            return;
        }
        if (visible) {
            mDialog.getWindow().getDecorView().setVisibility(View.VISIBLE);
        } else {
            mDialog.hide();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mDialog != null) {
            Bundle dialogState = mDialog.onSaveInstanceState();
            if (dialogState != null) {
                outState.putBundle(SAVED_DIALOG_STATE_TAG, dialogState);
            }
        }
        outState.putBoolean(IS_MENU_VISIBLE_TAG, mMenuVisible);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mDialog != null) {
            mDialog.dismiss();
        }
        mDialogDismissed = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDialog = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mAddedToFm = false;
    }


    @Override
    public void onCancel(DialogInterface dialog) {
        mDialogDismissed = true;
    }

    @Override
    public IRobustDialog businessTag(String businessTag) {
        return null;
    }
}

package com.coder.zzq.lib.robustfragment;

import android.content.DialogInterface;

import com.coder.zzq.smartshow.dialog.data_item.BoolDataItem;
import com.coder.zzq.smartshow.dialog.data_item.DataItem;
import com.coder.zzq.smartshow.dialog.data_item.IntDataItem;
import com.coder.zzq.smartshow.dialog.data_item.ObjectDataItem;

public class SmartBuildParams<CHAIN_INVOKE_TYPE extends SmartBuildParams> {
    public static final String PARAM_DARK_BEHIND_WHEN_SHOW = "darkBehindWhenShow";

    public static final String PARAM_WINDOW_BACKGROUND = "windowBackground";

    public static final String PARAM_CANCELABLE = "cancelable";

    public static final String PARAM_CANCELABLE_ON_TOUCH_OUTSIDE = "cancelableOnTouchOutside";

    public static final String PARAM_ON_DIALOG_SHOW_LISTENER = "onDialogShowListener";

    public static final String PARAM_ON_DIALOG_DISMISS_LISTENER = "onDialogDismissListener";

    public static final String PARAM_ON_DIALOG_CANCEL_LISTENER = "onDialogCancelListener";


    protected boolean mResetWhenShowAgain;

    protected OnBuildParamChangedListener mOnBuildParamChangedListener;

    protected BoolDataItem mDarkBehindWhenShow;

    protected IntDataItem mWindowBackground;

    protected BoolDataItem mCancelable;

    protected BoolDataItem mCancelableOnTouchOutside;

    protected ObjectDataItem<DialogInterface.OnShowListener> mOnDialogShowListener;

    protected ObjectDataItem<DialogInterface.OnDismissListener> mOnDialogDismissListener;

    protected ObjectDataItem<DialogInterface.OnCancelListener> mOnDialogCancelListener;

    public SmartBuildParams() {
        super();
        mDarkBehindWhenShow = new BoolDataItem();
        mDarkBehindWhenShow.setName(PARAM_DARK_BEHIND_WHEN_SHOW);
        mDarkBehindWhenShow.setNewData(true);
        mWindowBackground = new IntDataItem();
        mWindowBackground.setName(PARAM_WINDOW_BACKGROUND);
        mWindowBackground.setNewData(com.coder.zzq.smartshow.dialog.R.drawable.smart_show_round_dialog_bg);
        mCancelable = new BoolDataItem();
        mCancelable.setName(PARAM_CANCELABLE);
        mCancelable.setNewData(true);
        mCancelableOnTouchOutside = new BoolDataItem();
        mCancelableOnTouchOutside.setName(PARAM_CANCELABLE_ON_TOUCH_OUTSIDE);
        mCancelableOnTouchOutside.setNewData(false);
    }


    public CHAIN_INVOKE_TYPE resetWhenShowAgain(boolean resetWhenShowAgain) {
        mResetWhenShowAgain = resetWhenShowAgain;
        return (CHAIN_INVOKE_TYPE) this;
    }

    public boolean isResetWhenShowAgain() {
        return mResetWhenShowAgain;
    }


    public CHAIN_INVOKE_TYPE darkBehindWhenShow(boolean darkBehindWhenShow) {
        if (mDarkBehindWhenShow == null) {
            mDarkBehindWhenShow = new BoolDataItem();
            mDarkBehindWhenShow.setName(PARAM_DARK_BEHIND_WHEN_SHOW);
        }
        mDarkBehindWhenShow.setNewData(darkBehindWhenShow);
        return (CHAIN_INVOKE_TYPE) this;
    }

    public boolean isDarkBehindWhenShow() {
        return mDarkBehindWhenShow == null || mDarkBehindWhenShow.getCurrentData();
    }


    public CHAIN_INVOKE_TYPE windowBackground(int windowBackground) {
        if (mWindowBackground == null) {
            mWindowBackground = new IntDataItem();
            mWindowBackground.setName(PARAM_WINDOW_BACKGROUND);
        }
        mWindowBackground.setNewData(windowBackground);
        return (CHAIN_INVOKE_TYPE) this;
    }

    public int getWindowBackground() {
        return mWindowBackground == null ? 0 : mWindowBackground.getCurrentData();
    }

    public CHAIN_INVOKE_TYPE cancelable(boolean cancelable) {
        if (mCancelable == null) {
            mCancelable = new BoolDataItem();
            mCancelable.setName(PARAM_CANCELABLE);
        }
        mCancelable.setNewData(cancelable);
        return (CHAIN_INVOKE_TYPE) this;
    }

    public boolean isCancelable() {
        return mCancelable == null || mCancelable.getCurrentData();
    }


    public CHAIN_INVOKE_TYPE cancelableOnTouchOutside(boolean cancelableOnTouchOutside) {
        if (mCancelableOnTouchOutside == null) {
            mCancelableOnTouchOutside = new BoolDataItem();
            mCancelableOnTouchOutside.setName(PARAM_CANCELABLE_ON_TOUCH_OUTSIDE);
        }
        mCancelableOnTouchOutside.setNewData(cancelableOnTouchOutside);
        return (CHAIN_INVOKE_TYPE) this;
    }

    public boolean isCancelableOnTouchOutside() {
        return mCancelableOnTouchOutside == null || mCancelableOnTouchOutside.getCurrentData();
    }


    public CHAIN_INVOKE_TYPE onDialogShowListener(
            DialogInterface.OnShowListener onDialogShowListener) {
        if (mOnDialogShowListener == null) {
            mOnDialogShowListener = new ObjectDataItem();
            mOnDialogShowListener.setName(PARAM_ON_DIALOG_SHOW_LISTENER);
        }
        mOnDialogShowListener.setNewData(onDialogShowListener);
        return (CHAIN_INVOKE_TYPE) this;
    }

    public DialogInterface.OnShowListener getOnDialogShowListener() {
        return mOnDialogShowListener == null ? null : mOnDialogShowListener.getCurrentData();
    }

    public CHAIN_INVOKE_TYPE onDialogDismissListener(
            DialogInterface.OnDismissListener onDialogDismissListener) {
        if (mOnDialogDismissListener == null) {
            mOnDialogDismissListener = new ObjectDataItem();
            mOnDialogDismissListener.setName(PARAM_ON_DIALOG_DISMISS_LISTENER);
        }
        mOnDialogDismissListener.setNewData(onDialogDismissListener);
        return (CHAIN_INVOKE_TYPE) this;
    }

    public DialogInterface.OnDismissListener getOnDialogDismissListener() {
        return mOnDialogDismissListener == null ? null : mOnDialogDismissListener.getCurrentData();
    }


    public CHAIN_INVOKE_TYPE onDialogCancelListener(
            DialogInterface.OnCancelListener onDialogCancelListener) {
        if (mOnDialogCancelListener == null) {
            mOnDialogCancelListener = new ObjectDataItem();
            mOnDialogCancelListener.setName(PARAM_ON_DIALOG_CANCEL_LISTENER);
        }
        mOnDialogCancelListener.setNewData(onDialogCancelListener);
        return (CHAIN_INVOKE_TYPE) this;
    }

    public DialogInterface.OnCancelListener getOnDialogCancelListener() {
        return mOnDialogCancelListener == null ? null : mOnDialogCancelListener.getCurrentData();
    }

    public void setOnBuildParamChangedListener(OnBuildParamChangedListener onBuildParamChangedListener) {
        mOnBuildParamChangedListener = onBuildParamChangedListener;
    }

    public void update() {
        if (mOnBuildParamChangedListener == null) {
            return;
        }

        if (mDarkBehindWhenShow != null && mDarkBehindWhenShow.isDataChanged()) {
            mDarkBehindWhenShow.updateData();
            mOnBuildParamChangedListener.onBuildParamChanged(mDarkBehindWhenShow.getName());
        }

        if (mWindowBackground != null && mWindowBackground.isDataChanged()) {
            mWindowBackground.updateData();
            mOnBuildParamChangedListener.onBuildParamChanged(mWindowBackground.getName());
        }

        if (mCancelable != null && mCancelable.isDataChanged()) {
            mCancelable.updateData();
            mOnBuildParamChangedListener.onBuildParamChanged(mCancelable.getName());
        }

        if (mCancelableOnTouchOutside != null && mCancelableOnTouchOutside.isDataChanged()) {
            mCancelableOnTouchOutside.updateData();
            mOnBuildParamChangedListener.onBuildParamChanged(mCancelableOnTouchOutside.getName());
        }

        if (mOnDialogShowListener != null && mOnDialogShowListener.isDataChanged()) {
            mOnDialogShowListener.updateData();
            mOnBuildParamChangedListener.onBuildParamChanged(mOnDialogShowListener.getName());
        }

        if (mOnDialogDismissListener != null && mOnDialogDismissListener.isDataChanged()) {
            mOnDialogDismissListener.updateData();
            mOnBuildParamChangedListener.onBuildParamChanged(mOnDialogDismissListener.getName());
        }

        if (mOnDialogCancelListener != null && mOnDialogCancelListener.isDataChanged()) {
            mOnDialogCancelListener.updateData();
            mOnBuildParamChangedListener.onBuildParamChanged(mOnDialogCancelListener.getName());
        }

    }


    public DataItem getData(String dataName) {
        switch (dataName) {
            case PARAM_DARK_BEHIND_WHEN_SHOW:
                return mDarkBehindWhenShow;
            case PARAM_WINDOW_BACKGROUND:
                return mWindowBackground;
            case PARAM_CANCELABLE:
                return mCancelable;
            case PARAM_CANCELABLE_ON_TOUCH_OUTSIDE:
                return mCancelableOnTouchOutside;
            case PARAM_ON_DIALOG_SHOW_LISTENER:
                return mOnDialogShowListener;
            case PARAM_ON_DIALOG_DISMISS_LISTENER:
                return mOnDialogDismissListener;
            case PARAM_ON_DIALOG_CANCEL_LISTENER:
                return mOnDialogCancelListener;
            default:
                return null;
        }
    }

    public void reset() {
        mLifecycleBinder = null;
        if (mDarkBehindWhenShow != null) {
            mDarkBehindWhenShow.reset();
        }

        if (mWindowBackground != null) {
            mWindowBackground.reset();
        }

        if (mCancelable != null) {
            mCancelable.reset();
        }

        if (mCancelableOnTouchOutside != null) {
            mCancelableOnTouchOutside.reset();
        }

        if (mOnDialogShowListener != null) {
            mOnDialogShowListener.reset();
        }

        if (mOnDialogShowListener != null) {
            mOnDialogDismissListener.reset();
        }

        if (mOnDialogCancelListener != null) {
            mOnDialogCancelListener.reset();
        }
    }

    public void onDestroyed() {
        mOnBuildParamChangedListener = null;
    }

    public interface OnBuildParamChangedListener {
        void onBuildParamChanged(String paramName);
    }
}

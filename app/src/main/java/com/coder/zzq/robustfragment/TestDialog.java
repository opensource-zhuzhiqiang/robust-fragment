package com.coder.zzq.robustfragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.coder.zzq.lib.annotations.RobustDialog;

@RobustDialog
public class TestDialog extends AlertDialog {
    protected TestDialog(@NonNull Context context) {
        super(context);
    }

    protected TestDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected TestDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


}

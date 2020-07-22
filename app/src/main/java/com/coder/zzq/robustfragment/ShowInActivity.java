package com.coder.zzq.robustfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.coder.zzq.lib.robustfragment.dialog.DialogHostFragment;

public class ShowInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_in);
    }


    public void onShowDialogClick(View view) {
        DialogHostFragment.show(getSupportFragmentManager(), "123");
        DialogHostFragment.show(getSupportFragmentManager(), "123");
    }

    public void onRecreateClick(View view) {
        recreate();
    }


    public void onJumpToAnotherActivityClick(View view) {
        startActivity(new Intent(this, CertainActivity.class));
    }
}

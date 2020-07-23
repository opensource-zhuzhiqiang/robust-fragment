package com.coder.zzq.robustfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.coder.zzq.lib.robustfragment.options.FragmentOptions;

public class ShowInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_in);
    }


    public void onShowDialogClick(View view) {
        ExampleFragmentMaster.injector()
                .options(
                        FragmentOptions.create()
                                .containerId(R.id.fragment_container)
                                .tag("testTag")
                                .stringArgument(ExampleFragmentMaster.ARGUMENT_ORDER_NO, "12345678")
                )
                .addInto(this);
    }

    public void onRecreateClick(View view) {
        recreate();
    }


    public void onJumpToAnotherActivityClick(View view) {
        startActivity(new Intent(this, CertainActivity.class));
    }
}

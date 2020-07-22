package com.coder.zzq.robustfragment;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

public class TestActivity extends AppCompatActivity {
    int count = 0;
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mFragment = new ExampleFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mFragment)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mFragment)
                .commit();
    }

    public void onClick(View view) {

        getSupportFragmentManager().getFragments();
    }
}
package com.coder.zzq.robustfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    public static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new ExampleFragment(), "12345")
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new ExampleFragment(), "67890")
                .commit();
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    public void onClick(View view) {


//        getSupportFragmentManager().beginTransaction().detach(getSupportFragmentManager().findFragmentById(R.id.fragment_container)).commitNow();
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        getSupportFragmentManager().beginTransaction().remove(fragment).commitNow();
        Log.d("test", fragment.isAdded() + "" + fragment.isVisible() + fragment.isDetached());
    }
}

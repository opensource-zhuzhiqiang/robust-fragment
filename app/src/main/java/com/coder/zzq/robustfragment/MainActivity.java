package com.coder.zzq.robustfragment;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.coder.zzq.lib.robustfragment.FragmentOptions;
import com.coder.zzq.lib.robustfragment.RobustFragmentMaster;

public class MainActivity extends AppCompatActivity {

    public static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int id01 = RobustFragmentMaster.singleInjector()
                .fragment(
                        FragmentOptions.create()
                                .intArgument("testArg", 1)
                                .fragmentClass(ExampleFragment.class)
                                .addToBackStack(true, "fragment0001")
                                .tag("001")
                                .container(R.id.fragment_container, false)
                )
                .addInto(this);

       int id02=  RobustFragmentMaster.singleInjector()
                .fragment(
                        FragmentOptions.create()
                                .intArgument("testArg", 1)
                                .fragmentClass(ExampleFragment.class)
                                .addToBackStack(true, "fragment0002")
                                .tag("002")
                                .container(R.id.fragment_container, false)
                )
                .addInto(this);

       getSupportFragmentManager().popBackStack(id01,0);

    }

}

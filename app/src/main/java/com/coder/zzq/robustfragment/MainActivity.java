package com.coder.zzq.robustfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.coder.zzq.smartshow.core.SmartShow;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SmartShow.init(getApplication());
        mListView = findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, ShowInActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, TestActivity.class));
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}

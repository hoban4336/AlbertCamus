package com.pikitori.example.albertcamus.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TabHost;

import com.pikitori.example.albertcamus.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private MainTabsAdapter mainTabsAdapter;
    private int indexDefaultTab ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate()");

        indexDefaultTab = MainTabsConfig.TABINDEX.FIRST;

        mainTabsAdapter
                = new MainTabsAdapter(
                MainActivity.this,
                (TabHost)findViewById(android.R.id.tabhost),
                (ViewPager)findViewById(R.id.pager));
    //    if(indexDefaultTab != MainTabsConfig.TABINDEX.FIRST){
            mainTabsAdapter.selectTab(indexDefaultTab);
    //    }


    }
}

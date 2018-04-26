package com.ralo.unbeatable.Activities;

import android.view.View;

import com.ralo.unbeatable.R;

public class MainActivity extends BaseActivity {
    private View mainActivity;

    @Override
    public void onCreateActivity() {
        mainActivity = getLayoutInflater().inflate(R.layout.activity_main, null, false);
        setMainContainer(mainActivity);
    }
}

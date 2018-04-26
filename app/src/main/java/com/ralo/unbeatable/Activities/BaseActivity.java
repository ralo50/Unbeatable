package com.ralo.unbeatable.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.ralo.unbeatable.R;
import com.ralo.unbeatable.UnbeatableApp;

public abstract class BaseActivity extends AppCompatActivity {
    private FrameLayout mainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mainContainer = findViewById(R.id.main_container);
        setup();
    }

    private void setup() {
        UnbeatableApp.setCurrentActivity(this);
        onCreateActivity();
    }

    public void setMainContainer(View child){
        this.mainContainer.addView(child);
    }

    public abstract void onCreateActivity();

}


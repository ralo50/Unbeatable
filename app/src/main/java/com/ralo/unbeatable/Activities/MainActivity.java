package com.ralo.unbeatable.Activities;

import android.view.View;
import android.widget.ImageView;

import com.ralo.unbeatable.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private View mainActivity;
    @BindView(R.id.box0)
    ImageView box0;
    @BindView(R.id.box1)
    ImageView box1;
    @BindView(R.id.box2)
    ImageView box2;
    @BindView(R.id.box3)
    ImageView box3;
    @BindView(R.id.box4)
    ImageView box4;
    @BindView(R.id.box5)
    ImageView box5;
    @BindView(R.id.box6)
    ImageView box6;
    @BindView(R.id.box7)
    ImageView box7;
    @BindView(R.id.box8)
    ImageView box8;

    @Override
    public void onCreateActivity() {
        mainActivity = getLayoutInflater().inflate(R.layout.activity_main, null, false);
        setMainContainer(mainActivity);
        ButterKnife.bind(this);
        box0.setOnClickListener(this);
        box1.setOnClickListener(this);
        box2.setOnClickListener(this);
        box3.setOnClickListener(this);
        box4.setOnClickListener(this);
        box5.setOnClickListener(this);
        box6.setOnClickListener(this);
        box7.setOnClickListener(this);
        box8.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.box0:

            case R.id.box1:

                break;
            case R.id.box2:

                break;
            case R.id.box3:

                break;
            case R.id.box4:

                break;
            case R.id.box5:

                break;
            case R.id.box6:

                break;
            case R.id.box7:

                break;
            case R.id.box8:

                break;
        }
    }
}

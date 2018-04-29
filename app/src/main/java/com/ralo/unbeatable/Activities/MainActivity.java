package com.ralo.unbeatable.Activities;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ralo.unbeatable.R;
import com.ralo.unbeatable.Utils.Game.Board;
import com.ralo.unbeatable.Utils.Game.GameFlow;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    public TextView gameResult;
    public Button restartButton;
    public View mainActivity;
    public static List<ImageView> buttons = new ArrayList<>();
    public static final int[] BUTTON_IDS = {
            R.id.box0,
            R.id.box1,
            R.id.box2,
            R.id.box3,
            R.id.box4,
            R.id.box5,
            R.id.box6,
            R.id.box7,
            R.id.box8,
    };

    public static Board board = new Board();

    @SuppressLint("InflateParams")
    @Override
    public void onCreateActivity() {
        mainActivity = getLayoutInflater().inflate(R.layout.activity_main, null, false);
        setMainContainer(mainActivity);
        restartButton = findViewById(R.id.restart_button);
        gameResult = findViewById(R.id.game_result);
        restartButton.setVisibility(View.GONE);
        setButtonListeners();

    }

    @Override
    public void onClick(View view) {
        int tag = (int)view.getTag();
        GameFlow.playMove(tag);
        if(board.isGameOver()){
            restartButton.setVisibility(View.VISIBLE);
            gameResult.setText(board.getWinnerString());
        }
    }

    public void restartGame(View view) {
        board.reset();
        GameFlow.updateBoard();
        restartButton.setVisibility(View.GONE);
        gameResult.setText("");
    }

    private void setButtonListeners(){
        int tagNumberCounter = 0;
        for (int id : BUTTON_IDS) {
            ImageView button = findViewById(id);
            button.setOnClickListener(this);
            buttons.add(button);
            button.setTag(tagNumberCounter);
            tagNumberCounter++;
        }
    }
}

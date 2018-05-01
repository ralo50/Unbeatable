package com.ralo.unbeatable.Activities;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import com.ralo.unbeatable.R;
import com.ralo.unbeatable.Utils.Game.Board;
import com.ralo.unbeatable.Utils.Game.GameFlow;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public TextView gameResult;
    public Button restartButton;
    public View mainActivity;
    private static Switch difficultySwitch;
    private static Switch playerSwitch;
    public static boolean isUnbeatable;
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

        setViews();
        setButtonListeners();
    }

    @Override
    public void onClick(View view) {
        setSwitchState(false);
        int tag = (int)view.getTag();
        GameFlow.playMove(tag);
        if(board.isGameOver()){
            restartButton.setVisibility(View.VISIBLE);
            gameResult.setText(board.getWinnerString());
            setSwitchState(true);
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

    private void setViews(){
        restartButton = findViewById(R.id.restart_button);
        gameResult = findViewById(R.id.game_result);
        restartButton.setVisibility(View.GONE);
        difficultySwitch = findViewById(R.id.diff);
        difficultySwitch.setOnCheckedChangeListener(this);
        playerSwitch = findViewById(R.id.player);
        playerSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setViews();
        setButtonListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setViews();
        setButtonListeners();
        GameFlow.updateBoard();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean turnedOn) {
        switch (compoundButton.getId()) {
            case R.id.diff:
                if (turnedOn) {
                    isUnbeatable = true;
                    difficultySwitch.setText(this.getResources().getText(R.string.unbeatable));
                    break;
                } else {
                    isUnbeatable = false;
                    difficultySwitch.setText(this.getResources().getText(R.string.random));
                }
                break;
            case R.id.player:
                if(turnedOn){
                    playerSwitch.setText(this.getResources().getText(R.string.twoPlayers));
                    difficultySwitch.setEnabled(false);
                    break;
                }
                else {
                    playerSwitch.setText(this.getResources().getText(R.string.vsComputer));
                    difficultySwitch.setEnabled(true);
                }
                break;
        }
    }

    public static boolean isUnbeatable(){
        return isUnbeatable;
    }
    public static void setSwitchState(boolean state){
        difficultySwitch.setClickable(state);
        playerSwitch.setClickable(state);
    }

    public static boolean getSwitchState(){
        return difficultySwitch.isClickable();
    }

    public static boolean isVersusComputer(){
        return difficultySwitch.isEnabled();
    }
}
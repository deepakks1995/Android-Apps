package com.example.deepak.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;
import android.os.Handler;
import android.widget.Toast;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    private int THRESHOLD_VALUE = 100;
    private int user_overall_score = 0;
    private int user_turn_sore = 0;
    private int comp_overall_score = 0;
    private int comp_turn_score = 0;
    private Random random = new Random();
    private Handler timer = new Handler();
    Animation animation ;
    Runnable timerhandler = new Runnable() {
        @Override
        public void run() {
            if(computerTurn()) {
                comp_overall_score += comp_turn_score;
                comp_turn_score = 0;
                compScore.setText("Computer Score: " + comp_overall_score);
                turn.setText("Your Turn");
                roll.setEnabled(true);
                hold.setEnabled(true);
                image.setClickable(true);
                checkStatus();
                timer.removeCallbacks(timerhandler);
            }
            else
                timer.postDelayed(timerhandler,2000);
        }
    };
    Button roll,reset,hold;
    TextView playerScore,compScore,turn;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll = (Button) findViewById(R.id.roll);
        reset = (Button) findViewById(R.id.reset);
        hold = (Button) findViewById(R.id.hold);
        playerScore = (TextView) findViewById(R.id.playerText);
        compScore = (TextView) findViewById(R.id.compText);
        turn = (TextView) findViewById(R.id.turn);
        image = (ImageView) findViewById(R.id.imageView);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
        roll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int dice = rollDice();
                image.startAnimation(animation);
                image.setImageResource(getResources().getIdentifier("dice"+dice,"drawable",getPackageName()));
                if(dice!=1) {
                    user_turn_sore += dice;
                    playerScore.setText("Your Score: " + user_overall_score);
                }
                else {
                    Toast.makeText(MainActivity.this, "You Rolled a One", Toast.LENGTH_SHORT).show();
                    user_turn_sore = 0;
                    playerScore.setText("Your Score: " + user_overall_score);
                    roll.setEnabled(false);
                    hold.setEnabled(false);
                    image.setClickable(false);
                    if(checkStatus()==0)
                        timer.postDelayed(timerhandler,2000);
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                roll.setEnabled(true);
                hold.setEnabled(true);
                playerScore.setHighlightColor(0);
                compScore.setHighlightColor(0);
                user_turn_sore = 0;
                user_overall_score = 0;
                comp_overall_score = 0;
                comp_turn_score = 0;
                playerScore.setText("Your Score: " + user_overall_score);
                compScore.setText("Computer Score: " + comp_overall_score);
                turn.setText("Your Turn");
                image.startAnimation(animation);
                image.setImageResource(getResources().getIdentifier("dice1","drawable",getPackageName()));
            }
        });
        hold.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                user_overall_score += user_turn_sore;
                user_turn_sore = 0;
                playerScore.setText("Your Score: " + user_overall_score);
                if(checkStatus()==0)
                    timer.postDelayed(timerhandler,2000);
            }
        });
    }

    protected  int checkStatus(){
        if((user_overall_score > comp_overall_score) && user_overall_score >= THRESHOLD_VALUE ) {
            turn.setText("You Win!");
            roll.setEnabled(false);
            hold.setEnabled(false);
            playerScore.setHighlightColor(50);
            return 1;
        }
        else if ((comp_overall_score > user_overall_score) && comp_overall_score >=THRESHOLD_VALUE) {
            turn.setText("Computer Wins!");
            roll.setEnabled(false);
            hold.setEnabled(false);
            compScore.setHighlightColor(50);
            return 2;
        }
        return 0;
    }

    public void rollImage(View v) {
        int dice = rollDice();
        image.startAnimation(animation);
        image.setImageResource(getResources().getIdentifier("dice"+dice,"drawable",getPackageName()));
        if(dice!=1) {
            user_turn_sore += dice;
            playerScore.setText("Your Score: " + user_overall_score);
        }
        else {
            Toast.makeText(MainActivity.this, "You Rolled a One", Toast.LENGTH_SHORT).show();
            user_turn_sore = 0;
            roll.setEnabled(false);
            hold.setEnabled(false);
            image.setClickable(false);
            playerScore.setText("Your Score: " + user_overall_score);
            if(checkStatus()==0)
                timer.postDelayed(timerhandler,2000);
        }
    }

    protected boolean computerTurn() {
        roll.setEnabled(false);
        hold.setEnabled(false);
        image.setClickable(false);
        int dice;
        turn.setText("Please Wait! Computer is Playing ");
        dice = rollDice();
        image.startAnimation(animation);
        image.setImageResource(getResources().getIdentifier("dice"+dice,"drawable",getPackageName()));
        if(dice!=1) {
            comp_turn_score += dice;
        }
        else {
            Toast.makeText(MainActivity.this, "Computer Rolled a One", Toast.LENGTH_SHORT).show();
            comp_turn_score = 0;
            return true;
        }
        if(comp_turn_score >= 20)
            return true;
        else
            return false;
    }

    protected int rollDice() {
        return random.nextInt(6) + 1;
    }
}
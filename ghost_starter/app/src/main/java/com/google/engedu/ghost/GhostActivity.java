package com.google.engedu.ghost;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class GhostActivity extends AppCompatActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private static final String COMPUTER_WINS = "You Lose!";
    private static final String USER_WINS = "You Win !!";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    private String wordFragment;
    private TextView label,text;
    private Button challenge,reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);
        AssetManager assetManager = getAssets();
        text = (TextView) findViewById(R.id.ghostText);
        label = (TextView) findViewById(R.id.gameStatus);
        challenge = (Button) findViewById(R.id.challenge);
        reset = (Button) findViewById(R.id.reset);
        try {
            InputStream inputStream = assetManager.open("words.txt");
            dictionary = new SimpleDictionary(inputStream);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }
        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onStart(v);
            }
        });
        challenge.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                challengeClick(v);
            }
        });
        onStart(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }

    @Override
    public boolean onKeyUp(int KeyCode, KeyEvent event) {
        if(!userTurn)
            return super.onKeyUp(KeyCode,event);

        char ch = (char)event.getUnicodeChar();
        if( !( ch >= 'a' && ch <='z'  ) ) {
            return super.onKeyUp(KeyCode, event);
        }
        wordFragment = wordFragment + ch;
        label.setText(COMPUTER_TURN);
        text.setText(wordFragment);

        userTurn = false;
        computerTurn();
        return super.onKeyUp(KeyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onStart(View view) {
        wordFragment = new String();
        userTurn = random.nextBoolean();
        text.setText("");
        if (userTurn) {
            label.setText(USER_TURN);
            userTurn = true;
            text.setText("Enter an alphabet to start");
        } else {
            label.setText(COMPUTER_TURN);
            userTurn = false;
            computerTurn();
        }
        challenge.setEnabled(true);
        return true;
    }

    public boolean challengeClick(View view) {
        if(dictionary.isWord(wordFragment)) {
            label.setText(USER_WINS);
            challenge.setEnabled(false);
            return true;
        }
        String word = dictionary.getAnyWordStartingWith(wordFragment);
        if(word!=null) {
            label.setText(COMPUTER_WINS);
            text.setText("Possible word: " + word);
            challenge.setEnabled(false);
        }
        else{
            label.setText(USER_WINS);
            challenge.setEnabled(false);
        }
        userTurn = false;
        return true;
    }

    private boolean computerTurn() {
        if(dictionary.isWord(wordFragment)){
            label.setText(COMPUTER_WINS);
            userTurn = false;
            challenge.setEnabled(false);
            return true;
        }
        else {
            String word = dictionary.getGoodWordStartingWith(wordFragment);
            if(word!=null) {
                Log.d("word", word);
                wordFragment += word.charAt(wordFragment.length());
            }
            else{
                label.setText(COMPUTER_WINS);
                challenge.setEnabled(false);
                return true;
            }
        }
        userTurn = true;
        label.setText(USER_TURN);
        text.setText(wordFragment);
        return true;
    }
}

package niggas.your.planit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    private Button buttonGo;
    private Random random;
    String str;
    int rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rand = 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        random = new Random();
        text = (EditText) findViewById(R.id.randomNumber);
        buttonGo = (Button) findViewById(R.id.buttonGo);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int arr[] = new int[12];
                for(int i=0; i<12; ++i)
                    arr[i] = 0;
                str = text.getText().toString();
                if(str.length()==0)
                    rand = 1;
                else
                rand = Integer.parseInt(str);
                    Intent myintent = new Intent(MainActivity.this, Main2Activity.class);
                    myintent.putExtra("randomNumber", rand);
                myintent.putExtra("variables", arr);
                    startActivity(myintent);
            }
        });
    }
}

package niggas.your.planit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    int entertainment = 0, food = 0, shopping = 0, mall = 0 , bowling = 0, airHockey = 0, swimming = 0;
    int footabll = 0, KFC = 0, dominos = 0, maharaj = 0, raman = 0,restaurant = 0, games = 0, outdoor = 0,movie = 0;
    int arr[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView text = (TextView) findViewById(R.id.textOutput);
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        arr = getIntent().getIntArrayExtra("variables");
        for(int i=0; i<12 ; ++i)
            arr[i] +=1;
        initialiseVariables();
        if(entertainment > food && entertainment > shopping) {
            if(movie > games )
                text.setText("Entertainment-> Movie");
            else {
                if(footabll > swimming)
                    text.setText("Entertainment-> Games-> Football");
                else
                    text.setText("Entertainment-> Games-> Swimming");
            }
        }
        else if(food > entertainment && food > shopping) {
            if(mall > restaurant)
                text.setText("Food-> Mall");
            else text.setText("Food-> Restaurant");
        }
        else {
            text.setText("Shopping from Mall");
        }
    }


    public void initialiseVariables() {
        entertainment = arr[0];
        food = arr[1];
        shopping = arr[2];
        mall = arr[3];
        games = arr[4];
        swimming = arr[5];
        footabll = arr[6];
        KFC = arr[7];
        dominos = arr[8];
        raman = arr[9];
        maharaj = arr[10];
        movie = arr[11];
    }

}

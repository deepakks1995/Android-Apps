package niggas.your.planit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private Button mallButton,gamesButton,outdoorButton,foodButton,restaurantButton,shoppingButton,ramanButton, maharajButton, KFCButton;
    private Button dominosButton, entertainmentButton, bowlingButton, airhockeyButton, swimButton,footballButton,movieButton;
     int entertainment = 0, food = 0, shopping = 0, mall = 0 , bowling = 0, airHockey = 0, swimming = 0,movie = 0;
    int footabll = 0, KFC = 0, dominos = 0, maharaj = 0, raman = 0,restaurant = 0, games = 0, outdoor = 0;
    int numberOfFriends;
   // LinearLayout l2;
    LayoutParams lp;
    TextView text;
    LinearLayout l2;
    int arr[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        l2 = (LinearLayout) findViewById(R.id.rl);
        //l2 = (LinearLayout)findViewById(R.id.l2);
        /*int id = 100+1;
        l2.setId(id);*/
        lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        /*View l2 = inflater.inflate(R.layout.activity_main2,
                (ViewGroup) findViewById(R.id.rl));
        r2.addView(l2);*/
        text = (EditText)findViewById(R.id.textView);
        entertainmentButton = (Button)findViewById(R.id.enterButton);
        foodButton = (Button)findViewById(R.id.foodButton);
        shoppingButton = (Button)findViewById(R.id.shoppingbutton);
        mallButton = new Button(Main2Activity.this);
        gamesButton = new Button(Main2Activity.this);
        swimButton = new Button(Main2Activity.this);
        footballButton = new Button(Main2Activity.this);
        dominosButton = new Button(Main2Activity.this);
        KFCButton = new Button(Main2Activity.this);
        maharajButton = new Button(Main2Activity.this);
        ramanButton = new Button(Main2Activity.this);
        mallButton = new Button(Main2Activity.this);
        movieButton = new Button(Main2Activity.this);
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      /*  fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        numberOfFriends = getIntent().getIntExtra("randomNumber",0);
        arr = getIntent().getIntArrayExtra("variables");
        initialiseVariables();
        Toast.makeText(Main2Activity.this, "Entries Left: "+numberOfFriends, Toast.LENGTH_SHORT).show();
        final int totalButtons = numberOfFriends;
        entertainmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //r2.removeAllViews();
                l2.removeAllViews();
                //--numberOfFriends;
                TextView text1 = new TextView(Main2Activity.this);
                text1.setText("What next?");
                text1.setTextSize(25);
                text1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                ++entertainment;
                //mallButton = new Button(Main2Activity.this);
               // gamesButton = new Button(Main2Activity.this);
                movieButton.setText("Movie");
                gamesButton.setText("Games");
                l2.addView(text1,lp);
                l2.addView(movieButton,lp);
                l2.addView(gamesButton,lp);
               // Toast.makeText(Main2Activity.this, "entertainment", Toast.LENGTH_SHORT).show();
                movieButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ++movie;

                        --numberOfFriends;
                       // Toast.makeText(Main2Activity.this, "friends " + numberOfFriends, Toast.LENGTH_SHORT).show();
                        if(numberOfFriends>0) {
                            Intent myintent = new Intent(Main2Activity.this, Main2Activity.class);
                            myintent.putExtra("randomNumber", numberOfFriends);
                            insertIntoArray();
                            myintent.putExtra("variables" , arr);
                            startActivity(myintent);
                        }
                        else {
                            Intent myintent = new Intent(Main2Activity.this, Main3Activity.class);
                            myintent.putExtra("totalNumber", totalButtons);
                            initialiseVariables();
                            myintent.putExtra("variables", arr);
                            //for(int i=0; i<11; ++i)
                               // Toast.makeText(Main2Activity.this, " " + arr[0], Toast.LENGTH_SHORT).show();
                            startActivity(myintent);
                        }
                    }
                });
                gamesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ++games;
                        l2.removeAllViews();
                         TextView text1 = new TextView(Main2Activity.this);
                         text1.setText("What next?");
                        text1.setTextSize(25);
                        text1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        swimButton = new Button(Main2Activity.this);
                        footballButton = new Button(Main2Activity.this);
                        swimButton.setText("Swimming");
                        footballButton.setText("Football");
                         l2.addView(text1,lp);
                        l2.addView(swimButton,lp);
                        l2.addView(footballButton,lp);
                        swimButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ++swimming;
                                --numberOfFriends;
                               // Toast.makeText(Main2Activity.this, "friends " + numberOfFriends, Toast.LENGTH_SHORT).show();
                                if(numberOfFriends>0) {
                                    Intent myintent = new Intent(Main2Activity.this, Main2Activity.class);
                                    myintent.putExtra("randomNumber", numberOfFriends);
                                    insertIntoArray();
                                    myintent.putExtra("variables" , arr);
                                    startActivity(myintent);
                                }
                                else {
                                    Intent myintent = new Intent(Main2Activity.this, Main3Activity.class);
                                    initialiseVariables();
                                    myintent.putExtra("variables", arr);
                                    startActivity(myintent);
                                }
                            }
                        });
                        footballButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ++footabll;
                                --numberOfFriends;
                               // Toast.makeText(Main2Activity.this, "friends " + numberOfFriends, Toast.LENGTH_SHORT).show();
                                if(numberOfFriends>0) {
                                    Intent myintent = new Intent(Main2Activity.this, Main2Activity.class);
                                    myintent.putExtra("randomNumber", numberOfFriends);
                                    insertIntoArray();
                                    myintent.putExtra("variables" , arr);
                                    startActivity(myintent);
                                }
                                else {
                                    Intent myintent = new Intent(Main2Activity.this, Main3Activity.class);
                                    initialiseVariables();
                                    myintent.putExtra("variables", arr);
                                    startActivity(myintent);
                                   // Toast.makeText(Main2Activity.this, " " + arr[4], Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //r2.removeAllViews();
                l2.removeAllViews();
               // --numberOfFriends;
                TextView text1 = new TextView(Main2Activity.this);
                text1.setText("What next?");
                text1.setTextSize(25);
                text1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ++food;
                dominosButton = new Button(Main2Activity.this);
                KFCButton = new Button(Main2Activity.this);
                maharajButton = new Button(Main2Activity.this);
                ramanButton = new Button(Main2Activity.this);
                dominosButton.setText("Dominos");
                KFCButton.setText("KFC");
                maharajButton.setText("Maharaj Dhaba");
                ramanButton.setText("Raman Dhaba");
                l2.addView(text1,lp);
                l2.addView(dominosButton,lp);
                l2.addView(KFCButton,lp);
                l2.addView(ramanButton,lp);
                l2.addView(maharajButton,lp);

                maharajButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ++maharaj;
                        --numberOfFriends;
                       // Toast.makeText(Main2Activity.this, "friends " + numberOfFriends, Toast.LENGTH_SHORT).show();
                        if(numberOfFriends>0) {
                            Intent myintent = new Intent(Main2Activity.this, Main2Activity.class);
                            myintent.putExtra("randomNumber", numberOfFriends);
                            insertIntoArray();
                            myintent.putExtra("variables" , arr);
                            startActivity(myintent);
                        }
                        else {
                            Intent myintent = new Intent(Main2Activity.this, Main3Activity.class);
                            initialiseVariables();
                            myintent.putExtra("variables", arr);
                           // Toast.makeText(Main2Activity.this, " " + arr[1], Toast.LENGTH_SHORT).show();
                            startActivity(myintent);
                        }
                    }
                });
                ramanButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ++raman;
                        --numberOfFriends;
                       // Toast.makeText(Main2Activity.this, "friends " + numberOfFriends, Toast.LENGTH_SHORT).show();
                        if(numberOfFriends>0) {
                            Intent myintent = new Intent(Main2Activity.this, Main2Activity.class);
                            myintent.putExtra("randomNumber", numberOfFriends);
                            insertIntoArray();
                            myintent.putExtra("variables" , arr);
                            startActivity(myintent);
                        }
                        else {
                            Intent myintent = new Intent(Main2Activity.this, Main3Activity.class);
                            initialiseVariables();
                            myintent.putExtra("variables", arr);
                            startActivity(myintent);
                        }
                    }
                });

                dominosButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ++dominos;
                        --numberOfFriends;
                       // Toast.makeText(Main2Activity.this, "friends " + numberOfFriends, Toast.LENGTH_SHORT).show();
                        if(numberOfFriends>0) {
                            Intent myintent = new Intent(Main2Activity.this, Main2Activity.class);
                            myintent.putExtra("randomNumber", numberOfFriends);
                            insertIntoArray();
                            myintent.putExtra("variables" , arr);
                            startActivity(myintent);
                        }
                        else {
                            Intent myintent = new Intent(Main2Activity.this, Main3Activity.class);
                            initialiseVariables();
                            myintent.putExtra("variables", arr);
                            startActivity(myintent);
                        }
                    }
                });
                KFCButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ++KFC;
                        --numberOfFriends;

                        if(numberOfFriends>0) {
                            Intent myintent = new Intent(Main2Activity.this, Main2Activity.class);
                            myintent.putExtra("randomNumber", numberOfFriends);
                            insertIntoArray();
                            myintent.putExtra("variables" , arr);
                            startActivity(myintent);
                        }
                        else {
                            Intent myintent = new Intent(Main2Activity.this, Main3Activity.class);
                            initialiseVariables();
                            myintent.putExtra("variables", arr);
                            startActivity(myintent);
                        }
                    }
                });
            }
        });



        shoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //r2.removeAllViews();
                l2.removeAllViews();
               // --numberOfFriends;
                TextView text1 = new TextView(Main2Activity.this);
                text1.setText("What next?");
                text1.setTextSize(25);
                text1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ++shopping;
                mallButton = new Button(Main2Activity.this);
                l2.addView(text1,lp);
                mallButton.setText("Mall");
                l2.addView(mallButton,lp);
                mallButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ++mall;
                        --numberOfFriends;
                      //  Toast.makeText(Main2Activity.this, "friends " + numberOfFriends, Toast.LENGTH_SHORT).show();
                        if(numberOfFriends>0) {
                            Intent myintent = new Intent(Main2Activity.this, Main2Activity.class);
                            myintent.putExtra("randomNumber", numberOfFriends);
                            insertIntoArray();
                            myintent.putExtra("variables" , arr);
                            startActivity(myintent);
                        }
                        else {
                            Intent myintent = new Intent(Main2Activity.this, Main3Activity.class);
                            initialiseVariables();
                            myintent.putExtra("variables", arr);
                            startActivity(myintent);
                        }
                    }
                });
            }
        });
    }

    public void insertIntoArray() {
        arr = new int[12];
        for(int i=0; i<12; ++i)
            arr[i] = 0;
        arr[0] += entertainment;
        arr[1] += food;
        arr[2] += shopping;
        arr[3] += mall;
        arr[4] += games;
        arr[5] += swimming;
        arr[6] += footabll;
        arr[7] += KFC;
        arr[8] += dominos;
        arr[9] += raman;
        arr[10] += maharaj;
        arr[11] += movie;
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

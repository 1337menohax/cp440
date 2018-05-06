package com.example.cp440;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class battleScreen extends AppCompatActivity {
    TextView tv_time, tv_clicks;
    Button b_click, b_start;

    CountDownTimer timer;
    int time = 0;
    int clicks = 0;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("Clicks", clicks);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int clicks = savedInstanceState.getInt("Clicks");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_screen);
        tv_time = (TextView)findViewById(R.id.tv_time);
        tv_clicks=(TextView)findViewById(R.id.tv_clicks);
        b_click = (Button)findViewById(R.id.b_click);
        b_start= (Button)findViewById(R.id.b_start);

        //b_start.setEnabled(true);
        b_click.setEnabled(true);

        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                time ++;
                tv_time.setText(("Time: " +time + " sec"));
            }


            @Override
            public void onFinish() {
                b_start.setEnabled(true);
                b_click.setEnabled(false);
                tv_time.setText("Time: 0");

            }
        };
        timer.start();
        b_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clicks++;
                tv_clicks.setText(("Clicks: " + clicks));

            }
        });


        /*b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
                time = 0 ;
                clicks=0;
                b_start.setEnabled(false);
                b_click.setEnabled(true);
                tv_time.setText("Time: " +time);
                tv_clicks.setText("Clicks: "+clicks);
            }
        });*/
    }
}

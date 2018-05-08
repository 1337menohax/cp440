package com.example.cp440;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class battleScreen extends AppCompatActivity {
    TextView tv_time, tv_clicks, tv_gtx560, tv_upGtx560, tv_heat, tv_fan1, tv_upFan1;
    Button b_click, b_start, b_gtx560, b_hand, b_fan1;

    CountDownTimer timer;

    private Handler handler = new Handler();
    private int time;
    private int clicks;
    private int gtx560;
    private int gtx560Cost = 5;
    private int fan1;
    private int fan1Cost = 10;
    private long heat = 0;
    private int heatAcc = 0;
    /*SharedPreferences sharedPreferences;
    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Clicks", clicks);
        editor.commit();
        super.onBackPressed();
    }*/

    //fast clock for checking
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //check if you have enough clicks
            tv_upGtx560.setText(("" + gtx560Cost));
            tv_fan1.setText(("" + fan1));
            tv_upFan1.setText(("" + fan1Cost));
            tv_heat.setText(("Heat: " + heat +" /300"));
            if (clicks >= gtx560Cost){
                b_gtx560.setEnabled(true);
            }
            else{
                b_gtx560.setEnabled(false);
            }
            handler.postDelayed(this,100);
        }
    };

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_screen);

        handler.postDelayed(runnable,100);

        tv_time = (TextView)findViewById(R.id.tv_time);
        tv_clicks=(TextView)findViewById(R.id.tv_clicks);
        tv_gtx560=(TextView)findViewById(R.id.tv_gtx560);
        tv_upGtx560=(TextView)findViewById(R.id.tv_upGtx560);
        tv_heat=(TextView)findViewById(R.id.tv_heat);
        tv_fan1=(TextView)findViewById(R.id.tv_fan1);
        tv_upFan1=(TextView)findViewById(R.id.tv_upFan1);


        b_click = (Button)findViewById(R.id.b_click);
        b_start= (Button)findViewById(R.id.b_start);
        b_gtx560 = (Button)findViewById(R.id.b_gtx560);
        b_hand = (Button)findViewById(R.id.b_hand);
        b_fan1 = (Button)findViewById(R.id.b_fan1);

        b_start.setEnabled(true);
        b_click.setEnabled(true);




        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                time ++;
                heat+=heatAcc;
                clicks += gtx560;
                tv_time.setText(("Time: " +time + " sec"));
                tv_clicks.setText(("Clicks: " + clicks));
            }

            @Override
            public void onFinish() {
                b_start.setEnabled(true);
                b_click.setEnabled(false);
                b_gtx560.setEnabled(false);
                handler.removeCallbacks(runnable);//call for stop checker clock
                tv_time.setText("Time: 0");

            }
        };
        timer.start();
        b_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicks++;
            }
        });
        b_gtx560.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clicks-=gtx560Cost;
                //gtx560Cost += Math.pow(gtx560Cost,1.1);
                //gtx560Cost += (gtx560Cost * (Math.pow(Math.log10(gtx560Cost),2)));
                gtx560Cost += 5;
                gtx560++;
                heatAcc+=2;
                tv_gtx560.setText(("" + gtx560));
                tv_upGtx560.setText(("" + gtx560Cost));
                tv_clicks.setText(("Clicks: " + clicks));

            }
        });
        b_fan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clicks-=fan1Cost;
                fan1Cost += 5;
                fan1++;
                heatAcc-=3;
                tv_fan1.setText(("" + fan1));
                tv_upFan1.setText(("" + fan1Cost));
                tv_clicks.setText(("Clicks: " + clicks));

            }
        });
        b_hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heat = 0;
            }
        });

        //Start button function
        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
                time = 0 ;
                clicks=0;
                gtx560=0;
                gtx560Cost=1;
                b_start.setEnabled(false);
                b_click.setEnabled(true);
                tv_time.setText("Time: " +time);
                tv_clicks.setText("Clicks: "+clicks);
                tv_gtx560.setText(("" + gtx560));
                tv_upGtx560.setText(("" + gtx560Cost));
            }
        });
    }
    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("NumClicks", clicks);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        clicks = savedInstanceState.getInt("NumClicks");
    }*/
}

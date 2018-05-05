package com.example.cp440;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {
    private Button skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        skip = (Button)findViewById(R.id.button2);
        skip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openBattleScreen();
            }
        });
    }
    public void openBattleScreen(){
        Intent intent = new Intent(this, battleScreen.class);
        startActivity(intent);
        }
}

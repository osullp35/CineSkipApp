package com.example.admin.cineskip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class timeSelection extends AppCompatActivity implements View.OnClickListener{

    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_selection);

        buttonOne = (Button) findViewById(R.id.timeOne);
        buttonTwo = (Button) findViewById(R.id.timeTwo);
        buttonThree = (Button) findViewById(R.id.timeThree);

        buttonOne.setOnClickListener((View.OnClickListener) this);
        buttonTwo.setOnClickListener((View.OnClickListener) this);
        buttonThree.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {
        if (view == buttonOne || view == buttonTwo || view == buttonThree){
            Intent i = new Intent(this, orderDetails.class);
            startActivity(i);
        }

    }
}

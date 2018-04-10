package com.example.admin.cineskip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class payment extends AppCompatActivity implements View.OnClickListener{

    private Button payment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        payment = (Button) findViewById(R.id.paymentButton);
        payment.setOnClickListener((View.OnClickListener)this);
    }

    @Override
    public void onClick(View view) {
        if(view == payment){
            Intent i = new Intent(this, orderConfirmation.class);
            startActivity(i);
        }
    }
}

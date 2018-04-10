package com.example.admin.cineskip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class orderConfirmation extends AppCompatActivity implements View.OnClickListener{

    private Button tickets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        tickets = (Button)findViewById(R.id.orderTickets);
        tickets.setOnClickListener((View.OnClickListener)this);


    }

    @Override
    public void onClick(View view) {
        if(view == tickets){
            Intent i = new Intent(this, qrTest.class);
            startActivity(i);
        }
    }
}

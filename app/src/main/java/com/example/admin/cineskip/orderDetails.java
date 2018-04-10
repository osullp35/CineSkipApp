package com.example.admin.cineskip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class orderDetails extends AppCompatActivity implements View.OnClickListener {

    private TextView showValueT;
    private TextView showValueF;
    private TextView showValueD;
    private TextView showValueM;
    private Button orderProcessed;

    int counterT = 0; //tickets start on 0
    int counterF = 0;
    int counterD = 0;
    int counterM = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        showValueT = (TextView) findViewById(R.id.ticketNum);
        showValueF = (TextView) findViewById(R.id.foodNum);
        showValueD = (TextView) findViewById(R.id.drinkNum);
        showValueM = (TextView) findViewById(R.id.mealNum);
        orderProcessed = (Button) findViewById(R.id.orderProceed);
        orderProcessed.setOnClickListener((View.OnClickListener) this);

        Spinner mySpinnerT = (Spinner) findViewById(R.id.Ticket);

        ArrayAdapter<String> myAdapterT = new ArrayAdapter<String>(orderDetails.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Ticket));
        myAdapterT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerT.setAdapter(myAdapterT);


        Spinner mySpinnerF = (Spinner) findViewById(R.id.Food);

        ArrayAdapter<String> myAdapterF = new ArrayAdapter<String>(orderDetails.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Food));
        myAdapterF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerF.setAdapter(myAdapterF);


        Spinner mySpinnerD = (Spinner) findViewById(R.id.Drink);

        ArrayAdapter<String> myAdapterD = new ArrayAdapter<String>(orderDetails.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Drink));
        myAdapterD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerD.setAdapter(myAdapterD);


        Spinner mySpinnerM = (Spinner) findViewById(R.id.MealDeal);

        ArrayAdapter<String> myAdapterM = new ArrayAdapter<String>(orderDetails.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.MealDeal));
        myAdapterM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerM.setAdapter(myAdapterM);


    }

    public void countINt (View view) {
        //on each click of this button tickets will increase
        counterT++;
        showValueT.setText(Integer.toString(counterT));
    }

    public void countDEt (View v) {
        counterT--;
        showValueT.setText(Integer.toString(counterT));
    }

    public void countINf (View view) {
        //on each click of this button tickets will increase
        counterF++;
        showValueF.setText(Integer.toString(counterF));
    }

    public void countDEf (View v) {
        counterF--;
        showValueF.setText(Integer.toString(counterF));
    }

    public void countINd (View view) {
        //on each click of this button tickets will increase
        counterD++;
        showValueD.setText(Integer.toString(counterD));
    }

    public void countDEd (View v) {
        counterD--;
        showValueD.setText(Integer.toString(counterD));
    }

    public void countINm (View view) {
        //on each click of this button tickets will increase
        counterM++;
        showValueM.setText(Integer.toString(counterM));
    }

    public void countDEm (View v) {
        counterM--;
        showValueM.setText(Integer.toString(counterM));
    }

    @Override
    public void onClick(View view) {
        if(view == orderProcessed){
            Intent i = new Intent(this, payment.class);
            startActivity(i);
        }
    }
}


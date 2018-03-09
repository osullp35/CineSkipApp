package com.example.admin.cineskip;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registerscreen extends AppCompatActivity implements View.OnClickListener {

    private Button reg;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignIn;
    private EditText editTextnameRegistration;
    private EditText editTextsurnameRegistration;
    private EditText editTextdobRegistration;

    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;

    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerscreen);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), profileActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();


        progressDialog = new ProgressDialog(this);

        reg = (Button) findViewById(R.id.registerbutton);
        textViewSignIn = (TextView) findViewById(R.id.signIn);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextnameRegistration = (EditText) findViewById(R.id.nameRegistration);
        editTextsurnameRegistration = (EditText) findViewById(R.id.surnameRegistration);
        editTextdobRegistration = (EditText) findViewById(R.id.dobRegistration);

        reg.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);



    }

    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User. . .");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String user_id = firebaseAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                            String name = editTextnameRegistration.getText().toString().trim();
                            String surname = editTextsurnameRegistration.getText().toString().trim();
                            String dob = editTextdobRegistration.getText().toString().trim();

                            Map newPost = new HashMap();
                            newPost.put("Name", name);
                            newPost.put("Surname", surname);
                            newPost.put("DOB", dob);

                            current_user_db.setValue(newPost);

                            finish();
                            Toast.makeText(Registerscreen.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), profileActivity.class));
                        } else {
                            Toast.makeText(Registerscreen.this, "Could not register", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void onClick(View view) {
        if(view == reg){
            registerUser();
        }
        if(view == textViewSignIn){
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}

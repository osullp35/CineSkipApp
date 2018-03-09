package com.example.admin.cineskip;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button login;
    private TextView register;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), profileActivity.class));
        }

        login = (Button) findViewById(R.id.loginbutton);
        register = (TextView) findViewById(R.id.register);
        editTextEmail = (EditText) findViewById(R.id.signInEmail);
        editTextPassword = (EditText) findViewById(R.id.signInPassword);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

   private void userLogin(){
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

       progressDialog.setMessage("Signing In. . .");
       progressDialog.show();

       firebaseAuth.signInWithEmailAndPassword(email, password)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       progressDialog.dismiss();
                       if(task.isSuccessful()){
                           finish();
                           startActivity(new Intent(getApplicationContext(), profileActivity.class));
                       } else {
                           Toast.makeText(MainActivity.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                       }
                   }
               });

   }
   //commit comment


    @Override
    public void onClick(View view) {
        if(view == login){
            userLogin();
        }
        if(view == register) {
            finish();
            startActivity(new Intent(this, Registerscreen.class));
        }
    }
}

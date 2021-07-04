package com.example.gofruitsapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null){
            Toast.makeText(this, "Please wait you are already logged in", Toast.LENGTH_SHORT).show();
        }
    }
    public void login (View view){
        startActivity(new Intent(MainActivity2.this, LoginActivity.class));
    }

    public void register (View view){
        startActivity(new Intent(MainActivity2.this, RegisterActivity.class));
    }
}
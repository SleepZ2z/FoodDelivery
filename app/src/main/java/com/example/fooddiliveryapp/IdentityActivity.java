package com.example.fooddiliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class IdentityActivity extends AppCompatActivity {
    private Button buttoncoster,buttondiliver,buttonshop;
    public static boolean VALID_USER = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //(Button)findViewById(R.id.buttoncos).setOnClickListener(this);
        setContentView(R.layout.activity_identity);
        if (!VALID_USER)
        {
            Intent intent = new Intent();
            intent.setClass(this,LoginActivity.class);
            startActivity(intent);
        }
        buttoncoster = (Button)findViewById(R.id.buttoncos);
        buttondiliver = (Button)findViewById(R.id.buttondeliver);
        buttonshop = (Button)findViewById(R.id.buttonshop);
        buttoncoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent();
                intent.setClass(IdentityActivity.this,RoomActivity.class);
                startActivity(intent);*/
                finish();
            }
        });
        buttondiliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(IdentityActivity.this,MenuOfDeliveryActivity.class);
                startActivity(intent);
            }
        });
        buttonshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(IdentityActivity.this,MystoreActivity.class);
                startActivity(intent);
            }
        });
    }
}
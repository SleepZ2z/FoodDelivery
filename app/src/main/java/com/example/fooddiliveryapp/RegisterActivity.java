package com.example.fooddiliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements OnCompleteListener{
    private EditText etEmail;
    private EditText etPassword;
    private EditText etPhone;
    private EditText etname;
    private String UserName;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etPhone = findViewById(R.id.et_phone);
        etname = findViewById(R.id.et_name);



        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void onRegister(View view){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,this);
        //Intent intent = new Intent();
        //intent.setClass(this,RegisterActivity.class);
       // intent= new Intent(this,MainActivity.class);
       // intent.putExtra("abc",etname.getText().toString());
        //startActivity(intent);
    }
    @Override
    public void onComplete(@NonNull Task task)
    {
        if (task.isSuccessful()){
            Toast.makeText(this,"Successful",Toast.LENGTH_LONG).show();
            addUser();
            finish();

        }
        else{
            Toast.makeText(this,"Fail",Toast.LENGTH_LONG).show();
        }
    }
    private void addUser(){
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        UserName=etname.getText().toString();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference userRef = firebaseDatabase.getReference("users");
        DatabaseReference phoneRef = userRef.child(phone);
        Map<String,Object>user = new HashMap<>();
        user.put("email",email);
        user.put("phone",phone);
        user.put("name",UserName);
        phoneRef.updateChildren(user);
        // setContentView(R.layout.activity_menu);
    }
    public void onCancel(View view)
    {
        finish();
    }
}
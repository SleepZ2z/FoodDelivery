package com.example.fooddiliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RoomActivity extends AppCompatActivity {
    int position;
    Button create,join;
    EditText roomInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        Intent intent = getIntent();
        position = intent.getIntExtra("pos", 0);
        create = (Button) findViewById(R.id.btn_create);
        join = (Button) findViewById(R.id.btn_join);
        roomInput = (EditText) findViewById(R.id.roomnum);
        create.setOnClickListener(createRoom);
        join.setOnClickListener(joinRoom);
    }
    private View.OnClickListener joinRoom=new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int num=Integer.parseInt(roomInput.getText().toString());
            Intent intent = new Intent();
            intent.setClass(RoomActivity.this, StoreActivity.class);
            intent.putExtra("pos",position);
            intent.putExtra("room",num);
            startActivity(intent);
        }
    };
    private View.OnClickListener createRoom=new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int num=(int)(Math.random()*100)+1;
            Intent intent = new Intent();
            intent.setClass(RoomActivity.this, StoreActivity.class);
            intent.putExtra("pos",position);
            intent.putExtra("room",num);
            startActivity(intent);
        }
    };
}
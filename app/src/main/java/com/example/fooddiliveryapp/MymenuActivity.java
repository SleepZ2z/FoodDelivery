package com.example.fooddiliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MymenuActivity extends AppCompatActivity {
    private int[] foodimage = {R.drawable.cola, R.drawable.fried, R.drawable.hamburger};
    private String[] imgtxt = {"可樂", "薯條", "漢堡"};
    private String[] payment = {"$30", "$30", "$60"};
    private Dialog dialog;
    private ListView mymenulist;
    private Button confirm,cancel;
    private EditText newMoney,newName;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymenu);
        Intent intent = getIntent();
        position = intent.getIntExtra("pos", 0);
        List<Map<String, Object>> items = new ArrayList<>();
        for (int i = 0; i < foodimage.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("foodimage", foodimage[i]);
            item.put("foodname", imgtxt[i]);
            item.put("payment", payment[i]);
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                items, R.layout.list_item, new String[]{"foodimage", "foodname", "payment"},
                new int[]{R.id.image, R.id.name, R.id.payment});
        mymenulist = (ListView) findViewById(R.id.mymenulist);
        mymenulist.setAdapter(adapter);
        dialog = new Dialog(this);
        dialog.setTitle("Edit comment!");
        dialog.setContentView(R.layout.dialog_mymenu);
        newMoney = (EditText)dialog.findViewById(R.id.editmoney);
        newName = (EditText)dialog.findViewById(R.id.editname);
        confirm = (Button)dialog.findViewById(R.id.btn_edit);
        cancel = (Button)dialog.findViewById(R.id.btn_cancel);
        mymenulist.setOnItemClickListener(new ListView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id){
                dialog.show();
                confirm.setOnClickListener((v) -> {
                    if(newMoney.getText().toString().isEmpty()&&newName.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Text fields cannot be empty", Toast.LENGTH_LONG).show();
                    }
                    else{
                        imgtxt[pos]=newName.getText().toString().trim();
                        payment[pos]="$"+newMoney.getText().toString().trim();
                        List<Map<String, Object>> items = new ArrayList<>();
                        for (int i = 0; i < foodimage.length; i++) {
                            Map<String, Object> item = new HashMap<>();
                            item.put("foodimage", foodimage[i]);
                            item.put("foodname", imgtxt[i]);
                            item.put("payment", payment[i]);
                            items.add(item);
                        }
                        SimpleAdapter adapter = new SimpleAdapter(MymenuActivity.this,
                                items, R.layout.list_item, new String[]{"foodimage", "foodname", "payment"},
                                new int[]{R.id.image, R.id.name, R.id.payment});
                        mymenulist.setAdapter(adapter);
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        dialog.dismiss();
                    }
                });
            }

        });
    }
}
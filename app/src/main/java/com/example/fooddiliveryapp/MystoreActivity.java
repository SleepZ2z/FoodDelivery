package com.example.fooddiliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MystoreActivity extends AppCompatActivity {
    private ListView mystorelist;
    private int[] mystoreimage = {R.drawable.mc, R.drawable.pizzahut,
            R.drawable.kfc, R.drawable.dominopizza};
    private String[] mystorename = {"麥當勞","必勝客","肯德基","達美樂"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystore);
        List<Map<String, Object>> items = new ArrayList<>();
        for (int i = 0; i < mystoreimage.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("storePicture", mystoreimage[i]);
            item.put("storeName", mystorename[i]);
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                items,R.layout.store_list,new String[]{"storePicture","storeName"},
                new int[]{R.id.storePicture,R.id.storeName});
        mystorelist=this.findViewById(R.id.mystorelist);
        mystorelist.setAdapter(adapter);
        mystorelist.setOnItemClickListener(new ListView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent();
                intent.setClass(MystoreActivity.this, MymenuActivity.class);
                intent.putExtra("pos",position);
                startActivity(intent);
            }
        });
    }
}
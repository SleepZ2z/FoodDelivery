package com.example.fooddiliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {
    private int[] foodimage = {R.drawable.cola,R.drawable.fried,R.drawable.hamburger};
    private String[] imgtxt = {"可樂","薯條","漢堡"};
    private String[] payment={"$30","$30","$60"};
    private ListView listmenu;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        position = intent.getIntExtra("pos", 0);
        List<Map<String,Object>> items = new ArrayList<>();
        for(int i=0; i<foodimage.length;i++){
            Map<String,Object> item = new HashMap<>();
            item.put("image",foodimage[i]);
            item.put("name",imgtxt[i]);
            item.put("payment",payment[i]);
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                items,R.layout.list_item,new String[]{"image","name","payment"},
                new int[]{R.id.image,R.id.name,R.id.payment});
        listmenu=(ListView)findViewById(R.id.listmenu);
        listmenu.setAdapter(adapter);
    }
}
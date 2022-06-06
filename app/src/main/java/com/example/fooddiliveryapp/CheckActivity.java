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

public class CheckActivity extends AppCompatActivity {
    private int[] foodimage = {R.drawable.cola, R.drawable.fried, R.drawable.hamburger};
    private String[] imgtxt = {"可樂", "薯條", "漢堡"};
    private int[] payment = {30, 30, 60};
    private int[] count_order;
    private int[] count_money={0,0,0};
    private String[] countText=new String[3];
    private int total=0,position;
    private ListView listorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        Intent intent = getIntent();
        position = intent.getIntExtra("pos", 0);
        count_order = intent.getIntArrayExtra("numofOrder");
        int i=0;
        for(i=0; i<count_money.length; i++)
        {
            count_money[i]=count_order[i]*payment[i];
            countText[i]=String.format("%d",payment[i])+"x"+String.format("%d",count_order[i])+"="+String.format("%d",count_money[i]);
        }
        listorder=(ListView) findViewById(R.id.listcheckout);
        List<Map<String, Object>> items = new ArrayList<>();
        for (i = 0; i < foodimage.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("image", foodimage[i]);
            item.put("name", imgtxt[i]);
            item.put("payment", countText[i]);
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                items, R.layout.list_item, new String[]{"image", "name", "payment"},
                new int[]{R.id.image, R.id.name, R.id.payment});
        listorder.setAdapter(adapter);
    }
}
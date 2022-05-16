package com.example.fooddiliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreActivity extends AppCompatActivity {
    private int[] storeimage = {R.drawable.mc,R.drawable.pizzahut,R.drawable.kfc};
    private String[] storename = {"麥當勞","必勝客","肯德基"};
    private String[] distance={"0.3","0.5","2.0"};
    private int[] foodimage = {R.drawable.cola,R.drawable.fried,R.drawable.hamburger};
    private String[] imgtxt = {"紅茶","綠茶","珍珠奶茶"};
    private String[] payment={"$30","$30","$60"};
    private ListView listView;
    private Button menu;
    private Button comment;
    private ImageView imageView;
    private TextView name;
    private TextView storeDistance;
    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Intent intent = getIntent();
        int position = intent.getIntExtra("pos", 0);
        name = (TextView) findViewById(R.id.name);
        name.setText(storename[position]);
        storeDistance = (TextView) findViewById(R.id.distance);
        storeDistance.setText(distance[position] + "km away");
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageDrawable(getResources().getDrawable(storeimage[position]));
        List<Map<String, Object>> items = new ArrayList<>();
        for (int i = 0; i < foodimage.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("image", foodimage[i]);
            item.put("name", imgtxt[i]);
            item.put("payment", payment[i]);
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                items,R.layout.food_item,new String[]{"image","name","payment"},
                new int[]{R.id.image,R.id.name,R.id.payment});
        listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
    public void back(View v){
        finish();
    }
}
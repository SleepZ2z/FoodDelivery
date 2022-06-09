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

public class MenuActivity extends AppCompatActivity {
    private int[] foodimage = {R.drawable.cola, R.drawable.fried, R.drawable.hamburger};
    private String[] imgtxt = {"可樂", "薯條", "漢堡"};
    private String[] payment = {"$30", "$30", "$60"};
    private int[] num_of_order = {0, 0, 0};
    private String[] foodnum = {"x0", "x0", "x0"};
    private ListView listmenu;
    private Dialog dialog;
    private Button confirm, cancel, checkout;
    private EditText orderCount;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        position = intent.getIntExtra("pos", 0);
        checkout = (Button) findViewById(R.id.check);
        checkout.setOnClickListener(goCheck);
        List<Map<String, Object>> items = new ArrayList<>();
        for (int i = 0; i < foodimage.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("foodimage", foodimage[i]);
            item.put("foodname", imgtxt[i]);
            item.put("payment", payment[i]);
            item.put("foodnum", foodnum[i]);
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                items, R.layout.list_menu, new String[]{"foodimage", "foodname", "payment","foodnum"},
                new int[]{R.id.foodimage, R.id.foodname, R.id.payment,R.id.foodnum});
        listmenu = (ListView) findViewById(R.id.listmenu);
        listmenu.setAdapter(adapter);
        dialog = new Dialog(this);
        dialog.setTitle("Edit comment!");
        dialog.setContentView(R.layout.dialog_order);
        orderCount = (EditText) dialog.findViewById(R.id.ordercount);
        confirm = (Button) dialog.findViewById(R.id.confirm_order);
        cancel = (Button) dialog.findViewById(R.id.cancel_order);
        listmenu.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                dialog.show();
                confirm.setOnClickListener((v) -> {
                    if (orderCount.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Text fields cannot be empty", Toast.LENGTH_LONG).show();
                    } else {
                        num_of_order[pos] = Integer.parseInt(orderCount.getText().toString());
                        foodnum[pos]="x"+String.format("%s",num_of_order[pos]);
                        List<Map<String, Object>> items = new ArrayList<>();
                        for (int i = 0; i < foodimage.length; i++) {
                            Map<String, Object> item = new HashMap<>();
                            item.put("foodimage", foodimage[i]);
                            item.put("foodname", imgtxt[i]);
                            item.put("payment", payment[i]);
                            item.put("foodnum", foodnum[i]);
                            items.add(item);
                        }
                        SimpleAdapter adapter = new SimpleAdapter(MenuActivity.this,
                                items, R.layout.list_menu, new String[]{"foodimage", "foodname", "payment","foodnum"},
                                new int[]{R.id.foodimage, R.id.foodname, R.id.payment,R.id.foodnum});
                        listmenu.setAdapter(adapter);
                        //Toast.makeText(MenuActivity.this, String.valueOf(num_of_order[pos]), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }

        });
    }
    private Button.OnClickListener goCheck=new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setClass(MenuActivity.this, CheckActivity.class);
            intent.putExtra("pos",position);
            intent.putExtra("numofOrder",num_of_order);
            startActivity(intent);
        }
    };
}

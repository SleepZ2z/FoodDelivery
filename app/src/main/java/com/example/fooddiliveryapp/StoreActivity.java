package com.example.fooddiliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreActivity extends AppCompatActivity {
    private int[] storeimage = {R.drawable.mc,R.drawable.pizzahut,R.drawable.kfc};
    private int[] peopleimage = {R.drawable.sleep,R.drawable.totoro,R.drawable.dog};
    private double[] storestar = {4.5,3.0,5.0};
    private String[] peoplename = {"卡比獸","龍貓","小白"};
    private String[] peoplecomment = {"好好吃","好吃","不喜歡"};
    private String[] storename = {"麥當勞","必勝客","肯德基"};
    private String[] distance={"0.3","0.5","2.0"};
    private ListView listView;
    private Button order,join,comment,edit,cancel;
    private ImageView imageView;
    private TextView name;
    private TextView storeDistance;
    private RatingBar ratingBar;
    private Dialog dialog;
    private EditText editComment;
    int position,roomNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Intent intent = getIntent();
        position = intent.getIntExtra("pos", 0);
        roomNum = intent.getIntExtra("room", 0);
        join = (Button) findViewById(R.id.join);
        if(roomNum!=0)
        {
            join.setText(Integer.toString(roomNum));
        }
        order = (Button) findViewById(R.id.order);
        name = (TextView) findViewById(R.id.name);
        comment = (Button) findViewById(R.id.listbutton);
        name.setText(storename[position]);
        storeDistance = (TextView) findViewById(R.id.distance);
        storeDistance.setText(distance[position] + "km away");
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating((float) storestar[position]);
        ratingBar.setIsIndicator(true);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageDrawable(getResources().getDrawable(storeimage[position]));
        List<Map<String, Object>> items = new ArrayList<>();
        for (int i = 0; i < peopleimage.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("image", peopleimage[i]);
            item.put("name", peoplename[i]);
            item.put("payment", peoplecomment[i]);
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                items,R.layout.food_item,new String[]{"image","name","payment"},
                new int[]{R.id.image,R.id.name,R.id.payment});
        listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
        order.setOnClickListener(goOrder);
        join.setOnClickListener(joinRoom);
//        dialog = new Dialog(this);
//        dialog.setTitle("Edit comment!");
//        dialog.setContentView(R.layout.activity_comment);
//        editComment = (EditText)dialog.findViewById(R.id.editcomment);
//        edit = (Button)dialog.findViewById(R.id.btn_edit);
//        cancel = (Button)dialog.findViewById(R.id.btn_cancel);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //dialog.show();
//                Intent intent = new Intent();
//                intent.setClass(StoreActivity.this, MenuActivity.class);
//                intent.putExtra("pos",i);
//                startActivity(intent);
//            }
//        });
//        comment.setOnClickListener(Edit);
    }
    public void back(View v){
        finish();
    }
//    private Button.OnClickListener Edit=new Button.OnClickListener(){
//        @Override
//        public void onClick(View view) {
//            dialog.show();
//            edit.setOnClickListener((v) -> {
//                if(editComment.getText().toString().isEmpty()){
//                    Toast.makeText(getApplicationContext(), "Text fields cannot be empty", Toast.LENGTH_LONG).show();
//                }
//                else{
//                    String new_comment=editComment.getText().toString().trim();
//                    peoplecomment[0]=new_comment;
//                    dialog.dismiss();
//                }
//            });
//            cancel.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v){
//                    dialog.dismiss();
//                }
//            });
//        }
//    };
    private View.OnClickListener joinRoom=new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setClass(StoreActivity.this, RoomActivity.class);
            intent.putExtra("pos",position);
            startActivity(intent);
        }
    };
    private Button.OnClickListener goOrder=new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setClass(StoreActivity.this, MenuActivity.class);
            intent.putExtra("pos",position);
            startActivity(intent);
        }
    };
}
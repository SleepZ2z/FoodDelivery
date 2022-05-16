package com.example.fooddiliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;




public class StoreActivity extends AppCompatActivity {

    ListView storeList;
    int[] resIds = {R.drawable.mc, R.drawable.pizzahut,
            R.drawable.kfc, R.drawable.dominopizza};
    String[] storeNameArray;
    MyAdapter storeAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        //自定義spinner
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(
                this,            //Context
                R.array.address,        //被宣告的字串陣列
                R.layout.myselect_item  //Spinner 要呈現的樣子
        );
        adapter.setDropDownViewResource(R.layout.myspinner_dropdown);
        spinner.setAdapter(adapter);

        //設定搜尋顯示
        SearchView sv = findViewById(R.id.searchView);
        sv.setQueryHint("搜尋想吃的料理");
        sv.setIconifiedByDefault(false);

        //連結元件
        storeList = this.findViewById(R.id.storeList);

        storeNameArray = getResources().getStringArray(R.array.storeName);

        //建立自訂的Adapter
        storeAdapter = new MyAdapter(this);

        //設定ListView資料來源
        storeList.setAdapter(storeAdapter);

        //建立事件
        storeList.setOnItemClickListener(new ListView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?>parent, View view, int position, long id){
                Intent goTest = new Intent();
                goTest.setClass(StoreActivity.this, testActivity.class);
                startActivity(goTest);
                Toast.makeText(StoreActivity.this,
                        "you click " + storeNameArray[position],Toast.LENGTH_SHORT).show();
               // startActivity(new Intent(StoreActivity.this, testActivity));
            }

        });
    }

    class MyAdapter extends BaseAdapter {
        LayoutInflater myInflater;

        public MyAdapter(StoreActivity s){
            myInflater = LayoutInflater.from(s);
        }
        @Override
        public int getCount(){
            return storeNameArray.length;
        }

        @Override
        public Object getItem(int position){
            return storeNameArray[position];
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            convertView = myInflater.inflate(R.layout.store_list, null);

            ImageView storePicture = convertView.findViewById(R.id.storePicture);
            TextView storeName = convertView.findViewById(R.id.storeName);

            storePicture.setImageResource(resIds[position]);
            storeName.setText(storeNameArray[position]);

            return convertView;
        }
    }


}
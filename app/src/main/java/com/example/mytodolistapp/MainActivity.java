package com.example.mytodolistapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity<string> extends AppCompatActivity {
     ListView listView;
     ArrayList<string>arrayList;
     ArrayAdapter<string>arrayAdapter;
     String messageText;
     int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView) findViewById(R.id.listview);
        arrayList=new ArrayList<>();
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,EditMessageClass.class);
                intent.putExtra(intent_constant.INTENT_MESSAGE_DATA,arrayList.get(position).toString());
                intent.putExtra(intent_constant.INTENT_ITEM_POSITION,position);
                startActivityForResult(intent,intent_constant.INTENT_REQUEST_CODE_TWO);
            }
        });
    }
       public void onClick(View v){
           Intent intent = new Intent();
           intent.setClass(MainActivity.this,EditfieldClass.class);
           startActivityForResult(intent,intent_constant.INTENT_REQUEST_CODE);

       }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == intent_constant.INTENT_RESULT_CODE) {
            messageText = data.getStringExtra(intent_constant.INTENT_MESSAGE_FIELD);
            arrayList.add((string) messageText);
            arrayAdapter.notifyDataSetChanged();

        }
        else if(requestCode==intent_constant.INTENT_REQUEST_CODE_TWO){
            messageText = data.getStringExtra(intent_constant.INTENT_CHANGED_MESSAGE);
            position = data.getIntExtra(intent_constant.INTENT_ITEM_POSITION,-1);
            arrayList.remove(position);
            arrayList.add(position, (string) messageText);
            arrayAdapter.notifyDataSetChanged();


        }
    }
}

package com.example.android2.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.android2.R;

import java.util.List;

public class    Demo1MainActivity extends AppCompatActivity {
    private modelAdapter adapter;
    private List<model> list;
    private EditText txtTitel,txtDes;
    modeldatabasehelper dbhelper;
    private Button btnAdd;
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1_main);
        listView = findViewById(R.id.demo11lv);
        txtTitel = findViewById(R.id.demo11TxtTittel);
        txtDes = findViewById(R.id.demo11txtDes);
        btnAdd = findViewById(R.id.demo11btnAdd);
        dbhelper = new modeldatabasehelper(this);
        list = dbhelper.getAllData();
        adapter = new modelAdapter(this,list);
        listView.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titel = txtTitel.getText().toString();
                String des = txtDes.getText().toString();
                if(!titel.isEmpty() && !des.isEmpty()){
                    model model = new model(titel,des);
                    dbhelper.them(model);
                    list.add(model);
                    adapter.notifyDataSetChanged();
                    txtTitel.setText("");
                    txtDes.setText("");
                }
            }
        });
    }
}
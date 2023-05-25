package com.example.tomerge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name, equipment, collection;
    Button insert, view;
    DBHelper DB;
    ArrayList<Model> modelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        equipment = findViewById(R.id.equip);
        collection = findViewById(R.id.numCollection);
        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);

        modelArrayList = new ArrayList<Model>();
        DB = new DBHelper(this);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Userlist.class));
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String equipTXT = equipment.getText().toString();
                String collectTXT = collection.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, equipTXT, collectTXT);
                    if(checkinsertdata == true){
                        Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "No entry Inserted", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
}
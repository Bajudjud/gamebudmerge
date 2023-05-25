package com.example.tomerge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Userlist extends AppCompatActivity implements RecyclerViewInterface {
    RecyclerView recyclerView;
    FloatingActionButton button;
    ArrayList<Model> model = new ArrayList<>();
    DBHelper DB;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        DB = new DBHelper(this);

        button = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, model, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Userlist.this, MainActivity.class);
                startActivity(intent);
            }
        });

        displaydata();
    }

    private void displaydata() {
        Cursor cursor = DB.getData();

        if(cursor.getCount()==0){
            Toast.makeText(Userlist.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
            return;
        }else{
            while (cursor.moveToNext()){
                  model.add(new Model(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
        }
    }

    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(Userlist.this, Details.class);
            intent.putExtra("name", model.get(pos).getName());
            intent.putExtra("equipment", model.get(pos).getEquipment());
            intent.putExtra("collection", model.get(pos).getCollection());

        startActivity(intent);

    }

    @Override
    public void onItemLongClick(int pos) {
        model.remove(pos);
        adapter.notifyItemChanged(pos);
    }
}
package com.example.tomerge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Details extends AppCompatActivity {
    ImageView imageView;
    Button button;
    DBImages DB;
    Bitmap imageDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button)  findViewById(R.id.btnSubmit);
        DB = new DBImages(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] img = byteArray.toByteArray();

                boolean insert = DB.insertdata(img);
                    if(insert == true){
                        Toast.makeText(Details.this, "Image Saved", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Details.this, "Image Not Saved", Toast.LENGTH_SHORT).show();
                    }

                imageDB = DB.getImage();

                imageView.setImageBitmap(imageDB);
            }
        });

        Intent intent = getIntent();

            String name = getIntent().getStringExtra("name");
            String equipment = getIntent().getStringExtra("equipment");
            String collection = getIntent().getStringExtra("collection");

            TextView nameTXT = findViewById(R.id.name);
            TextView equipmentTXT = findViewById(R.id.equip);
            TextView collectionTXT = findViewById(R.id.colNumber);

            nameTXT.setText(name);
            equipmentTXT.setText(equipment);
            collectionTXT.setText(collection);
    }
}
package com.example.lab5_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Song extends AppCompatActivity {
    Button btnChangeView;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        arrayList = new ArrayList<>();

        btnChangeView = findViewById(R.id.btnChangeView);
        listView  = findViewById(R.id.listView);

        btnChangeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Song.this,MainActivity.class));
            }
        });

        try {

            FileInputStream fileInputStream = openFileInput("playlist.txt");

            if ( fileInputStream != null ) {

                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(fileInputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String s = "";
                while ( (s = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(s).append("\n");
                    arrayList.add(stringBuilder.toString());
                }
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
    }
}
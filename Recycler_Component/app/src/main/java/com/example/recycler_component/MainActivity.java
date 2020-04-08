package com.example.recycler_component;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView programmingList =(RecyclerView)findViewById(R.id.programmingList);

        programmingList.setLayoutManager(new LinearLayoutManager(this));

        String[] languages = { "Java","JavaScripts","C#","PHP","C","C++","Python"};

        programmingList.setAdapter(new ProgrammingAdapter(languages));

    }
}

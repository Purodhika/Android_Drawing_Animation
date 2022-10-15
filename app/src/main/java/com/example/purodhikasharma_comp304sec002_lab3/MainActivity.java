package com.example.purodhikasharma_comp304sec002_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] viewItems = getResources().getStringArray(R.array.taskSetup);
        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                viewItems
        );

       // String[] viewItems = getResources().getStringArray(R.array.taskActivities);
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position)
                {
                    case 0:
                        intent = new Intent(MainActivity.this, Exercise01.class);
                        break;

                    case 1:
                        intent = new Intent(MainActivity.this, Exercise02.class);
                        break;


                }
                startActivity(intent);
            }
        });
    }
}
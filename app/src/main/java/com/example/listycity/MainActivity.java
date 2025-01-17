package com.example.listycity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;



public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    private boolean addActive;
    private boolean deleteActive = false;

    private Button buttonAdd;
    private Button buttonDelete;
    private Button buttonConfirm;

    private EditText field;

    final int PURPLE = Color.parseColor("#6750A4");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        buttonAdd = findViewById(R.id.add_city);
        buttonDelete = findViewById(R.id.delete_city);
        buttonConfirm = findViewById(R.id.confirm);

        field = findViewById(R.id.text_input);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<> (this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!addActive) {
                    v.setBackgroundColor(Color.GREEN);
                    deleteActive = false;
                    addActive = true;
                    buttonConfirm.setVisibility(View.VISIBLE);
                    field.setVisibility(View.VISIBLE);
                    buttonDelete.setBackgroundColor(PURPLE);
                }else{
                    v.setBackgroundColor(PURPLE);
                    buttonConfirm.setVisibility(View.GONE);
                    field.setText("");
                    field.setVisibility(View.GONE);
                    addActive = false;
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!deleteActive) {
                    v.setBackgroundColor(Color.GREEN);
                    deleteActive = true;
                    buttonConfirm.setVisibility(View.GONE);
                    field.setText("");
                    field.setVisibility(View.GONE);
                    addActive = false;
                    buttonAdd.setBackgroundColor(PURPLE);
                }else{
                    v.setBackgroundColor(PURPLE);
                    deleteActive = false;
                }
            }
        });

        // This block is based on code from:https://www.geeksforgeeks.org/how-to-dynamically-add-elements-to-a-listview-in-android/
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(deleteActive){
                    cityAdapter.remove(dataList.get(position));
                    cityAdapter.notifyDataSetChanged();
                    buttonDelete.setBackgroundColor(PURPLE);
                    deleteActive = false;
                }
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = field.getText().toString();

                if(!city.isEmpty()){
                    dataList.add(city);
                    cityAdapter.notifyDataSetChanged();
                    field.setText("");
                }

                buttonConfirm.setVisibility(View.GONE);
                field.setVisibility(View.GONE);
                addActive = false;
                buttonAdd.setBackgroundColor(PURPLE);
            }
        });



    }
}
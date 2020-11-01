package com.example.androidappssdk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.calculator.InstalledApps;
import com.example.calculator.AppsProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView apps_list;
    EditText editTextSearch;
    AppsAdapter adapter;
    ImageView refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_main);
        refresh = (ImageView) findViewById(R.id.refresh);
        apps_list = (RecyclerView) findViewById(R.id.apps);
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        final List<InstalledApps> apps = AppsProvider.getApps(getApplicationContext());

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString(), (ArrayList<InstalledApps>) apps);
            }
        });

        adapter = new AppsAdapter((ArrayList<InstalledApps>) apps, MainActivity.this);
        apps_list.setHasFixedSize(true);
        apps_list.setLayoutManager(new LinearLayoutManager(this));
        apps_list.setAdapter(adapter);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.filterList((ArrayList<InstalledApps>) apps);
            }
        });


    }


    private void filter(String text, ArrayList<InstalledApps> orignalList) {
        //new array list that will hold the filtered data
        ArrayList<InstalledApps> filterdNames = new ArrayList<>();
        for (int i = 0; i < orignalList.size(); i++) {

            if (orignalList.get(i).getLabel().toLowerCase().contains(text.toLowerCase())) {
                filterdNames.add(orignalList.get(i));
            }
        }


        //calling a method of the adapter class and passing the filtered list

        adapter.filterList(filterdNames);
    }
}
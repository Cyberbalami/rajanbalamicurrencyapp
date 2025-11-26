package com.example.rajanbalamicurrencyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rajanbalamicurrencyapp.utilities.AsyncDataLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText inputCurrency;
    RecyclerView recyclerView;
    RatesAdapter adapter;
    List<String> displayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputCurrency = findViewById(R.id.inputCurrency);
        recyclerView = findViewById(R.id.recyclerView);

        adapter = new RatesAdapter(displayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        inputCurrency.setOnEditorActionListener((textView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String userInput = inputCurrency.getText().toString().trim().toUpperCase();

                if (userInput.isEmpty()) {
                    Toast.makeText(this, "Enter a currency!", Toast.LENGTH_SHORT).show();
                } else {

                    // *********** FIXED *************
                    String url = "https://api.exchangerate-api.com/v4/latest/" + userInput;

                    new AsyncDataLoader(data -> {
                        displayList.clear();

                        if (data == null) {
                            displayList.add("Error loading data!");
                        } else {
                            for (String key : data.keySet()) {
                                displayList.add(key + " = " + data.get(key));
                            }
                        }

                        adapter.notifyDataSetChanged();
                    }).execute(url);  // now we send the REAL URL
                }
                return true;
            }
            return false;
        });
    }
}
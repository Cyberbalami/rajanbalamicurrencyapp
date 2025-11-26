package com.example.rajanbalamicurrencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.rajanbalamicurrencyapp.utilities.AsyncDataLoader;
import com.example.rajanbalamicurrencyapp.utilities.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewRates;
    private EditText edtFilter;

    private ArrayList<String> rateList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewRates = findViewById(R.id.listViewRates);
        edtFilter = findViewById(R.id.edtFilter);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rateList);
        listViewRates.setAdapter(adapter);

        new AsyncDataLoader(this).execute(Constants.API_URL);

        edtFilter.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    public void updateRates(ArrayList<String> rates) {
        rateList.clear();
        rateList.addAll(rates);
        adapter.notifyDataSetChanged();
    }
}

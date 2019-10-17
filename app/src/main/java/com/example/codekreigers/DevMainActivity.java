package com.example.codekreigers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class DevMainActivity extends AppCompatActivity {

    private List<DevCard> infoList = new ArrayList<>();
    DevCardAdapter mDevCardAdapter;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dev_activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mDevCardAdapter = new DevCardAdapter(infoList,getApplicationContext());
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL,false);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mDevCardAdapter);

        DevCard card = new DevCard("Ammar\nAlavi", "Pre-Final\nYear",R.drawable.aditya);
        infoList.add(card);
        card = new DevCard("Aditya\nBhandari","Sophomore\nYear", R.drawable.aditya);
        infoList.add(card);
        card = new DevCard("Kunal\nJuneja","Sophomore\nYear", R.drawable.aditya);
        infoList.add(card);
        card = new DevCard("Nikita\nChauhan","Sophomore\nYear", R.drawable.aditya);
        infoList.add(card);
        card = new DevCard("Rishika\nPatel","Sophomore\nYear", R.drawable.aditya);
        infoList.add(card);
        mDevCardAdapter.notifyDataSetChanged();
    }
}

package com.example.codekreigers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class DevMainActivity extends AppCompatActivity {

    private List<DevCard> infoList = new ArrayList<>();
    DevCardAdapter mDevCardAdapter;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    FloatingActionButton feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dev_activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        feedback = findViewById(R.id.feedback);
        mDevCardAdapter = new DevCardAdapter(infoList,getApplicationContext());
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL,false);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mDevCardAdapter);

        DevCard card = new DevCard("Ammar\nAlavi", "Pre-Final\nYear",R.drawable.ammar_boss_dev);
        infoList.add(card);
        card = new DevCard("Aditya\nBhandari","Sophomore\nYear", R.drawable.aditya_dev);
        infoList.add(card);
        card = new DevCard("Kunal\nJuneja","Sophomore\nYear", R.drawable.kunal_dev);
        infoList.add(card);
        card = new DevCard("Nikita\nChauhan","Sophomore\nYear", R.drawable.nikita_dev);
        infoList.add(card);
        card = new DevCard("Rishika\nPatel","Sophomore\nYear", R.drawable.rishika_dev);
        infoList.add(card);
        mDevCardAdapter.notifyDataSetChanged();

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TO = "superalavi20@gmail.com"+",adityabhandari1500@gmail.com"+",kunaljuneja07@gmail.com"+",chauhannikita199@gmail.com"+",rishikaumesh@gmail.com" ;
                Intent browser1Intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", TO,null));
                browser1Intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Code Kriegers Feedback");
                browser1Intent.putExtra(Intent.EXTRA_TEXT," ");
                startActivity(Intent.createChooser(browser1Intent, "Choose an email client: "));
            }
        });
    }

    public void onBack(View view) {
        finish();
    }
}

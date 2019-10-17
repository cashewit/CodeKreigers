package com.example.codekreigers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StartGame extends AppCompatActivity {
    private List<Questions> questionList = new ArrayList<>();
    boolean visitedOnce = false;
    private RecyclerView mRecyclerView;
    private QuestionAdapter mAdapter;
    private static StartGame instance;
    private static int count = 0;
    FloatingActionButton myFAB;
    private RecyclerView.LayoutManager mLayoutManager;
    LinearSmoothScroller mLinearSmoothScroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        mRecyclerView = findViewById(R.id.recyclerView);
        myFAB = findViewById(R.id.floatingActionButton);

        mAdapter = new QuestionAdapter(questionList,getApplicationContext());
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayout.HORIZONTAL,false);

        myFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.isClickable = true;

        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(mRecyclerView);

        SharedPreferences myVisits = getSharedPreferences("myVisits",MODE_PRIVATE);
        visitedOnce = myVisits.getBoolean("visitedOnce",false);

        if(!visitedOnce) {
            prepareQuestionData(count, false);
            changeVisited();
        }

        else {
            SharedPreferences listRegeneration = getSharedPreferences("listRegeneration", MODE_PRIVATE);
            count = listRegeneration.getInt("LastElement", 1);
            for (int i = 0; i < count-1; i++) {
                prepareQuestionData(i, true);
            }
            prepareQuestionData(count-1, false);
        }

        instance = this;
    }

    public static StartGame getInstance(){
        return instance;
    }

    public void prepareQuestionData(int questionCount,boolean buttonValue){
        Questions question = new Questions("This is question"+questionCount,"12",buttonValue);
        questionList.add(question);
        int size = questionList.size();
        Toast.makeText(this,"List Size: "+size,Toast.LENGTH_SHORT).show();
        mAdapter.notifyDataSetChanged();

        mLinearSmoothScroller = new LinearSmoothScroller(getBaseContext());
        mLinearSmoothScroller.setTargetPosition(questionList.size()-1);
        mLayoutManager.startSmoothScroll(mLinearSmoothScroller);
    }

    public void changeVisited(){
        SharedPreferences myVisits = getSharedPreferences("myVisits", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = myVisits.edit();
        myEditor.putBoolean("visitedOnce",true);
        myEditor.apply();
    }

}

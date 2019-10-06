package com.example.codekreigers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StartGame extends AppCompatActivity {
    private List<Questions> questionList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private QuestionAdapter mAdapter;
    private static StartGame instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        mRecyclerView = findViewById(R.id.recyclerView);

        mAdapter = new QuestionAdapter(questionList,getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayout.HORIZONTAL,false);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(mRecyclerView);

        prepareQuestionData();


        instance = this;
    }

    public static StartGame getInstance(){
        return instance;
    }

    public void prepareQuestionData(){
        Questions question = new Questions("This is the first question","12");
        questionList.add(question);
        Toast.makeText(this,"Adding Item!",Toast.LENGTH_SHORT).show();
        mAdapter.notifyDataSetChanged();

    }

}

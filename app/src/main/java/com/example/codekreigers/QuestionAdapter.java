package com.example.codekreigers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {

    private List<Questions> questionList;
    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView questionDisplay;
        public Button mySubmitButton;

        public MyViewHolder(View view){
            super(view);
            questionDisplay = view.findViewById(R.id.questionDisplay);
            mySubmitButton = view.findViewById(R.id.submitButton);

            questionDisplay.setMovementMethod(new ScrollingMovementMethod());

            mySubmitButton.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            StartGame.getInstance().prepareQuestionData();
        }
    }


    public QuestionAdapter(List<Questions> questionList,Context context){
        this.questionList = questionList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Questions question = questionList.get(position);
        myViewHolder.questionDisplay.setText(question.getQuestion());
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }
}


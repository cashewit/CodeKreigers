package com.example.codekreigers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {

    private List<Questions> questionList;
    private Context mContext;
    public boolean isClickable = true;


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
            int position = (int) mySubmitButton.getTag();


            //Toast.makeText(StartGame.getInstance(),"Position is: " +position,Toast.LENGTH_SHORT).show();
            if(questionList.get(position).isAnswered()){
                Toast.makeText(StartGame.getInstance(),"Already answered!",Toast.LENGTH_SHORT).show();
                return;
            }
            SharedPreferences listRegeneration = StartGame.getInstance().getSharedPreferences("listRegeneration",Context.MODE_PRIVATE);
            int questionCount = listRegeneration.getInt("LastElement",1);
            StartGame.getInstance().prepareQuestionData(questionCount,false);
            questionList.get(position).setAnswered(true);

            SharedPreferences.Editor myEditor2 = listRegeneration.edit();
            myEditor2.putInt("LastElement",questionList.size());
            myEditor2.apply();
        }
    }


    public QuestionAdapter(List<Questions> questionList,Context context){
        this.questionList = questionList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_card_view,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Questions question = questionList.get(position);
        myViewHolder.questionDisplay.setText(question.getQuestion());
        myViewHolder.mySubmitButton.setTag(position);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }
}


package com.example.codekreigers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class TeamVision extends AppCompatActivity {

    private TextView teamDescription1,teamDescription2,teamDescription3;
    private ImageView arrowImage1,arrowImage2,arrowImage3;
    private LinearLayout mLinearLayout1,mLinearLayout2,mLinearLayout3;
    private boolean count1 = false,count2 = false,count3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_vision);

        teamDescription1 = findViewById(R.id.teamDescription1);
        teamDescription1.setVisibility(View.GONE);
        teamDescription2 = findViewById(R.id.teamDescription2);
        teamDescription2.setVisibility(View.GONE);
        teamDescription3 = findViewById(R.id.teamDescription3);
        teamDescription3.setVisibility(View.GONE);

        mLinearLayout1 = findViewById(R.id.arrow1);
        arrowImage1 = findViewById(R.id.arrowImage1);
        mLinearLayout2 = findViewById(R.id.arrow2);
        arrowImage2 = findViewById(R.id.arrowImage2);
        mLinearLayout3 = findViewById(R.id.arrow3);
        arrowImage3 = findViewById(R.id.arrowImage3);



        mLinearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!count1) {
                    teamDescription1.setVisibility(View.VISIBLE);
                    teamDescription1.setText(R.string.final_year);
                    arrowImage1.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                    count1 = true;
                }
                else{
                    teamDescription1.setVisibility(View.GONE);
                    teamDescription1.setText(" ");
                    arrowImage1.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    count1 = false;

                }
            }
        });

        mLinearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!count2) {
                    teamDescription2.setVisibility(View.VISIBLE);
                    teamDescription2.setText(R.string.pre_final_year);
                    arrowImage2.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                    count2 = true;
                }
                else{
                    teamDescription2.setVisibility(View.GONE);
                    teamDescription2.setText(" ");
                    arrowImage2.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    count2 = false;

                }
            }
        });

        mLinearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!count3) {
                    teamDescription3.setVisibility(View.VISIBLE);
                    teamDescription3.setText(R.string.second_year);
                    arrowImage3.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                    count3 = true;
                }
                else{
                    teamDescription3.setVisibility(View.GONE);
                    teamDescription3.setText(" ");
                    arrowImage3.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    count3 = false;

                }
            }
        });
    }

    public void onBack(View view) {
        finish();
    }
}

package com.example.codekreigers;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    private Button startButton;
    private TextView nameDisplay;
    private String teamMemberName1;
    private String teamMemberName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        SharedPreferences member1 = getSharedPreferences("member1",MODE_PRIVATE);
        teamMemberName1 = member1.getString("member1Name","Member 1");

        SharedPreferences member2 = getSharedPreferences("member2",MODE_PRIVATE);
        teamMemberName2 = member2.getString("member2Name","Member 2");

        startButton = findViewById(R.id.startGame);
        nameDisplay = findViewById(R.id.nameDisplayTextView);

        String display = teamMemberName1 + "\nand\n" + teamMemberName2;

        nameDisplay.setText(display);

        AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f);
        fadeIn.setRepeatMode(Animation.REVERSE);
        fadeIn.setRepeatCount(Animation.INFINITE);
        fadeIn.setDuration(200);

        startButton.startAnimation(fadeIn);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences loginPref = getSharedPreferences("loginPref",MODE_PRIVATE);
                boolean myLoginBool = loginPref.getBoolean("myLoginBool",true);

                if(myLoginBool) {
                    Intent myIntent = new Intent(HomePage.this, LoginActivity.class);
                    startActivity(myIntent);
                }
                else{
                    Intent myIntent = new Intent(HomePage.this, StartGame.class);
                    startActivity(myIntent);
                }
            }
        });
    }


}


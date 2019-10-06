package com.example.codekreigers;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterName extends AppCompatActivity {

    private Button enterButton;
    private EditText teamMember1;
    private EditText teamMember2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);



        enterButton = findViewById(R.id.enterButton);
        teamMember1 = findViewById(R.id.teamMember1);
        teamMember2 = findViewById(R.id.teamMember2);


        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((TextUtils.isEmpty(teamMember1.getText())) || (TextUtils.isEmpty(teamMember2.getText())))
                {
                    Toast.makeText(EnterName.this, "Name fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent myIntent = new Intent(EnterName.this, HomePage.class);

                    SharedPreferences member1 = getSharedPreferences("member1",MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = member1.edit();
                    editor1.putString("member1Name",teamMember1.getText().toString());
                    editor1.apply();

                    SharedPreferences member2 = getSharedPreferences("member2",MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = member2.edit();
                    editor2.putString("member2Name",teamMember2.getText().toString());
                    editor2.apply();

                    SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
                    final SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("firstStart",false);
                    editor.apply();

                    startActivity(myIntent);
                    finish();
                }
            }
        });

    }
}

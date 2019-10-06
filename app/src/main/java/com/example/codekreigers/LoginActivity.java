package com.example.codekreigers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public EditText usernameInput;
    private EditText passwordInput;
    private Button loginButton;
    private String control = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameInput = findViewById(R.id.username);
        passwordInput = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginBtn);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((TextUtils.isEmpty(usernameInput.getText())) || (TextUtils.isEmpty(passwordInput.getText())) )
                {
                    Toast.makeText(LoginActivity.this, "Username and Password required!", Toast.LENGTH_SHORT).show();
                }
                else{
                    validate(passwordInput.getText().toString());
                    passwordInput.getText().clear();
                }
            }
        });

    }

    private void validate(String password)
    {
        if(control.equals(password)){
            SharedPreferences loginPref = getSharedPreferences("loginPref",MODE_PRIVATE);
            SharedPreferences.Editor myEditor = loginPref.edit();
            myEditor.putBoolean("myLoginBool",false);
            myEditor.apply();

            passwordInput.getText().clear();
            Intent myIntent = new Intent(LoginActivity.this, StartGame.class);
            startActivity(myIntent);
            finish();
        }
        else{
            Toast.makeText(this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

}

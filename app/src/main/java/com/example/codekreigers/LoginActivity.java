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
    private String control = "hello_Vision";

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
                    validate(usernameInput.getText().toString(), passwordInput.getText().toString());
                    passwordInput.getText().clear();
                }
            }
        });

    }

    private void validate(String username, String password)
    {
        if(username.matches("CK@[1-9][0-9][0-9]") && control.equals(password)){
            SharedPreferences userCKid = getSharedPreferences("userCkid",MODE_PRIVATE);
            SharedPreferences.Editor myEditor = userCKid.edit();
            myEditor.putInt("CKid",Integer.parseInt(username.substring(3)));
            myEditor.apply();

            passwordInput.getText().clear();
            Intent myIntent1 = new Intent(LoginActivity.this,InstructionMainActivity.class);
            startActivity(myIntent1);
            finish();
        }
        else{
            Toast.makeText(this, "Incorrect CK ID or Password!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

}

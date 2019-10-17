package com.example.codekreigers;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    private ImageView menuButton;
    private ImageView backButton;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private TextView nameDisplay1,nameDisplay2;
    private String teamMemberName1;
    private String teamMemberName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_view);

        menuButton = findViewById(R.id.menu_button);
        mDrawerLayout = findViewById(R.id.drawer_view);
        mNavigationView = findViewById(R.id.nav_view);

        View header = mNavigationView.getHeaderView(0);
        backButton = header.findViewById(R.id.back_button);
        nameDisplay1 = header.findViewById(R.id.teamMember1);
        nameDisplay2 = header.findViewById(R.id.teamMember2);

        SharedPreferences member1 = getSharedPreferences("member1",MODE_PRIVATE);
        teamMemberName1 = member1.getString("member1Name","Member 1");
        SharedPreferences member2 = getSharedPreferences("member2",MODE_PRIVATE);
        teamMemberName2 = member2.getString("member2Name","Member 2");

        nameDisplay1.setText(teamMemberName1);
        nameDisplay2.setText(teamMemberName2);


        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.nav_home:
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        break;

                    case R.id.nav_play:
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
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        break;

                    case R.id.nav_instructions:
                        Intent myIntent_instruction = new Intent(HomePage.this, InstructionMainActivity.class);
                        startActivity(myIntent_instruction);
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        break;

                    case R.id.nav_team:
                        Toast.makeText(HomePage.this,"Team Vision Pressed!",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        break;

                    case R.id.nav_checkpoints:
                        Intent myIntent_checkpoints = new Intent(HomePage.this, CheckPoint.class);
                        startActivity(myIntent_checkpoints);
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        break;

                    case R.id.nav_devs:
                        Intent myIntent_devs = new Intent(HomePage.this, DevMainActivity.class);
                        startActivity(myIntent_devs);
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        break;

                    case R.id.nav_feedback:
                        Toast.makeText(HomePage.this,"We don't care about your feedback!",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                }
                return false;
            }
        });
    }
}
package com.example.codekreigers;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
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

                        SharedPreferences gameOver = getSharedPreferences("gameOver",MODE_PRIVATE);
                        boolean over = gameOver.getBoolean("gameOver",false);

                        SharedPreferences Completed = getSharedPreferences("gameCompleted",MODE_PRIVATE);
                        boolean completed = Completed.getBoolean("gameCompleted",false);

                        final String MyPREFERENCES = "MyPrefs" ;
                        int minutes,seconds,hours;
                        SharedPreferences sharedpreferences;
                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

                        seconds=sharedpreferences.getInt("second",0);
                        minutes=sharedpreferences.getInt("minute",0);
                        hours=sharedpreferences.getInt("hour",0);

                        if(myLoginBool) {
                            Intent myIntent = new Intent(HomePage.this, LoginActivity.class);
                            startActivity(myIntent);
                        }
                        else if(over){
                            AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
                            builder.setTitle("Game Over!");
                            builder.setCancelable(false);
                            builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                        else if(completed){
                            AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
                            builder.setTitle("Game Completed!");
                            builder.setCancelable(false);
                            builder.setMessage("Successfully completed game!\n Time taken: "+hours+":"+minutes+":"+seconds);
                            builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
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
                        Intent myIntent_team = new Intent(HomePage.this,TeamVision.class);
                        startActivity(myIntent_team);
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        break;

                    case R.id.nav_checkpoints:
                        AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
                        builder.setView(R.layout.checkpoint_activity);
                        builder.setCancelable(false);
                        builder.setTitle("Checkpoints");
                        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog checkpoints = builder.create();
                        checkpoints.show();
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        break;

                    case R.id.nav_devs:
                        Intent myIntent_devs = new Intent(HomePage.this, DevMainActivity.class);
                        startActivity(myIntent_devs);
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        break;

                    case R.id.website:
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                        String url = "https://visionmanit.org";
                        browserIntent.setData(Uri.parse(url));
                        startActivity(browserIntent);
                        break;

                    case R.id.ckFacebook:
                        Intent browser2Intent = new Intent(Intent.ACTION_VIEW);
                        String url2 = "https://www.facebook.com/codekriegers/";
                        browser2Intent.setData(Uri.parse(url2));
                        startActivity(browser2Intent);
                        break;

                    case R.id.facebook:
                        Intent browser3Intent = new Intent(Intent.ACTION_VIEW);
                        String url3 = "https://www.facebook.com/visionmanit/";
                        browser3Intent.setData(Uri.parse(url3));
                        startActivity(browser3Intent);
                        break;

                    case R.id.Email:
                        String TO = "INFO@VISIONMANIT.ORG";
                        Intent browser1Intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", TO,null));
                        startActivity(Intent.createChooser(browser1Intent, "Choose an email client: "));
                        break;

                }
                return false;
            }
        });
    }
}
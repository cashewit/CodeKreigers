package com.example.codekreigers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StartGame extends AppCompatActivity {
    public List<Questions> questionList = new ArrayList<>();
    boolean visitedOnce = false;
    private RecyclerView mRecyclerView;
    private QuestionAdapter mAdapter;
    private static StartGame instance;
    private static int count = 0;
    FloatingActionButton myFAB;
    private int life;
    private RecyclerView.LayoutManager mLayoutManager;
    LinearSmoothScroller mLinearSmoothScroller;
    static String answer="";

    public static final String MyPREFERENCES = "MyPrefs" ;
    TextView timerTextView;
    long startTime = 0;
    long millis;
    int minutes,seconds,hours,m,s,h;
    SharedPreferences sharedpreferences;
    private int[] lifeImages = {R.id.life1, R.id.life2, R.id.life3};


    public Questions[] myQuestionArray = {
            new Questions(R.string.path2_question1,"6",false,"SBI ATM"),
            new Questions(R.string.path1_question1,"18",false,"MARIO GRAFFITI"),
            new Questions(R.string.path3_question1,"21",false,"TRAIN"),
            new Questions(R.string.path2_question2,"64",false,"DIRECTOR OFFICE"),
            new Questions(R.string.path1_question2,"4",false,"AMUL"),
            new Questions(R.string.path3_question2,"20",false,"EEOAT"),
            new Questions(R.string.path2_question3,"15",false,"NESCAFE"),
            new Questions(R.string.path1_question3,"4",false,"ROLTA"),
            new Questions(R.string.path3_question3,"10",false,"G9"),
            new Questions(R.string.path2_question4,"7",false,"CHEMISTRY LAB"),
            new Questions(R.string.path1_question4,"28",false,"NESCAFE"),
            new Questions(R.string.path3_question4,"12",false,"G6"),
            new Questions(R.string.path2_question5,"8",false,"EEOAT"),
            new Questions(R.string.path1_question5,"20",false,"ED HALL"),
            new Questions(R.string.path3_question5,"4",false,"NTB"),
            new Questions(R.string.path2_question6,"14",false,"ARCHI DEPARTMENT"),
            new Questions(R.string.path1_question6,"0",false,"NTB"),
            new Questions(R.string.path3_question6,"Vision",false,"TB212"),
            new Questions(R.string.path2_question7,"4",false,"NTB"),
            new Questions(R.string.path1_question7,"Vision",false,"TB212"),
            new Questions(R.string.path2_question8,"Vision",false,"TB212")
    };


    int[] sizeArray={7,6,6};

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            millis = System.currentTimeMillis() - startTime;
            seconds = (int) (millis / 1000);
            minutes = seconds / 60;
            seconds = seconds % 60;
            hours = minutes/60;
            minutes=minutes%60;

            s=59-seconds;
            m=59-minutes;
            h=2-hours;

            if(s==0 && m==0 && h==0){
                SharedPreferences gameOver = getSharedPreferences("gameOver",MODE_PRIVATE);
                SharedPreferences.Editor myEditor = gameOver.edit();
                myEditor.putBoolean("gameOver",true);
                myEditor.apply();

                AlertDialog.Builder builder = new AlertDialog.Builder(StartGame.this);
                builder.setTitle("Game Over!");
                builder.setMessage("Time's Up");
                builder.setCancelable(false);
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
            }

            if(s<=0){
                s+=60;
                m--;
            }

            if(m<=0){
                m+=60;
                h--;
            }
            m=m%60;
            s=s%60;

            timerTextView.setText(String.format("%01d:%02d:%02d",h, m, s));

            if(h==0 && m==0 && s==0) {
                SharedPreferences gameOver = getSharedPreferences("gameOver", MODE_PRIVATE);
                SharedPreferences.Editor myEditor = gameOver.edit();
                myEditor.putBoolean("gameOver", true);
                myEditor.apply();

                AlertDialog.Builder builder = new AlertDialog.Builder(StartGame.this);
                builder.setTitle("Game Over!");
                builder.setMessage("Time's Up");
                builder.setCancelable(false);
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
            }

            timerHandler.postDelayed(this, 1000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        //Find Views by IDs
        mRecyclerView = findViewById(R.id.recyclerView);
        myFAB = findViewById(R.id.floatingActionButton);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        startTime=sharedpreferences.getLong("start",System.currentTimeMillis());
        millis=sharedpreferences.getLong("millis",0);
        seconds=sharedpreferences.getInt("second",0);
        minutes=sharedpreferences.getInt("minute",0);
        hours=sharedpreferences.getInt("hour",0);

        if(hours >=3 ){
            SharedPreferences gameOver = getSharedPreferences("gameOver",MODE_PRIVATE);
            SharedPreferences.Editor myEditor = gameOver.edit();
            myEditor.putBoolean("gameOver",true);
            myEditor.apply();

            AlertDialog.Builder builder = new AlertDialog.Builder(StartGame.this);
            builder.setTitle("Game Over!");
            builder.setMessage("Time's Up");
            builder.setCancelable(false);
            builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
        }

        timerTextView = findViewById(R.id.timer);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        timerHandler.postDelayed(timerRunnable, 0);

        //Recycler View Adapter Configurations
        mAdapter = new QuestionAdapter(questionList,getApplicationContext());
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayout.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.isClickable = true;

        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(mRecyclerView);


        //Floating Action Button onClickListener
        myFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StartGame.this);
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
            }
        });


        SharedPreferences cameraOpenedOnce = getSharedPreferences("cameraOpened",MODE_PRIVATE);
        boolean opened = cameraOpenedOnce.getBoolean("openedCamera",false);

        //Shared Preferences to check if visited the page already
        SharedPreferences myVisits = getSharedPreferences("myVisits",MODE_PRIVATE);
        visitedOnce = myVisits.getBoolean("visitedOnce",false);

        //Shared Preferences for username. Path to be decided by this
        SharedPreferences userCKid = getSharedPreferences("userCkid",MODE_PRIVATE);
        int username = userCKid.getInt("CKid",100);

        //Setting starting position
        int startPosition = username%3;

        SharedPreferences listRegeneration = getSharedPreferences("listRegeneration", Context.MODE_PRIVATE);
        int questionCount = listRegeneration.getInt("LastElement", startPosition + 3);



        if(opened) {
           checkLocation();
        }




        //If first time visit
        if(!visitedOnce) {
            prepareQuestionData(myQuestionArray[startPosition]);
            for(int i=0;i<3;i++){
                ImageView heart = findViewById(lifeImages[i]);
                heart.setVisibility(View.VISIBLE);
            }
            changeVisited();
        }

        if(visitedOnce) {
            SharedPreferences listRegeneration1 = getSharedPreferences("listRegeneration", MODE_PRIVATE);
            count = listRegeneration1.getInt("LastElement", startPosition+3);
            for (int i = startPosition; i < count-3; i+=3) {
                prepareQuestionData(myQuestionArray[i]);
                myQuestionArray[i].setAnswered(true);
            }
            SharedPreferences lives = getSharedPreferences("lives", Context.MODE_PRIVATE);
            int currentLives = lives.getInt("gameLives", 3);

            for(int i=0;i<currentLives;i++) {
                ImageView heart = findViewById(lifeImages[i]);
                heart.setVisibility(View.VISIBLE);
            }

            try {
                prepareQuestionData(myQuestionArray[count - 3]);
            }catch (ArrayIndexOutOfBoundsException e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StartGame.this);
                builder.setTitle("Game Completed!");
                builder.setCancelable(false);
                builder.setMessage("Successfully completed game!\n Time taken: " + hours + ":" + minutes + ":" + seconds);
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        SharedPreferences completed = getSharedPreferences("gameCompleted", MODE_PRIVATE);
                        SharedPreferences.Editor myEditor = completed.edit();
                        myEditor.putBoolean("gameCompleted", true);
                        myEditor.apply();
                        finish();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }

        instance = this;
    }

    public void prepareQuestionData(Questions newQuestion){
        Questions question = newQuestion;
        questionList.add(question);
        mAdapter.notifyDataSetChanged();

        mLinearSmoothScroller = new LinearSmoothScroller(getBaseContext());
        mLinearSmoothScroller.setTargetPosition(questionList.size()-1);
        mLayoutManager.startSmoothScroll(mLinearSmoothScroller);
    }



    public void changeVisited(){
        SharedPreferences myVisits = getSharedPreferences("myVisits", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = myVisits.edit();
        myEditor.putBoolean("visitedOnce",true);
        myEditor.apply();
    }

    public void reduceLives(int countLives){
        ImageView lifeView = findViewById(lifeImages[countLives-1]);
        lifeView.setVisibility(View.INVISIBLE);

        Log.d("Code Kriegers","Lives Left: "+countLives);
        if(countLives==1){
            SharedPreferences gameOver = getSharedPreferences("gameOver",MODE_PRIVATE);
            SharedPreferences.Editor gameOver_Editor = gameOver.edit();
            gameOver_Editor.putBoolean("gameOver",true);
            gameOver_Editor.apply();

            AlertDialog.Builder builder = new AlertDialog.Builder(StartGame.this);
            builder.setTitle("Game Over!");
            builder.setMessage("You lost all your lives!");
            builder.setCancelable(false);
            builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }

        SharedPreferences lives = getSharedPreferences("lives", Context.MODE_PRIVATE);
        int currentLives = lives.getInt("gameLives", 3);
        SharedPreferences.Editor livesEditor = lives.edit();
        livesEditor.putInt("gameLives", currentLives - 1);
        livesEditor.apply();
    }

    public void checkLocation(){
        SharedPreferences userCKid = getSharedPreferences("userCkid", Context.MODE_PRIVATE);
        int username = userCKid.getInt("CKid", 100);
        int startPosition = username % 3;

        final SharedPreferences listRegeneration = getSharedPreferences("listRegeneration", Context.MODE_PRIVATE);
        final int questionCount = listRegeneration.getInt("LastElement", startPosition + 3);

        SharedPreferences lives = getSharedPreferences("lives", Context.MODE_PRIVATE);
        final int currentLives = lives.getInt("gameLives", 3);

        SharedPreferences getLocation = getSharedPreferences("getLocation", Context.MODE_PRIVATE);
        String currentLocation = getLocation.getString("location", "Kunal");
        Log.d("QRScanner_Result", "Result"+currentLocation);

        final String correctLocation = myQuestionArray[questionCount-3].getLocation();
        final String correctAnswer = myQuestionArray[questionCount-3].getAnswer();


        if (correctLocation.equals(currentLocation)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(StartGame.this);
            builder.setTitle("Enter your answer");
            final EditText enterAnswer = new EditText(this);
            enterAnswer.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(enterAnswer);
            builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    answer = enterAnswer.getText().toString();
                    Log.d("Code Kriegers","Answer: "+answer+" Correct Answer: "+correctAnswer);
                    if(answer.equals(correctAnswer)) {
                        Log.d("CheckCode", "Code was same!");
                        SharedPreferences.Editor myEditor2 = listRegeneration.edit();
                        myEditor2.putInt("LastElement", questionCount + 3);
                        myEditor2.apply();
                        startActivity(new Intent(StartGame.this,StartGame.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    }else {
                        Log.d("CheckCode", "Code was different. Your currentLocation: " + correctLocation);
                        reduceLives(currentLives);
                    }
                }
            });

            AlertDialog alert = builder.create();
            alert.show();


        } else {
            Log.d("CheckCode", "Code was different. Your currentLocation: "+correctLocation);
            reduceLives(currentLives);

        }

    }

    public static StartGame getInstance(){
        return instance;
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("Code Kriegers","onResume called!");
        /*SharedPreferences cameraOPenedOnce = getSharedPreferences("cameraOpened",MODE_PRIVATE);
        SharedPreferences.Editor openEditor = cameraOPenedOnce.edit();
        openEditor.putBoolean("openedCamera",false);
        openEditor.apply();*/
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d("Code Kriegers","onPause called!");
        SharedPreferences cameraOPenedOnce = getSharedPreferences("cameraOpened",MODE_PRIVATE);
        SharedPreferences.Editor openEditor = cameraOPenedOnce.edit();
        openEditor.putBoolean("openedCamera",false);
        openEditor.apply();

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putLong("start", startTime);
        editor.putLong("millis", millis);
        editor.putInt("second", seconds);
        editor.putInt("minute", minutes);
        editor.putInt("hour", hours);
        editor.apply();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("Code Kriegers","onDestroy called!");
        /*SharedPreferences cameraOPenedOnce = getSharedPreferences("cameraOpened",MODE_PRIVATE);
        SharedPreferences.Editor openEditor = cameraOPenedOnce.edit();
        openEditor.putBoolean("openedCamera",false);
        openEditor.apply();*/
    }

}

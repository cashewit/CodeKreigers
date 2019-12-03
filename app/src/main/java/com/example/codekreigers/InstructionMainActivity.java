package com.example.codekreigers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class InstructionMainActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;

    private InstructionSliderAdapter mInstructionSliderAdapter;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction_activity_main);

        mSlideViewPager = findViewById(R.id.slideViewPager);
        mDotLayout = findViewById(R.id.dotsLayout);

        mButton = findViewById(R.id.menuButton);

        mInstructionSliderAdapter =new InstructionSliderAdapter(this);
        mSlideViewPager.setAdapter(mInstructionSliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences loginPref = getSharedPreferences("loginPref",MODE_PRIVATE);
                if(loginPref.getBoolean("myLoginBool",true)){
                    SharedPreferences.Editor myEditor = loginPref.edit();
                    myEditor.putBoolean("myLoginBool",false);
                    myEditor.apply();
                    Intent myIntent = new Intent(InstructionMainActivity.this,StartGame.class);
                    startActivity(myIntent);
                    finish();
                }
                else
                    finish();
            }
        });
    }

    public void addDotsIndicator(int position ){
        mDots=new TextView[3];
        mButton.setVisibility(View.INVISIBLE);
        mDotLayout.removeAllViews();
        for(int i=0 ; i<mDots.length ; i++){
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorWhite));
            mDotLayout.addView(mDots[i]);
        }
        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            if(position == 2)
                mButton.setVisibility(View.VISIBLE);
        }
    }
    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
         addDotsIndicator(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}

package com.example.codekreigers;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

public class InstructionSliderAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;

    public InstructionSliderAdapter(Context context){

        this.mContext=context;
    }
    //Arrays
    public int[] slide_images={
            R.drawable.paanda,
            R.drawable.pandaa,
            R.drawable.pandaaa
    };

    public int[] slide_descs={R.string.slide1, R.string.slide2, R.string.slide3};

    @Override
    public int getCount(){
        return slide_images.length;
    }

    @Override
    public boolean isViewFromObject(View view,Object o){
        return view==(ConstraintLayout)o;
    }

    @Override
    public Object instantiateItem(ViewGroup container,int position){
        mLayoutInflater=(LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view=mLayoutInflater.inflate(R.layout.instruction_slide_layout,container,false);

        ImageView slideImageView = view.findViewById(R.id.slideImage);

        TextView slideDescription = view.findViewById(R.id.slide_descs);

        slideImageView.setImageResource(slide_images[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }
    @Override
    public void destroyItem(ViewGroup container,int position,Object object){
     container.removeView((ConstraintLayout)object);

    }
}

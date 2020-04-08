package com.example.home.ui.home;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import androidx.appcompat.widget.AppCompatRadioButton;


public class myRadioButton extends AppCompatRadioButton {
    private OnCheckedChangeListener onCheckedChangeListener;
    public myRadioButton(Context context) {
        super(context);
    }

    public myRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public myRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //this is called when the view is attached
        setOwnOnCheckedChangeListener();
        setButtonDrawable(null);//lets remove default drawable to create our own

    }
    public void setOwnOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener){
        this.onCheckedChangeListener=onCheckedChangeListener;
    }
    private void setOwnOnCheckedChangeListener(){
        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //this is set when we have set our listener .Let's create a method for it
                if(onCheckedChangeListener !=null) onCheckedChangeListener.onCheckedChanged(buttonView,isChecked);
                {}
            }
        });
    }
}

package com.example.swathi.gameboggle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by dohnj on 3/3/2017.
 */

public class GameLayout extends RelativeLayout {
    public GameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
        return true;
    }
}

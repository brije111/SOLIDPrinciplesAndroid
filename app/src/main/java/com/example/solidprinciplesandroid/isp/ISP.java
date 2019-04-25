package com.example.solidprinciplesandroid.isp;

import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by 51778499 on 25,April,2019
 * Hcl Technologies,
 * India.
 */
public class ISP {
    public interface OnClickListener {
        void onClick(View v);
        void onLongClick(View v);
        void onTouch(View v, MotionEvent event);
    }
    /*
    As you can see, this interface contains three different methods,
    assuming that we wanna get click from a button.
     */

    // Violation of Interface segregation principle
    //Button valid= (Button)findViewById(R.id.valid);
    //code for instantiation...
    //then
    /*valid.setOnClickListener(new View.OnClickListener {
        public void onClick(View v) {
            // TODO: do some stuff...

        }

        public void onLongClick(View v) {
            // we don't need to it
        }

        public void onTouch(View v, MotionEvent event) {
            // we don't need to it
        }
    });*/

    /*
    The interface is too fat because it’s forcing to implement all the methods, even if it doesnt’t not need them. Let’s trying to fix them using ISP:
     */

    // Fix of Interface Segregation principle


}
 interface OnClickListener {
    void onClick(View v);
}
 interface OnLongClickListener {
    void onLongClick(View v);
}
 interface OnTouchListener {
    void onTouch(View v, MotionEvent event);
}



/*
Now we can use the interface without implement some messy methods
 */


        /*interface ViewPager.OnPageChangeListener{
            void onPageScrollStateChanged();
            void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
            void onPageSelected(int position);
        }*/

        interface TextWatcher {
            void beforeTextChanged(CharSequence s, int start, int count, int after);
            void onTextChanged(CharSequence s, int start, int before, int count);
            void afterTextChanged(Editable s);
        }

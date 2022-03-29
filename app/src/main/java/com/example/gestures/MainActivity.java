package com.example.gestures;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;

import android.app.DatePickerDialog;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements
        GestureOverlayView.OnGesturePerformedListener, GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private GestureLibrary mGestureLib;
    TextView text;
    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!mGestureLib.load()) {
            finish();
        }


        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(this);

        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this,this);
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);




        SwitchMaterial switchMode = findViewById(R.id.switch1);
        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    gestures.setVisibility(View.VISIBLE);
                    text.setText("Draw something...");
                } else {
                    gestures.setVisibility(View.GONE);
                    text.setText("Swipe somehow...");
                }
            }
        });



        text = findViewById(R.id.text);
        refreshActivity();
    }

    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = mGestureLib.recognize(gesture);
        if (predictions.size() > 0) {
            Prediction prediction = predictions.get(0);
            if (prediction.score > 1.0) {
                text.setText("Распознано: " + prediction.name);
            } else {
                text.setText("Нераспознанный жест");
            }
        }else {
            text.setText("Нераспознанный жест");
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    private void refreshActivity() {



    }


    @Override
    public boolean onDown(MotionEvent event) {
        text.setText ("onDown");
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        text.setText("onFling");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        text.setText("onLongPress");
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {
        text.setText("onScroll");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        text.setText("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        text.setText("onSingleTapUp");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        text.setText("onDoubleTap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        text.setText("onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        text.setText("onSingleTapConfirmed");
        return true;
    }
}
package com.example.mytools;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.nfc.Tag;
import android.os.Handler;
import android.os.PersistableBundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Placeholder;
import androidx.appcompat.app.AppCompatActivity;
import maes.tech.intentanim.CustomIntent;

import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

private Placeholder placeholder;
private ConstraintLayout layout;
Handler mHandler;
byte i;
Intent intent;
AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        placeholder = findViewById(R.id.placeholder);
        layout = findViewById(R.id.layout);
        mHandler = new Handler();

        Log.d("UI", "onCreate");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
       if(hasFocus)
                  animationDrawable = (AnimationDrawable) layout.getBackground();
                  animationDrawable.setEnterFadeDuration(1000);
                  animationDrawable.setExitFadeDuration(5000);
                  animationDrawable.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("UI", "onStart");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("UI", "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("UI", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("UI", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("UI", "onDestroy");
    }

    public void swapView(View v){
        TransitionManager.beginDelayedTransition(layout);
        placeholder.setContentId(v.getId());

        switch(v.getId()){
            case R.id.calculator:
                i = 1;
                mHandler.postDelayed(run, 300);
                break;
            case R.id.compass:
                i = 2;
                mHandler.postDelayed(run, 300);
                break;
            case R.id.list:
                i = 3;
                mHandler.postDelayed(run, 300);
                break;
        }
    }

    private Runnable run = new Runnable() {
        @Override
        public void run() {
            switch(i){
                case 1:
                    intent = new Intent(MainActivity.this, CalculatorActivity.class);
                    startActivity(intent);
                    CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");
                    break;

                case 2:
                    intent = new Intent(MainActivity.this, SensorActivity.class);
                    startActivity(intent);
                    CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");
                    break;

                case 3:
                    intent = new Intent(MainActivity.this, ListActivity.class);
                    startActivity(intent);
                    CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");
                    break;
        }
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}

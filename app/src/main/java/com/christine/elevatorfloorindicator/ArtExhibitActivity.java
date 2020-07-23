package com.christine.elevatorfloorindicator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class ArtExhibitActivity extends AppCompatActivity {

    TextView inputCodeTextView;

    MediaPlayer correctEntry;

    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_exhibit);
        this.inputCodeTextView = findViewById(R.id.tv_input_code);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Intent intent = getIntent();
        b = intent.getExtras();

        correctEntry= MediaPlayer.create(this,R.raw.success_sound);
    }

    public void onClickNumberButton(View view) {
        String currentText = inputCodeTextView.getText().toString();
        //Will only concatenate the next number if less than 5 numbers have been entered
        if (currentText.length() < 5) {
            TextView textView = (TextView) view;
            String number = textView.getText().toString();
            currentText = currentText + number;
            inputCodeTextView.setText(currentText);
        } else {
            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(50); //You can manage the time of the blink with this parameter
            anim.setStartOffset(20);
            anim.setRepeatCount(2);
            inputCodeTextView.startAnimation(anim);
        }
    }



    public void onClickEnter(View view) {
        String number = inputCodeTextView.getText().toString();
        if (Objects.equals(number, "34784")) {//if correct code is entered by player, Terminal Activity opens
            inputCodeTextView.setText("");
            correctEntry.start();
            Intent myIntent = new Intent(ArtExhibitActivity.this, TerminalActivity.class);
            myIntent.putExtras(b);
            ArtExhibitActivity.this.startActivity(myIntent);
        }
        else if(Objects.equals(number, "92606")){
            //return to bluetooth selection screen if staff code is entered
            inputCodeTextView.setText("");
            Intent i = new Intent(ArtExhibitActivity.this, MainActivity.class);
            this.startActivity(i);
        }
        else {
            inputCodeTextView.setTextColor(Color.parseColor("#ff0000"));
            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(50); //You can manage the time of the blink with this parameter
            anim.setStartOffset(20);
            anim.setRepeatCount(5);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    inputCodeTextView.setText("");
                    inputCodeTextView.setTextColor(Color.parseColor("#d7801d"));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            inputCodeTextView.startAnimation(anim);

        }
    }

    public void onClickClear(View view) {
        this.inputCodeTextView.setText("");
    }
}

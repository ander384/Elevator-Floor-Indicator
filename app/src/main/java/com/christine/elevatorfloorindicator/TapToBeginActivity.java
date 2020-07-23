package com.christine.elevatorfloorindicator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TapToBeginActivity extends AppCompatActivity {

    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_to_begin);

        Intent intent = getIntent();
        b = intent.getExtras();
    }

    public void OnClickScreen(View view) {
        Intent i = new Intent(TapToBeginActivity.this, ArtExhibitActivity.class);
        i.putExtras(b);
        this.startActivity(i);
    }
}

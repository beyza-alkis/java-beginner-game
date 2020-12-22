package com.example.presscollect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView pointScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pointScreen = findViewById(R.id.textViewPointScreen);

        Intent intent = getIntent();
        int point = intent.getIntExtra("lastPoint", -1);
        if (point != -1) {
            pointScreen.setText("Point from the previous game: " + point );
        }
    }
    public void startGame(View view) {
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);

    }
}
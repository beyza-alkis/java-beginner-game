package com.example.presscollect;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView twTime;
    TextView twPoint;
    int point;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] picture;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        twTime = findViewById(R.id.twTime);
        twPoint = findViewById(R.id.twPoint);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        picture = new ImageView[]{ imageView,
                imageView2,
                imageView3,
                imageView4,
                imageView5,
                imageView6,
                imageView7,
                imageView8,
                imageView9,
        };
        security();
        showRandomPicture();
        point = 0;
        new CountDownTimer(10000 , 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                twTime.setText("Remaining Time: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                twTime.setText("Time's Up!");
                handler.removeCallbacks(runnable);
                security();

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("Leave Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        intent.putExtra("lastPoint", point);
                        startActivity(intent);
                    }
                });
                alert.show();


            }
        }.start();
    }

    public void pointUp (View view) {
        point+=5;
        twPoint.setText("Point: " + point);

    }
    public void pointUpDarwin (View view) {
        point+=10;
        twPoint.setText("Point: " + point);

    }
    private  void security() {
        for (ImageView view : picture) {
            view.setVisibility(View.INVISIBLE);
        }

    }
    private void showRandomPicture() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                security();
                Random random = new Random();
                int i = random.nextInt(9);
                picture[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 500);

            }
        };
        handler.post(runnable);
    }
}
package com.beyza.catchgame;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView score,time;
    ImageView imageView;
    Handler handler;
    Runnable runnable;
    int s,randomX,randomY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score =findViewById(R.id.score);
        time = findViewById(R.id.time);
        imageView=findViewById(R.id.imageView);
        s=0;


    }

    @Override
    protected void onStart() {
        super.onStart();
        s=0;
        score.setText("Score:"+s);
        timer();
        generateNumber();


    }

    public void timer (){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
                time.setText("Time: " + l/1000);
            }

            @Override
            public void onFinish() {
                time.setText("Time is up!!!");
                handler.removeCallbacks(runnable);
                imageView.setVisibility(View.INVISIBLE);
                alert.setTitle("Restart");
                alert.setMessage("Do you want to start a new game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        imageView.setVisibility(View.VISIBLE);
                        onStart();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"GAME OVER",Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
            }
        }.start();

    }
    public void scoreUp(View view){
            s++;
            score.setText("Score:" + s);
        }
    public void generateNumber(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                randomX = new Random().nextInt(1000 - 0) + 0;
                randomY = new Random().nextInt(1000 - 0) + 0;
                handler.postDelayed(this, 600);
                imageView.setX(randomX);
                imageView.setY(randomY);
               // textView2.setText(Integer.toString(score));
            }
        };
        handler.post(runnable);

    }

            }






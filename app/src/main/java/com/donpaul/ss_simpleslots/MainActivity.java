package com.donpaul.ss_simpleslots;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    private TextView msg;
    private ImageView img1,img2,img3;
    private Wheel wheel1,wheel2,wheel3;
    private Button btn;
    private boolean isStarted;

    public static long randomLong(long lower, long upper){
        return lower + (long)(RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        btn = (Button) findViewById(R.id.btn);
        msg = (TextView) findViewById(R.id.msg);
        btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isStarted) {
                wheel1.stopWheel();
                wheel2.stopWheel();
                wheel3.stopWheel();
                if (wheel1.currentIndex == wheel2.currentIndex && wheel1.currentIndex == wheel3.currentIndex){
                    msg.setText("You Win! Congrats!");
                }
                else
                    if(wheel1.currentIndex == wheel2.currentIndex || wheel2.currentIndex == wheel3.currentIndex || wheel1.currentIndex == wheel3.currentIndex){
                        msg.setText("You almost got it!");
                    }
                    else{
                        msg.setText("Try again!");
                    }
                btn.setText("Roll");
                isStarted = false;
            }
            else{
                wheel1 = new Wheel(new Wheel.WheelListener() {
                    @Override
                    public void newImage(final int img) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                img1.setImageResource(img);
                            }
                        });
                    }
                }, 200, randomLong(0,200));
                wheel1.start();

                wheel2 = new Wheel(new Wheel.WheelListener() {
                    @Override
                    public void newImage(final int img) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                img2.setImageResource(img);
                            }
                        });
                    }
                }, 200, randomLong(150,400));
                wheel2.start();


                wheel3 = new Wheel(new Wheel.WheelListener() {
                    @Override
                    public void newImage(final int img) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                img3.setImageResource(img);
                            }
                        });
                    }
                }, 200, randomLong(200,450));
                wheel3.start();

                btn.setText("Stop");
                msg.setText("");
                isStarted = true;
            }
        }
        });
    }
}

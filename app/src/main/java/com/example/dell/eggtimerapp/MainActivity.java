package com.example.dell.eggtimerapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.dell.eggtimerapp.R.id.seekBar;
import static com.example.dell.eggtimerapp.R.id.textView;

public class MainActivity extends AppCompatActivity {
    public SeekBar seekBar;
    public TextView textView;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.textView);

        seekBar.setMax(600);//in sec
        seekBar.setProgress(30);//in sec
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setSeekBar(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void setTimerMethod(View view) {
        Log.d("button","Button tapped");
        //
        new CountDownTimer(seekBar.getProgress()*1000,1000){//in millisec.

            @Override
            public void onTick(long millisUntilFinished) {
                setSeekBar((int)millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {

                mp = MediaPlayer.create(getApplicationContext(),R.raw.song);
                mp.start();
                textView.setText("0:00");

            }
        }.start();
    }
    public void setSeekBar(int time){
        int min = (int) time/60;
        int sec =  time - (min*60);

        String m =String.valueOf(min);
        String s= String.valueOf(sec);

        if(Integer.parseInt(s)==0)
        {
            s="00";
        }
        else if(Integer.parseInt(s)<=9 && Integer.parseInt(s)>0)
        {
            s="0"+s;
        }
        textView.setText(m+ ":"+s);
    }
}

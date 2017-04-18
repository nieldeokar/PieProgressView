package com.niel.circularprogresscustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

public class BgActivity extends AppCompatActivity {

  BgProgressView bgProgressView;
  SeekBar mSeekBar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bg);


    bgProgressView = (BgProgressView) findViewById(R.id.progressPie);

    // SeekBar
    mSeekBar = (SeekBar) findViewById(R.id.seekBar);
    mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        bgProgressView.setProgress(i);
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
  }
  }


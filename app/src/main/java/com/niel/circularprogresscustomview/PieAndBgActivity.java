package com.niel.circularprogresscustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

public class PieAndBgActivity extends AppCompatActivity {

  private PieAndBGprogressView mPieAndBGprogress;
  private SeekBar mSeekBar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pie_and_bg);


    mPieAndBGprogress = (PieAndBGprogressView) findViewById(R.id.pieAndBgProgress);

    // SeekBar
    mSeekBar = (SeekBar) findViewById(R.id.seekBar);
    mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        mPieAndBGprogress.setProgress(i);
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

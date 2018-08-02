package com.niel.circularprogresscustomview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar

class ProgressPieActivity : AppCompatActivity() {


    private var mSeekBar: SeekBar? = null

    private var mProgressPieView: PieProgressView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_pie)


        mProgressPieView = findViewById(R.id.progressPie)

        // SeekBar
        mSeekBar = findViewById(R.id.seekBar)
        mSeekBar!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                mProgressPieView!!.setProgress(i)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
    }
}

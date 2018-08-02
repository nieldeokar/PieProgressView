package com.niel.circularprogresscustomview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar

class PieAndBgActivity : AppCompatActivity() {

    private var mPieAndBGprogress: PieAndBGprogressView? = null
    private var mSeekBar: SeekBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_and_bg)


        mPieAndBGprogress = findViewById(R.id.pieAndBgProgress)

        mSeekBar = findViewById(R.id.seekBar)
        mSeekBar!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                mPieAndBGprogress!!.setProgress(i)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
    }
}

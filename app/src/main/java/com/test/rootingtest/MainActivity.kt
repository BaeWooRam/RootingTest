package com.test.rootingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(RootingUtil.isRooted(packageManager))
            Log.e("isRooted", "true")
        else
            Log.e("isRooted", "false")
    }
}

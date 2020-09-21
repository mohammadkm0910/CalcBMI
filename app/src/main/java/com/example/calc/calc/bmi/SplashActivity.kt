package com.example.calc.calc.bmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val moveTop:Animation = AnimationUtils.loadAnimation(this,R.anim.move_top);
        val moveBottom:Animation = AnimationUtils.loadAnimation(this,R.anim.move_bottom);
        nameApp.startAnimation(moveTop)
        showIconApp.startAnimation(moveBottom)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },2000)
    }
}
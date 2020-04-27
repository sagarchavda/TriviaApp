package com.appscrip.triviaapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.appscrip.triviaapp.R
import com.appscrip.triviaapp.ui.main.MainActivity

/**
 * This splash screen is used for startup screen when every time app open.
 */
class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashTimer()

    }

    /**
     * It's start timer till defined time and then perform written code
     */
    private fun splashTimer() {
        Handler().postDelayed({
                redirectToMain()
        }, SPLASH_TIME_OUT.toLong())
    }

    /**
     * It's redirect to MainActivity with new task so, user can't come back into splash screen
     */
    private fun redirectToMain() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}

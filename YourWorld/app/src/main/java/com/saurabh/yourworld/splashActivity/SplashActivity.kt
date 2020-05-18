package com.saurabh.yourworld.splashActivity

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.FrameMetrics.ANIMATION_DURATION
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.saurabh.yourworld.MainActivity
import com.saurabh.yourworld.R
import com.saurabh.yourworld.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 1000
    private var mDelayHandler: Handler? = null
    private var progressBarStatus = 0
    var dummy:Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDelayHandler = Handler()


//        Handler().postDelayed({
//            startActivity(Intent(this,MainActivity::class.java))
//            finish()
//        },SPLASH_TIME_OUT)

//        startAnimation()
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_TIME_OUT)
        startAnimation()
    }

    private val mRunnable: Runnable = Runnable {

        Thread(Runnable {
            while (progressBarStatus < 100) {
                // performing some dummy operation
                try {
                    dummy = dummy+25
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                // tracking progress
                progressBarStatus = dummy

                // Updating the progress bar
                splash_screen_progress_bar.progress = progressBarStatus
            }

            //splash_screen_progress_bar.setProgress(10)

        }).start()
    }

    private fun startAnimation() {
        // Intro animation configuration.
        val valueAnimator = ValueAnimator.ofFloat(0f, 2f)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            textView.scaleX = value
            textView.scaleY = value

            textView1.scaleX = value
            textView1.scaleY = value

            progressBar.scaleX = value
            progressBar.scaleY = value
        }
        valueAnimator.interpolator = BounceInterpolator()
        valueAnimator.duration = SPLASH_TIME_OUT

        // Set animator listener.
        val intent = Intent(this, LoginActivity::class.java)
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                // Navigate to main activity on navigation end.
                startActivity(intent)
                finish()
            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationStart(p0: Animator?) {}

        })

        // Start animation.
        valueAnimator.start()
    }
}
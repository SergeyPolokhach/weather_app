package com.polohach.weather.ui.screens.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import com.polohach.weather.R
import com.polohach.weather.ui.base.BaseActivity
import com.polohach.weather.ui.screens.main.MainActivity

class SplashActivity : BaseActivity<SplashVM>() {

    override val viewModelClass = SplashVM::class.java

    override val containerId = R.id.flContainer

    override val layoutId = R.layout.activity_splash

    private val timerObserver = Observer<Long> {
        openAuthScreen()
    }

    override fun hasProgressBar() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.startTimer()
    }

    override fun observeLiveData(viewModel: SplashVM) {
        viewModel.apply {
            timerLD.observe(this@SplashActivity, timerObserver)
        }
    }

    private fun openAuthScreen() {
        MainActivity.start(this)
        finish()
    }
}

package com.polohach.weather.ui.screens.splash

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.polohach.weather.ui.base.BaseVM
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class SplashVM(app: Application) : BaseVM(app) {

    companion object {
        private const val DELAY = 2_000L
    }

    val timerLD = MutableLiveData<Long>()

    fun startTimer() {
        Single.timer(DELAY, TimeUnit.MILLISECONDS)
                .doAsync(timerLD, isShowProgress = false)
    }
}

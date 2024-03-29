package com.polohach.weather.ui.base

import com.cleveroad.bootstrap.kotlin_core.ui.BaseLifecycleActivity
import com.cleveroad.bootstrap.kotlin_core.ui.BaseLifecycleViewModel
import com.cleveroad.bootstrap.kotlin_core.ui.BlockedCallback
import com.google.android.material.snackbar.Snackbar
import com.polohach.weather.R

abstract class BaseActivity<T : BaseLifecycleViewModel> : BaseLifecycleActivity<T>(),
        BlockedCallback {

    override fun getProgressBarId() = R.id.progressView

    override fun getSnackBarDuration() = Snackbar.LENGTH_SHORT

    override fun onBlocked() = Unit
}

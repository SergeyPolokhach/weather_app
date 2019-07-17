package com.polohach.weather.ui.base

import com.cleveroad.bootstrap.kotlin_core.ui.BaseLifecycleFragment
import com.cleveroad.bootstrap.kotlin_core.ui.BaseLifecycleViewModel
import com.cleveroad.bootstrap.kotlin_core.ui.NO_ID
import com.polohach.weather.BuildConfig
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<T : BaseLifecycleViewModel> : BaseLifecycleFragment<T>() {

    override var endpoint = ""

    override var versionName = ""

    private val rxPermission by lazy { RxPermissions(this) }
    private val permissionDisposable = CompositeDisposable()


    override fun getVersionsLayoutId() = NO_ID

    override fun getEndPointTextViewId() = NO_ID

    override fun getVersionsTextViewId() = NO_ID

    override fun isDebug() = BuildConfig.DEBUG

    /**
     * Display a warning when going to action back
     */
    override fun showBlockBackAlert() = Unit

    override fun onDestroy() {
        permissionDisposable.apply {
            dispose()
            clear()
        }
        super.onDestroy()
    }

    @SuppressWarnings("SpreadOperator")
    fun requestPermission(vararg permission: String,
                          isDeniedCallback: () -> Unit = { },
                          isGrantedCallback: () -> Unit) {
        rxPermission.request(*permission)?.subscribe { granted ->
            if (granted) isGrantedCallback() else isDeniedCallback()
        }?.let { permissionDisposable.add(it) }
    }
}
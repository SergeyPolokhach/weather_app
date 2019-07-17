package com.polohach.weather.ui.base

import com.cleveroad.bootstrap.kotlin_core.ui.BaseLifecycleViewModel
import com.cleveroad.bootstrap.kotlin_core.ui.NO_ID
import com.cleveroad.bootstrap.kotlin_core.ui.adapter.BaseListFragment
import com.polohach.weather.BuildConfig

abstract class SimpleListFragment<ViewModel : BaseLifecycleViewModel, M : Any> : BaseListFragment<ViewModel, M>() {

    override var endpoint = ""

    override var versionName = ""

    override fun isDebug() = BuildConfig.DEBUG

    override fun showBlockBackAlert() = Unit

    override fun getEndPointTextViewId() = NO_ID

    override fun getVersionsLayoutId() = NO_ID

    override fun getVersionsTextViewId() = NO_ID

}
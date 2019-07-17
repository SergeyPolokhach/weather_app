package com.polohach.weather.ui.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

/**
 * [AppCompatAutoCompleteTextView] extension,
 * which supports delay before request performing.
 */
class DelayAutoCompleteTextView : AppCompatAutoCompleteTextView {

    companion object {
        private const val AUTO_COMPLETE_DELAY_MS = 500L
    }

    private val autoCompleteSubject = PublishSubject.create<CharSequence>()

    private val disposables = CompositeDisposable()

    private var currentKeyCode: Int = 0

    constructor(ctx: Context) : super(ctx) {
        initAutoCompleteSubject()
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        initAutoCompleteSubject()
    }

    override fun performFiltering(text: CharSequence?, keyCode: Int) {
        text?.let {
            currentKeyCode = keyCode
            autoCompleteSubject.onNext(it.toString())
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        disposables.dispose()
    }

    private fun initAutoCompleteSubject() {
        disposables.add(autoCompleteSubject.debounce(AUTO_COMPLETE_DELAY_MS, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribe { super.performFiltering(it, currentKeyCode) })
    }
}
package com.dobrowins.allsouls.allsoulsweather20.presenters

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Artem Dobrovinskiy
 */
abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    protected fun Disposable.autoDispose() = { compositeDisposable.add(this) }

}
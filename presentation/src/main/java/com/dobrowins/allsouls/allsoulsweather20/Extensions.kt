package com.dobrowins.allsouls.allsoulsweather20

import android.content.Context
import android.widget.Toast
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import timber.log.Timber

/**
 * @author Artem Dobrovinskiy
 */
// ANDROID EXTENSIONS

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.toast(resInt: Int) {
    val message = this.getString(resInt)
    return Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

// RX EXTENSIONS

fun <T> T.toObservable(): Observable<T> = Observable.just(this)

fun logErrorMessage(t: Throwable) = Timber.e(t.message)

fun Completable.onErrorLogAndComplete(): Completable =
    this
        .doOnError { t -> timber.log.Timber.e(t.message) }
        .onErrorComplete()

fun Completable.applySameScheduler(scheduler: Scheduler): Completable =
    this
        .observeOn(scheduler)
        .subscribeOn(scheduler)

fun <T> Observable<T>.applySameScheduler(scheduler: Scheduler): Observable<T> =
    this
        .observeOn(scheduler)
        .subscribeOn(scheduler)

fun <T> Single<T>.applySameScheduler(scheduler: Scheduler): Single<T> =
    this
        .observeOn(scheduler)
        .subscribeOn(scheduler)

fun <T> TestObserver<T>.trigger(body: () -> Unit): TestObserver<T> {
    body.invoke()
    return this
}



package com.dobrowins.allsouls.allsoulsweather20

import com.dobrowins.allsouls.domain.SchedulersFactory
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Artem Dobrovinskiy
 */
class SchedulersFactoryImpl : SchedulersFactory {

    override fun io(): Scheduler = Schedulers.io()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun computation(): Scheduler = Schedulers.computation()

    override fun single(): Scheduler = Schedulers.single()

}
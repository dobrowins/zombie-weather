package com.dobrowins.allsouls.domain

import io.reactivex.Scheduler

/**
 * @author Artem Dobrovinskiy
 */
interface SchedulersFactory {
    fun io(): Scheduler
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun single(): Scheduler
}
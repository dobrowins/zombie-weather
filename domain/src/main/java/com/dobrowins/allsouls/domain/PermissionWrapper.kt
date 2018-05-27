package com.dobrowins.allsouls.domain

import io.reactivex.Single
import java.lang.ref.WeakReference

/**
 * @author Artem Dobrovinskiy
 * T - activity type
 * P - permission
 */
interface PermissionWrapper<T, P> {
    fun create(weakReference: WeakReference<T>)
    fun isPermissionGranted(permission: String): Single<Boolean>
    fun request(permission: String): Single<P>
    fun clearReferent(): Unit?
}
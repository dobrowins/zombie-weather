package com.dobrowins.allsouls.allsoulsweather20.utils

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import com.dobrowins.allsouls.domain.PermissionWrapper
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Single
import io.reactivex.Single.just
import java.lang.ref.WeakReference

/**
 * @author Artem Dobrovinskiy
 */
class PermissionWrapperImpl : PermissionWrapper<Activity, Permission> {

    private var weakReference: WeakReference<Activity>? = null
    private lateinit var activity: AppCompatActivity
    private lateinit var rxPermissions: RxPermissions

    override fun create(weakReference: WeakReference<Activity>) {
        this.weakReference = weakReference
        activity = weakReference.get() as AppCompatActivity
        rxPermissions = RxPermissions(activity)
    }

    override fun isPermissionGranted(permission: String): Single<Boolean> =
        just(rxPermissions.isGranted(permission))

    override fun request(permission: String): Single<Permission> =
        rxPermissions.requestEach(permission).firstOrError()

    override fun clearReferent(): Unit? = weakReference?.clear()

}
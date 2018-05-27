package com.dobrowins.allsouls.allsoulsweather20.di.modules

import android.app.Activity
import com.dobrowins.allsouls.allsoulsweather20.utils.PermissionWrapperImpl
import com.dobrowins.allsouls.domain.PermissionWrapper
import com.tbruyelle.rxpermissions2.Permission
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Artem Dobrovinskiy
 */
@Module
class UtilsModule {

    @Provides
    @Singleton
    fun permissionWrapper(): PermissionWrapper<Activity, Permission> =
        PermissionWrapperImpl()

}
package com.dobrowins.allsouls.allsoulsweather20.di.modules

import com.dobrowins.allsouls.allsoulsweather20.SchedulersFactoryImpl
import com.dobrowins.allsouls.domain.SchedulersFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Artem Dobrovinskiy
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun schedulersFactory(): SchedulersFactory = SchedulersFactoryImpl()

}
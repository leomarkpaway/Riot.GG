package com.leomarkpaway.riotgg

import android.app.Application
import com.leomarkpaway.riotgg.di.networkModule
import com.leomarkpaway.riotgg.di.repositoryModule
import com.leomarkpaway.riotgg.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class RiotGGApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RiotGGApp)
            modules(networkModule, repositoryModule, useCaseModule)
        }
        setupTimberLogging()
    }

    private fun setupTimberLogging() {
        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                super.log(priority, "Timber_$tag", message, t)
            }

            override fun createStackElementTag(element: StackTraceElement): String {
                return String.format(
                    "%s:%s",
                    super.createStackElementTag(element),
                    element.methodName
                )
            }
        })
    }
}
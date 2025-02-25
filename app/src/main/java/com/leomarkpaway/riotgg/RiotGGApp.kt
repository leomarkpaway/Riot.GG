package com.leomarkpaway.riotgg

import android.app.Application
import timber.log.Timber

class RiotGGApp : Application() {

    override fun onCreate() {
        super.onCreate()
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
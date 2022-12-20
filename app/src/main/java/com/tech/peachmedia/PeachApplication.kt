package com.tech.peachmedia

import android.app.Application
import com.google.firebase.FirebaseApp
import com.tech.peachmedia.di.appModule
import javax.annotation.Nullable
import org.jetbrains.annotations.NotNull
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.error.KoinAppAlreadyStartedException
import timber.log.Timber

class PeachApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
        //initFirebase()
        initKoin()
    }

    private fun initFirebase() {
        FirebaseApp.initializeApp(this)
    }

    private fun initKoin() {
        try {
            startKoin {
                androidContext(this@PeachApplication)
                modules(
                    appModule
                )
            }
        } catch (error: KoinAppAlreadyStartedException) {
            Timber.e(error.localizedMessage)
        }
    }

    private fun initTimber() {
        Timber.plant(object : Timber.DebugTree() {
            @Nullable
            override fun createStackElementTag(@NotNull element: StackTraceElement): String? {
                return super.createStackElementTag(element) + ":" + element.lineNumber
            }
        })
    }
}

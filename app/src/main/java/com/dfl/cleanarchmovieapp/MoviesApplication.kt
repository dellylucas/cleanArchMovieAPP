package com.dfl.cleanarchmovieapp

import android.app.Application
import com.dfl.cleanarchmovieapp.di.components.AppComponent
import com.dfl.cleanarchmovieapp.di.components.DaggerAppComponent

open class MoviesApplication : Application() {

    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = initMoviesComponent()
    }

    open fun initMoviesComponent() = DaggerAppComponent.factory().create(this)
}
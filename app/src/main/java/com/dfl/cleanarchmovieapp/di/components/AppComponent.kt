package com.dfl.cleanarchmovieapp.di.components

import android.app.Application
import com.dfl.cleanarchmovieapp.di.modules.AppModule
import com.dfl.cleanarchmovieapp.di.modules.DataModule
import com.dfl.cleanarchmovieapp.di.modules.ListFragmentComponent
import com.dfl.cleanarchmovieapp.di.modules.ListFragmentModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        AppModule::class]
)
interface AppComponent {

    fun plus(module: ListFragmentModule): ListFragmentComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }
}
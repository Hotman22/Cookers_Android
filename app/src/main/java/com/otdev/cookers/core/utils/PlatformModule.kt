package com.otdev.cookers.core.utils

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
 * Module providing platform related components (android, Kotlin/JVM, etc.).
 */
@InstallIn(SingletonComponent::class)
@Module
object PlatformModule {

    @Provides
    @Singleton
    fun provideCoroutineContexts() = object : CoroutineContextProvider {
        override val ui: CoroutineContext
            get() = Dispatchers.Main
        override val io: CoroutineContext
            get() = Dispatchers.IO
        override val default: CoroutineContext
            get() = Dispatchers.Default
    }
}
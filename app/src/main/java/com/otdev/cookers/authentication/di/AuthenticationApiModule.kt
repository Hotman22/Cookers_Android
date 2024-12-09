package com.otdev.cookers.authentication.di

import com.otdev.cookers.authentication.data.datasource.remote.AuthenticationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthenticationApiModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthenticationApi =
        retrofit.create(AuthenticationApi::class.java)
}
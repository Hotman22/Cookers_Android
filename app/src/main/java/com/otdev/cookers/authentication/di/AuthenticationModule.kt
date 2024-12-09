package com.otdev.cookers.authentication.di

import com.otdev.cookers.authentication.data.datasource.remote.AuthenticationNetwork
import com.otdev.cookers.authentication.data.datasource.remote.AuthenticationNetworkImpl
import com.otdev.cookers.authentication.data.repository.AuthenticationRepositoryImpl
import com.otdev.cookers.authentication.domain.repository.AuthenticationRepository
import com.otdev.cookers.authentication.domain.usecase.LogInImpl
import com.otdev.cookers.authentication.domain.usecase.interfaces.LogIn
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AuthenticationModule {

    @Binds
    abstract fun provideAuthNetwork(
        authenticationNetwork: AuthenticationNetworkImpl
    ): AuthenticationNetwork

    @Binds
    abstract fun provideAuthRepository(
        authenticationRepositoryImpl: AuthenticationRepositoryImpl
    ): AuthenticationRepository

    @Binds
    abstract fun provideLogIn(
        logInImpl: LogInImpl
    ): LogIn
}
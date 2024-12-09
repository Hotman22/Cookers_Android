package com.otdev.cookers.authentication.presentation.viewmodel

import app.cash.turbine.test
import com.otdev.cookers.authentication.data.datasource.remote.AuthenticationApi
import com.otdev.cookers.authentication.data.datasource.remote.AuthenticationNetwork
import com.otdev.cookers.authentication.data.datasource.remote.AuthenticationNetworkImpl
import com.otdev.cookers.authentication.data.model.LoginRequest
import com.otdev.cookers.authentication.data.model.TokenDto
import com.otdev.cookers.authentication.data.repository.AuthenticationRepositoryImpl
import com.otdev.cookers.authentication.domain.repository.AuthenticationRepository
import com.otdev.cookers.authentication.domain.usecase.LogInImpl
import com.otdev.cookers.authentication.domain.usecase.interfaces.LogIn
import com.otdev.cookers.utils.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LogInViewModelTest {
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val authApi: AuthenticationApi = mockk()
    private val authNetwork: AuthenticationNetwork = AuthenticationNetworkImpl(authApi)
    private val authRepository: AuthenticationRepository = AuthenticationRepositoryImpl(authNetwork)
    private val logIn: LogIn = LogInImpl(authRepository, coroutinesTestRule.testDispatcherProvider)
    private val viewModel: LogInViewModel = LogInViewModel(logIn)

    @Test
    fun `GIVEN a user WHEN he enter a right email and password THEN is redirect to Home View`() =
        runTest {
            //GIVEN
            val email = "Patrick"
            val password = "yes"
            val loginRequest = LoginRequest(email, password)
            val tokenDto = TokenDto("userId", "token", "refreshToken")
            coEvery { authApi.logIn(loginRequest) } returns tokenDto

            //WHEN
            viewModel.sendIntent(LogInViewModel.UIAction.LogIn(email, password))

            //THEN
            viewModel.uiState.test {
                val result = awaitItem()

                coVerify { authApi.logIn(loginRequest) }
                assertFalse(result.isLoading)
            }
        }
}
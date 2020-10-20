package com.shahzar.enoclink.data

import com.shahzar.enoclink.data.local.UserPreferences
import com.shahzar.enoclink.data.mock.MockApiServiceImpl
import com.shahzar.enoclink.data.model.request.AvatarRequest
import com.shahzar.enoclink.data.model.response.UserResponse
import com.shahzar.enoclink.util.interceptor.SessionInterceptor
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserRepositoryTest {

    lateinit var userRepository: UserRepository

    @Mock
    lateinit var prefs: UserPreferences

    lateinit var interceptor: SessionInterceptor

    @Before
    fun init() {
        interceptor = SessionInterceptor()
        userRepository = UserRepository(MockApiServiceImpl(prefs), prefs, interceptor)
    }

    @Test
    fun userLogin_validCred_shouldBeTrue() {
        runBlocking {
            val res = userRepository.userLogin("test@mail.com", "Pass@123")
            Mockito.verify(prefs).setAccessToken(res.token)
            Mockito.verify(prefs).setUserId(res.userid)
            Mockito.verify(prefs).setUserEmail("test@mail.com")
            Mockito.verify(prefs).setUserPassword("Pass@123")
        }
    }

    @Test
    fun getUserDetails_valid_shouldBeTrue() {
        runBlocking {
            `when`(prefs.getUserEmail()).thenReturn("test@mail.com")
            val res = userRepository.getUserDetails("12345")
            assertEquals(res, UserResponse("test@mail.com", ""))
        }
    }

    @Test
    fun uploadUserAvatar_valid_shouldBeTrue() {
        runBlocking {
            val res = userRepository.uploadAvatar("12345", AvatarRequest(""))
            assert(res.avatarUrl.isNotEmpty())
        }
    }

}
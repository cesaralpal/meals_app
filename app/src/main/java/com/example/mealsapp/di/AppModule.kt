package com.example.mealsapp.di

import com.example.mealsapp.network.MealsApi
import com.example.mealsapp.repository.MealsRepository
import com.example.mealsapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    var httpClient = OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY))

    @Singleton
    @Provides
    fun provideQuestionRepository(api: MealsApi) = MealsRepository(api)

    @Singleton
    @Provides
    fun provideQuestionApi(): MealsApi {
        val headerAuthorizationInterceptor = Interceptor { chain ->
            var request = chain.request()
            request = request.newBuilder().build()
            chain.proceed(request)
        }
        httpClient.addInterceptor(headerAuthorizationInterceptor)

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(MealsApi::class.java)
    }

}
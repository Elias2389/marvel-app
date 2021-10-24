package com.ae.marvelappication.core.di

import com.ae.marvelappication.BuildConfig
import com.ae.marvelappication.data.interceptor.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    private const val TIME_TIMEOUT: Long = 30

    /**
     * Provide interceptor of http requests
     * @return HttpLoggingInterceptor
     */
    @Provides
    fun provideNetworkInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /**
     * Provide interceptor with APIS KEY and Hash
     * @return Interceptor
     */
    @Provides
    fun provideInterceptor(): Interceptor = ApiKeyInterceptor()

    /**
     * Provide Http client with behaviors
     * @return OkHttpClient
     */
    @Provides
    fun provideOkHttpClient(
        networkInterceptor: HttpLoggingInterceptor,
        interceptor: ApiKeyInterceptor,
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addNetworkInterceptor(networkInterceptor)
            .connectTimeout(TIME_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIME_TIMEOUT, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor(interceptor)
        return httpBuilder.build()
    }

    /**
     * Create interface with retrofit generic class
     * @return service to create
     */
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .build()

}
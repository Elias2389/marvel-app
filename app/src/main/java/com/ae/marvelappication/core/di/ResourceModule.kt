package com.ae.marvelappication.core.di

import android.content.Context
import com.ae.marvelappication.common.reponse.ResponseHandler
import com.ae.marvelappication.common.reponse.ResponseHandlerImpl
import com.ae.marvelappication.common.resource.ResourceProvider
import com.ae.marvelappication.common.resource.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ResourceModule {

    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return ResourceProviderImpl(context)
    }

    @Provides
    fun provideResponseHandler(resourceProvider: ResourceProvider): ResponseHandler {
        return ResponseHandlerImpl(resourceProvider)
    }
}
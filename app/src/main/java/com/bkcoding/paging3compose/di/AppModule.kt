package com.bkcoding.paging3compose.di

import com.bkcoding.paging3compose.network.ApiService
import com.bkcoding.paging3compose.repository.BeerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder().baseUrl("https://api.punkapi.com/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }

    @Provides
    fun providesBeerRepo(apiService: ApiService): BeerRepository {
        return BeerRepository(apiService)
    }

}
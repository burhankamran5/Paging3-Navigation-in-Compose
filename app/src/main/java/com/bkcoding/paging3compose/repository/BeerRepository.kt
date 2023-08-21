package com.bkcoding.paging3compose.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bkcoding.paging3compose.data.model.BeerDto
import com.bkcoding.paging3compose.network.ApiService
import com.bkcoding.paging3compose.paging.BeerPagingSource
import kotlinx.coroutines.flow.Flow

class BeerRepository(private val apiService: ApiService) {
    fun getBeerList(): Flow<PagingData<BeerDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 5,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                BeerPagingSource(apiService)
            }
        ).flow
    }
}
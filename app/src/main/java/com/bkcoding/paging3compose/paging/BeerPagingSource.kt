package com.bkcoding.paging3compose.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bkcoding.paging3compose.data.model.BeerDto
import com.bkcoding.paging3compose.network.ApiService
import retrofit2.HttpException
import java.io.IOException

class BeerPagingSource(private val apiService: ApiService) : PagingSource<Int, BeerDto>() {
    override fun getRefreshKey(state: PagingState<Int, BeerDto>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(1)?.prevKey?.plus(
                1
            ) ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeerDto> {
        val position = params.key ?: 1
        return try {

            val remoteData = apiService.getBeerList(
                position, params.loadSize
            )
            val nextKey = if (remoteData.size < params.loadSize) {
                null
            } else {
                position + 1
            }
            LoadResult.Page(
                remoteData,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
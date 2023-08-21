package com.bkcoding.paging3compose.viewmodel

import androidx.lifecycle.ViewModel
import com.bkcoding.paging3compose.repository.BeerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(private val repository: BeerRepository) :ViewModel() {
    val pagingData = repository.getBeerList()
}
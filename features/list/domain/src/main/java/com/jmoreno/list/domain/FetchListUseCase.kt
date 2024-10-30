package com.jmoreno.list.domain

import com.jmoreno.list.data.ListRepository
import kotlinx.coroutines.flow.Flow

class FetchListUseCase(private val listRepository: ListRepository) {

    suspend operator fun invoke(): Flow<List<String>> {
        return listRepository.fetchListItems()
    }
}
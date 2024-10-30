package com.jmoreno.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmoreno.list.domain.FetchListUseCase
import com.jmoreno.list.domain.FetchRewardsDomainModel
import com.jmoreno.list.domain.ItemDomainEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

class FetchListViewModel(private val fetchListUseCase: FetchListUseCase) : ViewModel() {
    suspend fun refresh() {
        refreshItems()
    }

    val viewState: MutableStateFlow<FetchListViewState> = MutableStateFlow(FetchListViewState())

    init {
        //  Fetch initial data
        //
        viewModelScope.launch {
            viewState.emit(viewState.value.copy(isLoading = true))
            yield()
            fetchListUseCase().map { it.toUIModel() }.onSuccess {
                viewState.emit(viewState.value.copy(isLoading = false, data = it))
            }.onFailure {
                viewState.emit(viewState.value.copy(isLoading = false, isError = true))
            }
        }

    }

    private suspend fun refreshItems(){
        viewModelScope.launch {
            viewState.emit(viewState.value.copy(isLoading = true))
            yield()
            fetchListUseCase().map { it.toUIModel() }.onSuccess {
                viewState.emit(viewState.value.copy(isLoading = false, data = it))
            }.onFailure {
                viewState.emit(viewState.value.copy(isLoading = false, isError = true))
            }
        }
    }
}

private fun List<ItemDomainEntity>.mapToUiModel(): List<Item> {
    return map {
        Item(id = it.id, name = it.name)
    }
}

private fun List<FetchRewardsDomainModel>.toUIModel(): List<FetchRewardsItemUI> {
    return map {
        FetchRewardsItemUI(it.groupId, it.data.mapToUiModel())
    }
}

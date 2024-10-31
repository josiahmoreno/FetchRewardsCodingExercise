package com.jmoreno.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmoreno.list.domain.FetchListUseCase
import com.jmoreno.list.domain.GroupDomainModel
import com.jmoreno.list.domain.ItemDomainModel
import com.jmoreno.list.ui.models.FetchRewardsGroupUI
import com.jmoreno.list.ui.models.FetchRewardsItemUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

class FetchListViewModel(private val fetchListUseCase: FetchListUseCase) : ViewModel() {
    val viewState: MutableStateFlow<FetchListViewState> = MutableStateFlow(FetchListViewState())

    init {
        //  Fetch initial data
        //
        refreshItems()
    }

    private fun refreshItems() {
        viewModelScope.launch {
            viewState.emit(viewState.value.copy(isLoading = true, isError = false))
            yield()
            fetchListUseCase().map { it.toUIModel() }.onSuccess {
                viewState.emit(viewState.value.copy(isLoading = false, data = it,  isError = false))
            }.onFailure {
                viewState.emit(viewState.value.copy(isLoading = false, isError = true))
            }
            yield()
        }
    }

    fun refresh() {
        refreshItems()
    }
}

private fun List<ItemDomainModel>.mapToUiModel(): List<FetchRewardsItemUI> {
    return map {
        FetchRewardsItemUI(id = it.id, name = it.name)
    }
}

private fun List<GroupDomainModel>.toUIModel(): List<FetchRewardsGroupUI> {
    return map {
        FetchRewardsGroupUI(it.groupId, it.data.mapToUiModel())
    }
}

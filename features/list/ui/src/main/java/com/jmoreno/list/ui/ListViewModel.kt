package com.jmoreno.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmoreno.list.domain.FetchListUseCase
import com.jmoreno.list.domain.FetchRewardsDomainModel
import com.jmoreno.list.domain.ItemDomainEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ListViewModel(private val fetchListUseCase: FetchListUseCase) : ViewModel() {
    val viewState: MutableStateFlow<ListViewState> = MutableStateFlow(ListViewState())

    init {
        //  Fetch initial data
        viewModelScope.launch {
            fetchListUseCase().map { it.toUIModel() }.onEach {
                viewState.emit(viewState.value.copy(data = it))
            }.launchIn(viewModelScope)
        }

    }
}

private fun List<ItemDomainEntity>.mapToUiModel(): List<Item> {
    return map {
        Item(id = it.id.toString(), name = it.name)
    }
}

private fun List<FetchRewardsDomainModel>.toUIModel(): List<FetchRewardsItemUI> {
    return map {
        FetchRewardsItemUI(it.groupId.toString(), it.data.mapToUiModel())
    }
}

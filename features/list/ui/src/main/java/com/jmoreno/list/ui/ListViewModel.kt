package com.jmoreno.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmoreno.list.domain.FetchListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ListViewModel(private val fetchListUseCase: FetchListUseCase): ViewModel() {
    val viewState: MutableStateFlow<ListViewState> = MutableStateFlow(ListViewState())

    init {
        //  Fetch initial data
        viewModelScope.launch {
            fetchListUseCase().onEach {
                viewState.emit(viewState.value.copy(data = it))
            }.launchIn(viewModelScope)
        }

    }
}
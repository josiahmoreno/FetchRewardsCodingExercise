package com.jmoreno.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmoreno.list.domain.EventsDomainModel
import com.jmoreno.list.domain.FetchEventsUseCase
import com.jmoreno.list.ui.models.DateFormatted
import com.jmoreno.list.ui.models.EventItemUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class EventsListViewModel(private val fetchEventsUseCase: FetchEventsUseCase) : ViewModel() {
    val viewState: MutableStateFlow<FetchListViewState> = MutableStateFlow(FetchListViewState())

    init {
        //  Fetch initial data
        //
        refreshItems()
    }

    private fun refreshItems() {
        viewModelScope.launch {
            viewState.emit(viewState.value.copy(isLoading = true, isError = false, data = listOf()))
            yield()
            fetchEventsUseCase().map { it.toUIModel() }.onSuccess {
                viewState.emit(viewState.value.copy(isLoading = false, data = it, isError = false))
            }.onFailure {
                viewState.emit(viewState.value.copy(isLoading = false, isError = true))
            }
            yield()
        }
    }

    fun refresh() {
        refreshItems()
    }

    fun onEventCLicked(eventItemUI: EventItemUI) {
        viewModelScope.launch {
           viewState.emit(viewState.value.copy(detail = eventItemUI))
        }

    }

    fun onEventCleared() {
        viewModelScope.launch {
            viewState.emit(viewState.value.copy(detail = null))
        }
    }
}

private fun List<EventsDomainModel>.toUIModel(): List<EventItemUI> {
    return map {
        EventItemUI(
            id = it.id,
            imgSrc = it.imgSrc,
            phone = it.phone,
            dateOfEventFormatted = formatDate(it.timeStamp),
            title = it.title,
            locationLine1 = it.locationline1,
            locationLine2 = it.locationline2,
            description = it.description
        )
    }
}

private fun formatDate(date: String): DateFormatted {
    val zonedDateTime = ZonedDateTime.parse(date)
    val deviceTimeZone = ZoneId.systemDefault()

    val localDateTime = zonedDateTime.withZoneSameInstant(deviceTimeZone)

    val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' h:mm a")
    val formattedDate = localDateTime.format(formatter)
    return DateFormatted(formattedDate)
}

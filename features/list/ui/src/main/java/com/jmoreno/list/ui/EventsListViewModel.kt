package com.jmoreno.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmoreno.list.domain.EventsDomainModel
import com.jmoreno.list.domain.FetchListUseCase
import com.jmoreno.list.ui.models.DateFormatted
import com.jmoreno.list.ui.models.EventItemUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class EventsListViewModel(private val fetchListUseCase: FetchListUseCase) : ViewModel() {
    val viewState: MutableStateFlow<FetchListViewState> = MutableStateFlow(FetchListViewState())
    private var filterNullsOrBlank: Boolean = true

    init {
        //  Fetch initial data
        //
        refreshItems()
    }

    private fun refreshItems() {
        viewModelScope.launch {
            viewState.emit(viewState.value.copy(isLoading = true, isError = false))
            yield()
            fetchListUseCase(filterNullsOrBlank).map { it.toUIModel() }.onSuccess {
                viewState.emit(viewState.value.copy(isLoading = false, data = it, isError = false))
            }.onFailure {
                viewState.emit(viewState.value.copy(isLoading = false, isError = true))
            }
            yield()
        }
    }

    fun toggle() {
        filterNullsOrBlank = !filterNullsOrBlank
        refreshItems()
    }

    fun refresh() {
        refreshItems()
    }
}


private fun List<EventsDomainModel>.toUIModel(): List<EventItemUI> {
    return map {
        EventItemUI(
            id = it.id,
            imgSrc = it.imgSrc,
            dateFormatted = formatDate(it.date),
            title = it.title,
            locationLine1 = it.locationline1,
            locationLine2 = it.locationline2,
            description = it.description
        )
    }
}

fun formatDate(date: String): DateFormatted {
    // Parse the input date string into a ZonedDateTime
    val zonedDateTime = ZonedDateTime.parse(date)

    // Get the device's default timezone
    val deviceTimeZone = ZoneId.systemDefault()

    // Convert the date to the device's timezone
    val localDateTime = zonedDateTime.withZoneSameInstant(deviceTimeZone)

    // Format it to the desired format
    val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' h:mm a")
    val formattedDate = localDateTime.format(formatter)
    return DateFormatted(formattedDate)
}

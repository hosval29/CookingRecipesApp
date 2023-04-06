package com.example.cookingrecipesapp.presentation.screens.map

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.example.cookingrecipesapp.core.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor() : ViewModel() {


    var state by mutableStateOf(MapState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.OnsetLatLng -> {
                state = state.copy(
                    latLng = setFormatLatLng(event.latitude, event.longitude)
                )
            }
        }
    }

    private fun setFormatLatLng(latitude: String, longitude: String): LatLng {
        Log.d("TAG", "setFormatLatLng: $latitude $longitude")
        val lat = latitude.toDouble()
        val lng = longitude.toDouble()
        return LatLng(lat, lng)
    }
}
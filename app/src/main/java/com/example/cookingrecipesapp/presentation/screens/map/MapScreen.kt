package com.example.cookingrecipesapp.presentation.screens.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun MapScreen(
    latitude: String,
    longitude: String,
    viewModel: MapViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(latitude) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.onEvent(MapEvent.OnsetLatLng(latitude = latitude, longitude = longitude))
        }
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (googleMap) = createRefs()
        val cameraPosition = CameraPositionState(
            position = CameraPosition.fromLatLngZoom(
                state.latLng,
                3f
            )
        )
        GoogleMap(
            modifier = Modifier
                .constrainAs(googleMap) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            cameraPositionState = cameraPosition
        ) {
            if (state.latLng != LatLng(0.0, 0.0)) {
                Marker(
                    state = MarkerState(state.latLng),
                    title = "${state.latLng}"
                )
            }
        }
    }
}
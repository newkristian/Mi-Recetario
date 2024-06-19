package me.kristianconk.mirecetario.presentation.feature.detail

data class DetailActions(
    val onBackClick: () -> Unit = {},
    val onMapClick: (Double, Double) -> Unit = {_,_ ->}
)

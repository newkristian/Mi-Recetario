package me.kristianconk.mirecetario.domain.model

enum class OrderBy {
    NAME_ASCENDING,
    NAME_DESCENDING,
    LESS_INGREDIENTS,
    MORE_INGREDIENTS,
    LESS_STEPS,
    MORE_STEPS,
    RECENT,
    OLDER,
    NEAR_TO_LOCATION,
    NONE
}
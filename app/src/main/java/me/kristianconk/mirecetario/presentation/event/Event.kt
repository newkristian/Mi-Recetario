package me.kristianconk.mirecetario.presentation.event

class Event<T>(val data: T) {

    private var consumed = false
    fun getIfNotConsumed(): Any? {
        if(!consumed) {
            consumed = true
            return data
        }
        return null
    }
}
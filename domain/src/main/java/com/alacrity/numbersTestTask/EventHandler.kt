package com.alacrity.numbersTestTask

interface EventHandler<T> {
    fun obtainEvent(event: T)
}
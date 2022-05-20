package com.example.healthy.domain.util

enum class Themes(val value: Int) {
    Light(1),
    Dark(2),
    Auto(3);

    companion object {
        private val VALUES = values()
        fun from(value: Int) : Themes = VALUES.first { it.value == value }
    }
}
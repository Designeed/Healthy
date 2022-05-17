package com.example.healthy.domain.use_cases.shared

class ValidateOnBlank {
    fun execute(params: List<String>): Boolean {
        params.forEach {
            if (it.isBlank())
                return false
        }

        return true
    }
}
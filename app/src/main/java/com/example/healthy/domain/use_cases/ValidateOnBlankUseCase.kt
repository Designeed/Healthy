package com.example.healthy.domain.use_cases

class ValidateOnBlankUseCase {

    fun execute(vararg params: String): Boolean {
        params.forEach {
            if (it.isBlank())
                return false
        }
        return true
    }
}
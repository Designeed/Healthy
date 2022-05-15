package com.example.healthy.domain.use_cases

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidateOnBlankTest {

    @Test
    fun `List with all empty items`() {
        val list = listOf(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )

        val result = ValidateOnBlank().execute(list)
        assertThat(result).isFalse()
    }

    @Test
    fun `List with two empty items`() {
        val list = listOf(
            "item 1",
            "",
            ""
        )

        val result = ValidateOnBlank().execute(list)
        assertThat(result).isFalse()
    }

    @Test
    fun `List with out empty items`() {
        val list = listOf(
            "item 1",
            "item 2",
            "item 3"
        )

        val result = ValidateOnBlank().execute(list)
        assertThat(result).isTrue()
    }

    @Test
    fun `List with one empty items`() {
        val list = listOf(
            "item 1",
            "item 2",
            "item 3",
            ""
        )

        val result = ValidateOnBlank().execute(list)
        assertThat(result).isFalse()
    }
}
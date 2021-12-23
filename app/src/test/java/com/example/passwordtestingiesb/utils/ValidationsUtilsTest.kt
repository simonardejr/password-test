package com.example.passwordtestingiesb.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidationsUtilsTest {



    @Test
    fun `Validar fun isContainDigit funciona corretamente`() {

        val stringWithNumbers = "teste123"
        val stringWithOutNumbers = "teste"


        assertThat(ValidationsUtils.isContainDigit(stringWithNumbers)).isTrue()
        assertThat(ValidationsUtils.isContainDigit(stringWithOutNumbers)).isFalse()

    }


}
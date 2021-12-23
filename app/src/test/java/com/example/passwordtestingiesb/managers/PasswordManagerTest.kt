package com.example.passwordtestingiesb.managers

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PasswordManagerTest {


    @Test
    fun `Digitar senha menor que 8 caracteres, retornar erro de 8 caracteres`() {
        val password = buildString {
            for(i in 1 until 8) {
                append(i)
            }
        }

        val result = PasswordManager.calculatePassword(password)

        assertThat(result.javaClass).isEqualTo(PasswordStatus.Invalid::class.java)

        assertThat((result as PasswordStatus.Invalid).type)
            .contains(InvalidType.Minimo8Caracteres)
    }

    @Test
    fun `Digitar senha sem Numeros, retorn erro de falta de numero`() {
        val password = buildString {
            for(i in 1 until 9) {
                append("a")
            }
        }

        val result = PasswordManager.calculatePassword(password)

        assertThat(result.javaClass).isEqualTo(PasswordStatus.Invalid::class.java)

        assertThat((result as PasswordStatus.Invalid).type)
            .contains(InvalidType.Minimo1Numero)
    }





}
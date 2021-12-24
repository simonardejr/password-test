package com.example.passwordtestingiesb.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

object ValidationsUtils {

    fun isContainDigit(str: String): Boolean {
        val regexPatt: Pattern = Pattern.compile("\\d", Pattern.DOTALL)
        val matcherRegex: Matcher = regexPatt.matcher(str)
        return matcherRegex.find()
    }

    fun isContainSpecialCharacter(str: String): Boolean {

        return false
    }


}
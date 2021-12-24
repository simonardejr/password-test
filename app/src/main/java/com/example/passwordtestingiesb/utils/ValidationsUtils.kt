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
        val regexPatt: Pattern = Pattern.compile("[^A-Za-z0-9]", Pattern.CASE_INSENSITIVE)
        val matcherRegex: Matcher = regexPatt.matcher(str)
        return matcherRegex.find()
    }


}
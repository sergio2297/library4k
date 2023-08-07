package es.sfernandez.libraries.library4k

import java.math.BigDecimal

object BigDecimalUtils {

    fun isZero(num: BigDecimal) : Boolean {
        return areEquals(num, 0)
    }

    fun areEquals(num1: BigDecimal, num2 : Number) : Boolean {
        return bigDecimalOf(num2).compareTo(num1) == 0
    }

    fun withinRange(num: BigDecimal, start: Number, end: Number) : Boolean {
        return bigDecimalOf(start) <= num
                && num <= bigDecimalOf(end)
    }

    fun bigDecimalOf(num: Number) : BigDecimal {
        return BigDecimal.valueOf(num.toDouble())
    }

}
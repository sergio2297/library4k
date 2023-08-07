package es.sfernandez.libraries.library4k.commontypes

import es.sfernandez.libraries.library4k.BigDecimalUtils
import java.math.BigDecimal

class Percentage(
    value: Number,
    valueOutOfRange: Boolean = false
) {

    //---- Attributes ----
    val value: BigDecimal

    //---- Constructor ----
    init {
        checkIfValueIsOutOfRangeIncorrectly(value, valueOutOfRange)
        this.value = BigDecimal.valueOf(value.toDouble())
    }

    private fun checkIfValueIsOutOfRangeIncorrectly(value: Number, valueOutOfRange: Boolean) {
        if(!valueOutOfRange && !BigDecimalUtils.withinRange(BigDecimal.valueOf(value.toDouble()), 0, 100))
            throw IllegalArgumentException("Error. Value can not be out of range [0, 100] unless you explicitly configure it.")
    }

    //---- Methods ----
    fun apply(number: Number) : BigDecimal {
        return value.divide(BigDecimal.valueOf(100))
            .multiply(BigDecimal.valueOf(number.toDouble()))
    }

}
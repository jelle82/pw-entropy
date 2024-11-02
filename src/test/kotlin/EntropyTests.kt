import org.junit.jupiter.api.Test
import kotlin.math.log2
import kotlin.math.round
import kotlin.test.assertEquals

class EntropyTests {

    /*
    Source: https://proton.me/blog/what-is-password-entropy

    E = L × log2(R)
    L = num characters
    R is the possible range of character types in your password
     */

    @Test
    fun entropyTests() {

        assertEquals(
            68.4,
            "Bankruptcies".entropy().round(1)
        )

        assertEquals(
            83.4,
            "1Bankruptcies2".entropy().round(1)
        )

        assertEquals(
            96.0,
            "1Bankruptcies2&%".entropy(listOf('&', '%')).round(1)
        )

    }

    @Test
    fun rounding() {
        assertEquals(8.1, 8.101.round(1))
        assertEquals(8.2, 8.151.round(1))
        assertEquals(8.15, 8.151.round(2))
       //  assertEquals(8.16, 8.155.round(2))
    }

    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }
}

fun String.entropy(possibleSpecialChars: List<Char> = listOf()): Double {
    var totalRange = 0

    if (any { it.isUpperCase() }) {
        totalRange += 26
    }

    if (any { it.isLowerCase() }) {
        totalRange += 26
    }

    if (any { it.isDigit() }) {
        totalRange += 10
    }

    val specialCharsFiltered =
        possibleSpecialChars.filterNot { it.isUpperCase() || it.isLowerCase() || it.isDigit() }
    if (any { it in specialCharsFiltered }) {
        totalRange += specialCharsFiltered.size
    }

    return (length * log2(totalRange.toDouble()))
}

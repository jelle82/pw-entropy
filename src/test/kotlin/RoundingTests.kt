import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RoundingTests {
    @Test
    fun rounding() {
        assertEquals(8.1, 8.101.round(1))
        assertEquals(8.2, 8.151.round(1))
        assertEquals(8.15, 8.151.round(2))
        //  assertEquals(8.16, 8.155.round(2))
    }
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}

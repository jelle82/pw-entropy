import org.junit.jupiter.api.Test
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
            0.0,
            "".entropy().round(1)
        )

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
}

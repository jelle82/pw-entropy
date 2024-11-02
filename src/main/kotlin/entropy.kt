import kotlin.math.log2

/**
 * Calculates the entropy of a string based on its composition of character types.
 *
 * Entropy is a measure of uncertainty or randomness in a set of data. This function evaluates
 * the given string and considers the presence of uppercase letters, lowercase letters, digits,
 * and any specified special characters to compute its entropy.
 *
 * The formula used for entropy is:
 *
 *     entropy = length * log2(totalRange)
 *
 * where `totalRange` is the sum of the number of possible characters based on the types
 * present in the string.
 *
 * The possible character types include:
 * - Uppercase letters (A-Z): contributes 26 to the total range if any uppercase letter is present.
 * - Lowercase letters (a-z): contributes 26 to the total range if any lowercase letter is present.
 * - Digits (0-9): contributes 10 to the total range if any digit is present.
 * - Special characters: contributes the number of special characters specified in the
 *   `possibleSpecialChars` list, if any of them are present in the string.
 *
 * @param possibleSpecialChars A list of special characters that can be included in the entropy
 * calculation. Only those characters that are not letters or digits will be considered.
 * Defaults to an empty list if not provided.
 *
 * @return The entropy of the string as a Double, representing the uncertainty of the
 *         string's composition.
 *
 */
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

    if (totalRange == 0) {
        // log2 of 0 is undefined, returning 0.0
        return 0.0
    }

    return length * log2(totalRange.toDouble())
}

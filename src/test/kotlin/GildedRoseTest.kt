import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.Test
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GildedRoseTest {
    @ParameterizedTest
    @MethodSource("createItems") // This is the name of s static method
    fun `parametrized example for update quality`(actual: Item, expected: Item) {
        var gilde = GildedRose(listOf(actual))

        gilde.updateQuality()
        assertEquals(expected.toString(), gilde.items[0].toString())
        // We create the GildedRose object and call the updateQuality method
        // We test our assertions
    }

    companion object {
        @JvmStatic
        fun createItems() = listOf(
            Arguments.of(Item("expected", 0, 0), Item("expected", -1, 0)),
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 1, 3), Item("Backstage passes to a TAFKAL80ETC concert", 0, 6)),
            Arguments.of(Item("expected", 0, 3), Item("expected", -1, 1)),

        )
    }
}


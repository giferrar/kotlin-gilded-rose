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
            Arguments.of(Item("Sulfuras, Hand of Ragnaros", 0, 80), Item("Sulfuras, Hand of Ragnaros", 0, 80)),
            Arguments.of(Item("Sulfuras, Hand of Ragnaros", -1, 80), Item("Sulfuras, Hand of Ragnaros", -1, 80)),
            Arguments.of(Item("Aged Brie", 2, 0), Item("Aged Brie", 1, 1)),
            Arguments.of(Item("Aged Brie", 0, 0), Item("Aged Brie", -1, 2)),
            Arguments.of(Item("Aged Brie", 0, 51), Item("Aged Brie", -1, 51)),
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 0, 20), Item("Backstage passes to a TAFKAL80ETC concert", -1, 0)),
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 20, 20), Item("Backstage passes to a TAFKAL80ETC concert", 19, 21)),
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 5, 49), Item("Backstage passes to a TAFKAL80ETC concert", 4, 50)),
            Arguments.of(Item("foo", 0, 2), Item("foo", -1, 0)),
            Arguments.of(Item("foo", 0, 0), Item("foo", -1, 0)),
            Arguments.of(Item("foo", 2, 0), Item("foo", 1, 0)),
            Arguments.of(Item("Conjured Mana Cake", 3, 6), Item("Conjured Mana Cake", 2, 4)),
            Arguments.of(Item("Conjured Mana Cake", 0, 6), Item("Conjured Mana Cake", -1, 2))
            )
    }
}


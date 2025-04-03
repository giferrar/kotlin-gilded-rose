import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GildedRoseTest {

    @ParameterizedTest
    @MethodSource("createItems")
    fun `parametrized test for update quality`(actual: Item, expected: Item) {
        val updatedItem = getUpdatedItem(actual)

        assertEquals(expected.toString(), updatedItem.toString(), "Item '${actual.name}' was not updated properly")
    }

    companion object {
        @JvmStatic
        fun createItems() = listOf(
            // TODO Here we will write our test cases parameters
            Arguments.of(Item("foo", 1, 0), Item("foo", 0, 0)),
        )

        fun getUpdatedItem(actual: Item): Item {
            val app = GildedRose(listOf(actual))
            app.updateQuality()
            return app.items[0]
        }
    }

}

import model.factory.BrieUpdater
import model.factory.PassUpdater

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items.stream().forEach { item ->
            // TODO next we will implement object updaters that we will later use in a factory method
            when (item.name) {
                "Aged Brie" -> {
                    BrieUpdater().update(item)
                }
                "Backstage passes to a TAFKAL80ETC concert" -> {
                    PassUpdater().update(item)
                }
                "Sulfuras, Hand of Ragnaros" -> {}
                else -> {
                    decreaseQuality(item)
                    item.sellIn -= 1
                    if (item.sellIn < 0) {
                        decreaseQuality(item)
                    }
                }
            }
        }
    }

    private fun decreaseQuality(item: Item) {
        if (item.quality > 0) {
            item.quality -= 1
        }
    }

}

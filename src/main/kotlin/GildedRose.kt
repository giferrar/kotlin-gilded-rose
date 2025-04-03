class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items.stream().forEach { item ->
            // TODO next we will implement object updaters that we will later use in a factory method
            when (item.name) {
                "Aged Brie" -> {
                    increaseQuality(item)
                    item.sellIn -= 1
                    if (item.sellIn < 0) {
                        increaseQuality(item)
                    }
                }
                "Backstage passes to a TAFKAL80ETC concert" -> {
                    increaseQuality(item)
                    if (item.sellIn < 11) {
                        increaseQuality(item)
                    }
                    if (item.sellIn < 6) {
                        increaseQuality(item)
                    }
                    item.sellIn -= 1
                    if (item.sellIn < 0) {
                        item.quality = 0
                    }
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

    private fun increaseQuality(item: Item) {
        if (item.quality < 50) {
            item.quality += 1
        }
    }

}

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items.stream().forEach { item ->
            if (item.name == "Aged Brie") {
                increaseQuality(item)
            } else if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                increaseQuality(item)
                if (item.sellIn < 11) {
                    increaseQuality(item)
                }
                if (item.sellIn < 6) {
                    increaseQuality(item)
                }
            } else if (item.name != "Sulfuras, Hand of Ragnaros") {
                decreaseQuality(item)
            }

            if (item.name != "Sulfuras, Hand of Ragnaros") {
                item.sellIn -= 1
            }

            if (item.sellIn < 0) {
                if (item.name == "Aged Brie") {
                    increaseQuality(item)
                } else if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                        item.quality = 0
                } else if (item.name != "Sulfuras, Hand of Ragnaros") {
                    decreaseQuality(item)
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

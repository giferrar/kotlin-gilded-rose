class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        fun incrementQuality(item: Item) {
            if (item.quality < 50) {
                item.quality++
            }
        }

        fun decreaseQuality(item: Item) {
            if (item.quality > 0) {
                item.quality--
            }
        }

        items.forEach { item ->
            if (item.name == "Aged Brie") {
                incrementQuality(item)
                item.sellIn--
                if (item.sellIn < 0) {
                    incrementQuality(item)
                }
            } else if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                incrementQuality(item)
                if (item.sellIn < 11) {
                    incrementQuality(item)
                }
                if (item.sellIn < 6) {
                    incrementQuality(item)
                }
                item.sellIn--
                if (item.sellIn < 0) {
                    item.quality = 0
                }
            } else if (item.name == "Sulfuras, Hand of Ragnaros") {
                // Do nothing
            } else if (item.name == "Conjured Mana Cake") {
                decreaseQuality(item)
                decreaseQuality(item)
                item.sellIn--
                if (item.sellIn < 0) {
                    decreaseQuality(item)
                    decreaseQuality(item)
                }
            } else {
                decreaseQuality(item)
                item.sellIn--
                if (item.sellIn < 0) {
                    decreaseQuality(item)
                }
            }

        }
    }

}

package model.factory

import Item

class UpdaterFactory {
    companion object {
        fun getUpdater(item: Item): ItemUpdater {
            return when (item.name) {
                "Aged Brie" -> BrieUpdater(item)
                "Backstage passes to a TAFKAL80ETC concert" -> PassUpdater(item)
                "Sulfuras, Hand of Ragnaros" -> SulfurasUpdater(item)
                else -> GenericUpdater(item)
            }
        }
    }
}

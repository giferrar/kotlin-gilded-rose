package model.factory

import Item

class UpdaterFactory {
    companion object {
        fun getUpdater(item: Item): ItemUpdater {
            return when (item.name) {
                "Aged Brie" -> BrieUpdater()
                "Backstage passes to a TAFKAL80ETC concert" -> PassUpdater()
                "Sulfuras, Hand of Ragnaros" -> SulfurasUpdater()
                else -> GenericUpdater()
            }
        }
    }
}

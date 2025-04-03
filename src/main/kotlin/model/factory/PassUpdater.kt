package model.factory

import Item

class PassUpdater(val item: Item): ItemUpdater() {

    override fun update() {
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

}

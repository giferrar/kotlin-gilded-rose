package model.factory

import Item

class PassUpdater: ItemUpdater() {

    override fun update(item: Item) {
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

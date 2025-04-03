package model.factory

import Item

class ConjuredUpdater(val item: Item): ItemUpdater() {

    override fun update() {
        decreaseQuality(item)
        decreaseQuality(item)
        item.sellIn -= 1
        if (item.sellIn < 0) {
            decreaseQuality(item)
            decreaseQuality(item)
        }
    }

}

package model.factory

import Item

class GenericUpdater(val item: Item): ItemUpdater() {

    override fun update() {
        decreaseQuality(item)
        item.sellIn -= 1
        if (item.sellIn < 0) {
            decreaseQuality(item)
        }
    }

}

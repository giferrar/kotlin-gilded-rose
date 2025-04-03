package model.factory

import Item

class GenericUpdater: ItemUpdater() {

    override fun update(item: Item) {
        decreaseQuality(item)
        item.sellIn -= 1
        if (item.sellIn < 0) {
            decreaseQuality(item)
        }
    }

}

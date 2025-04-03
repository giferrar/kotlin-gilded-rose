package model.factory

import Item

abstract class ItemUpdater {

    abstract fun update()

    internal fun decreaseQuality(item: Item) {
        if (item.quality > 0) {
            item.quality -= 1
        }
    }

    internal fun increaseQuality(item: Item) {
        if (item.quality < 50) {
            item.quality += 1
        }
    }
}

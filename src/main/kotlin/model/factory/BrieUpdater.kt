package model.factory

import Item

class BrieUpdater: ItemUpdater() {

    override fun update(item: Item) {
        increaseQuality(item)
        item.sellIn -= 1
        if (item.sellIn < 0) {
            increaseQuality(item)
        }
    }

}

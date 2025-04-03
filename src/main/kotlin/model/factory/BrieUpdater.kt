package model.factory

import Item

class BrieUpdater(val item: Item): ItemUpdater() {

    override fun update() {
        increaseQuality(item)
        item.sellIn -= 1
        if (item.sellIn < 0) {
            increaseQuality(item)
        }
    }

}

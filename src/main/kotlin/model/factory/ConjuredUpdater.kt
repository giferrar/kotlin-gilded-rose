package model.factory

import Item

class ConjuredUpdater(val item: Item): ItemUpdater() {

    override fun update() {
        item.sellIn = 2
        item.quality = 4
    }

}

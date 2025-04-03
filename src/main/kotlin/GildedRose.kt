import model.factory.BrieUpdater
import model.factory.GenericUpdater
import model.factory.PassUpdater
import model.factory.SulfurasUpdater

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items.stream().forEach { item ->
            // TODO next we will implement object updaters that we will later use in a factory method
            when (item.name) {
                "Aged Brie" -> BrieUpdater().update(item)
                "Backstage passes to a TAFKAL80ETC concert" -> PassUpdater().update(item)
                "Sulfuras, Hand of Ragnaros" -> SulfurasUpdater().update(item)
                else -> GenericUpdater().update(item)
            }
        }
    }

}

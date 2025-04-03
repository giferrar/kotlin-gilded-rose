import model.factory.*

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items.stream().forEach {
            UpdaterFactory.getUpdater(it).update()
        }
    }

}

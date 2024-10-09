package com.gildedrose

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            when {
                item.name == "Aged Brie" -> updateAgedBrie(item)
                item.name.startsWith("Backstage passes") -> updateBackstagePass(item)
                item.name.startsWith("Conjured") -> updateConjuredItem(item)
                item.name != "Sulfuras, Hand of Ragnaros" -> updateNormalItem(item)
            }
            decrementSellIn(item)
            if (item.sellIn < 0) {
                applyPostSellInEffects(item)
            }
        }
    }

    private fun applyPostSellInEffects(item: Item) {
        when {
            item.name == "Aged Brie" && item.quality < 50 -> item.quality++
            item.name == "Backstage passes to a TAFKAL80ETC concert" -> item.quality = 0
            item.quality > 0 && item.name != "Sulfuras, Hand of Ragnaros" -> item.quality--
        }
    }

    private fun updateAgedBrie(item: Item) {
        if (item.quality < 50) {
            item.quality++
        }
    }

    private fun updateBackstagePass(item: Item) {
        if (item.quality < 50) {
            item.quality++
        }
        if (item.sellIn < 11 && item.quality < 50) {
            item.quality++
        }
        if (item.sellIn < 6 && item.quality < 50) {
            item.quality++
        }
    }

    private fun updateNormalItem(item: Item) {
        if (item.quality > 0) {
            item.quality--
        }
    }

     private fun updateConjuredItem(item: Item) {
         if (item.quality > 0) {
            item.quality -= 2
            if (item.quality < 0) item.quality = 0
        }
        if (item.sellIn < 0 && item.quality > 0) {
            item.quality -= 2
            if (item.quality < 0) item.quality = 0
        }
    }

    private fun decrementSellIn(item: Item) {
        if (item.name != "Sulfuras, Hand of Ragnaros") {
            item.sellIn--
        }
    }
}
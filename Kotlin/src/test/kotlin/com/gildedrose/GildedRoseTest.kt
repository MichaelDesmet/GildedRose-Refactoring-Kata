package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

      @Test
    fun `normal item degrades quality and sellIn correctly`() {
        val items = listOf(Item("Elixir of the Mongoose", 5, 7))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(6, items[0].quality)
        assertEquals(4, items[0].sellIn)
    }

    // Negative
    @Test
    fun `normal item quality does not go below 0`() {
        val items = listOf(Item("Elixir of the Mongoose", 5, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, items[0].quality)
    }

    @Test
    fun `Aged Brie increases in quality`() {
        val items = listOf(Item("Aged Brie", 2, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(1, items[0].quality)
        assertEquals(1, items[0].sellIn)
    }

    // Negative
    @Test
    fun `Aged Brie quality does not exceed 50`() {
        val items = listOf(Item("Aged Brie", 2, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(50, items[0].quality)
    }

    @Test
    fun `Sulfuras does not degrade in quality or sellIn`() {
        val items = listOf(Item("Sulfuras, Hand of Ragnaros", 0, 80))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(80, items[0].quality)
        assertEquals(0, items[0].sellIn)
    }

    @Test
    fun `Backstage passes increase in quality as sellIn decreases`() {
        val items = listOf(Item("Backstage passes to a TAFKAL80ETC concert", 10, 45))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(47, items[0].quality)
        assertEquals(9, items[0].sellIn)
    }

     // Negative
    @Test
    fun `Backstage passes quality does not exceed 50`() {
        val items = listOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 49))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(50, items[0].quality)
    }

    @Test
    fun `Backstage passes quality drops to 0 after concert`() {
        val items = listOf(Item("Backstage passes to a TAFKAL80ETC concert", 0, 20))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, items[0].quality)
        assertEquals(-1, items[0].sellIn)
    }

    
}
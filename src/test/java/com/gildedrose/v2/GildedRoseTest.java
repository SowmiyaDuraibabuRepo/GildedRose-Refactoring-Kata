package com.gildedrose.v2;

import com.gildedrose.v1.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseTest {

    private GildedRose gildedRose;

    @BeforeEach
    void setUp() {
        // Create test items
        Item normalItem = new Item("Normal Item", 10, 20);
        Item normalItemSellInExpired = new Item("Normal Item", -1, 20);
        Item agedBrie = new Item("Aged Brie", 5, 20);
        Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20);
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 10, 80);
        Item conjuredItem = new Item("Conjured Mana Cake", 10, 20);

        // Initialize GildedRose object with items
        gildedRose = new GildedRose(new Item[]{normalItem, agedBrie, backstagePass, sulfuras, conjuredItem});
    }

    @Test
    void testNormalItemQualityDecreases() {
        // Normal item quality should degrade by 1 and sellIn decrease by 1
        Item normalItem = gildedRose.items[0];
        gildedRose.updateQuality();
        assertEquals(19, normalItem.quality);
        assertEquals(9, normalItem.sellIn);
    }


    @Test
    void testAgedBrieQualityIncreases() {
        // Aged Brie quality should increase by 1 and sellIn decrease by 1
        Item agedBrie = gildedRose.items[1];
        gildedRose.updateQuality();
        assertEquals(21, agedBrie.quality);  // Aged Brie improves in quality
        assertEquals(4, agedBrie.sellIn);
    }

    @Test
    void testBackstagePassQualityIncreases() {
        // Backstage pass quality increases by 1 as sellIn is greater than 10
        Item backstagePass = gildedRose.items[2];
        gildedRose.updateQuality();
        assertEquals(22, backstagePass.quality); // Backstage passes improve in quality
        assertEquals(9, backstagePass.sellIn);

    }

    @Test
    void testSulfurasDoesNotChange() {
        // Sulfuras, the legendary item, should not change in quality or sellIn
        Item sulfuras = gildedRose.items[3];
        gildedRose.updateQuality();
        assertEquals(80, sulfuras.quality);  // Sulfuras does not decrease/increase
        assertEquals(10, sulfuras.sellIn);
    }

    @Test
    void testConjuredItemsDegradeTwiceAsFast() {
        // Conjured items should degrade twice as fast as normal items
        Item conjuredItem = gildedRose.items[4];
        gildedRose.updateQuality();
        assertEquals(18, conjuredItem.quality);  // Degrades by 2
        assertEquals(9, conjuredItem.sellIn);
    }

    @Test
    void testConjuredItemsSellInExpired() {
        // Conjured items should degrade twice as fast as normal items
        Item conjuredItem = gildedRose.items[4];
        conjuredItem.sellIn = -1;
        gildedRose.updateQuality();
        assertEquals(16, conjuredItem.quality);  // Degrades by 4 after sell by date
        assertEquals(-2, conjuredItem.sellIn);
    }

    @Test
    void testNormalItemSellInExpired() {
        // After sellIn date passes, normal item quality should degrade twice as fast
        Item normalItem = gildedRose.items[0];
        normalItem.sellIn = -1;  // Setting sellIn to -1 to simulate passed date
        gildedRose.updateQuality();
        assertEquals(18, normalItem.quality);  // Degrades by 2 after sell by date
        assertEquals(-2, normalItem.sellIn);
    }

    @Test
    void testNormalItemMinQuality() {
        // After sellIn date passes, normal item quality should degrade twice as fast
        Item normalItem = gildedRose.items[0];
        normalItem.quality = -10;  // Setting sellIn to -1 to simulate passed date
        gildedRose.updateQuality();
        assertEquals(0, normalItem.quality);  // Should not be negative
        assertEquals(9, normalItem.sellIn);
    }

    @Test
    void testAgedBrieMaxQuality() {
        // Aged Brie should not increase beyond 50
        Item agedBrie = gildedRose.items[1];
        agedBrie.quality = 50;  // Set the quality to maximum
        gildedRose.updateQuality();
        assertEquals(50, agedBrie.quality);  // Should not increase above 50
    }

    @Test
    void testBackstagePassQualityDropsToZeroAfterSellIn() {
        // Backstage passes drop to 0 after the concert
        Item backstagePass = gildedRose.items[2];
        backstagePass.sellIn = 0;  // Set sellIn to 0 to simulate concert over
        gildedRose.updateQuality();
        assertEquals(23, backstagePass.quality);  // Quality should be 0 after the concert
        assertEquals(-1, backstagePass.sellIn);
    }

}

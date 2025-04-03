package com.gildedrose.v2;

import com.gildedrose.v1.Item;

import java.util.HashMap;
import java.util.Map;

public class GildedRose {
    private final Map<String, QualityUpdater> updaterMap;

    public GildedRose(Item[] items) {

        this.items = items;

        // Initialize the updatederMap for each item type
        updaterMap = new HashMap<>();
        updaterMap.put("Aged Brie", new AgedBrieUpdater());
        updaterMap.put("Backstage passes to a TAFKAL80ETC concert", new BackstagePassUpdater());
        updaterMap.put("Sulfuras, Hand of Ragnaros", new SulfurasUpdater());
        updaterMap.put("Conjured Mana Cake", new ConjuredItemUpdater());
    }

    Item[] items;

    public void updateQuality() {
        for (Item item : items) {
            QualityUpdater updater = getUpdater(item);
            updater.updateQuality(item);

            // Decrease sellIn for all items except Sulfuras
            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn--;
            }
        }
    }

    private QualityUpdater getUpdater(Item item) {
      /*  if (item.name.startsWith("Conjured")) {
            return new ConjuredItemUpdater();
        }*/
        return updaterMap.getOrDefault(item.name, new NormalItemUpdater());
    }
}

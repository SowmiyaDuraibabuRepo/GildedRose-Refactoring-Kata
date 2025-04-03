package com.gildedrose.v2;

import com.gildedrose.v1.Item;

public class ConjuredItemUpdater implements QualityUpdater{


    @Override
    public void updateQuality(Item item) {
        if (item.quality > 0) {
            item.quality -= 2;
        }

        if (item.sellIn < 0 && item.quality > 0) {
            item.quality -= 2;
        }

        item.quality = Math.max(item.quality, 0);
    }
}

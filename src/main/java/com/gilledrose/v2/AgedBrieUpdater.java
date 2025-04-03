package com.gildedrose.v2;

import com.gildedrose.v1.Item;

public class AgedBrieUpdater implements QualityUpdater {

    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    @Override
    public void updateQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality++;
        }

        if (item.sellIn < 0 && item.quality < MAX_QUALITY) {
            item.quality++;
        }

        item.quality = Math.max(item.quality, MIN_QUALITY);
    }
}

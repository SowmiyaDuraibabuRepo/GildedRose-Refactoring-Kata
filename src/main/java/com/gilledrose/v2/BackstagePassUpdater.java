package com.gildedrose.v2;

import com.gildedrose.v1.Item;

public class BackstagePassUpdater implements QualityUpdater{

    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    @Override
    public void updateQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality++;
            if (item.sellIn <= 10) {
                item.quality = Math.min(item.quality + 1, MAX_QUALITY);
            }
            if (item.sellIn <= 5) {
                item.quality = Math.min(item.quality + 1, MAX_QUALITY);
            }
        }

        if (item.sellIn < 0) {
            item.quality = MIN_QUALITY;
        }
    }
}

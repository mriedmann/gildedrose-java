package com.gildedrose.items;

import com.gildedrose.Item;

class BackstagePass extends AbstractUpdatableItem {

    public BackstagePass(Item item) {
        super(item);
    }

    @Override
    public void update() {
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = 0;
            return;
        }

        if (item.quality >= 50) {
            return;
        }

        item.quality = item.quality + 1;

        if (item.sellIn < 10 && item.quality < 50) {
            item.quality = item.quality + 1;
        }

        if (item.sellIn < 5 && item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}

package com.gildedrose.items;

import com.gildedrose.Item;

class AgedBrie extends AbstractUpdatableItem {

    AgedBrie(Item item) {
        super(item);
    }

    @Override
    public void update() {
        item.sellIn = item.sellIn - 1;

        if (item.quality >= 50) {
            return;
        }

        item.quality = item.quality + 1;

        if (item.sellIn < 0) {
            item.quality = item.quality + 1;
        }
    }
}

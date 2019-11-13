package com.gildedrose.items;

import com.gildedrose.Item;

class DefaultItem extends AbstractUpdatableItem {

    DefaultItem(Item item) {
        super(item);
    }

    @Override
    public void update() {
        item.sellIn = item.sellIn - 1;

        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }

        if (item.quality > 0 && item.sellIn < 0) {
            item.quality = item.quality - 1;
        }
    }
}

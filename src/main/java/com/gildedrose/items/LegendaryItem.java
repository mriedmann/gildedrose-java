package com.gildedrose.items;

import com.gildedrose.Item;

class LegendaryItem extends AbstractUpdatableItem {

    LegendaryItem(Item item) {
        super(item);
    }

    @Override
    public void update() {
        //Legendary Items do not change
    }
}

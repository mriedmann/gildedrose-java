package com.gildedrose.items;

import com.gildedrose.Item;
import com.gildedrose.UpdatableItem;

abstract class AbstractUpdatableItem implements UpdatableItem {
    protected final Item item;

    public AbstractUpdatableItem(Item item){
        this.item = item;
    }
}

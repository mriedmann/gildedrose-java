package com.gildedrose.items;

import com.gildedrose.Item;
import com.gildedrose.UpdatableItem;
import com.gildedrose.UpdatableItemFactory;

public class UpdatableItemFactoryImpl implements UpdatableItemFactory {
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";

    public UpdatableItem getUpdatableItem(Item item) {
        switch (item.name) {
            case SULFURAS:
                return new LegendaryItem(item);
            case AGED_BRIE:
                return new AgedBrie(item);
            case BACKSTAGE_PASSES:
                return new BackstagePass(item);
            default:
                return new DefaultItem(item);
        }
    }
}

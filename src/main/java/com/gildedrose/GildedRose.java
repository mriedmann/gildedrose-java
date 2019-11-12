package com.gildedrose;

class GildedRose {
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateItem(items[i]);
        }
    }

    private void updateItem(Item item) {
        switch (item.name) {
            case SULFURAS:
                //Legendary Items do not change
                break;
            case AGED_BRIE:
                handleAgedBrieUpdate(item);
                break;
            case BACKSTAGE_PASSES:
                handleBackstagePassesUpdate(item);
                break;
            default:
                handleDefaultItemUpdate(item);
                break;
        }
    }

    private void handleDefaultItemUpdate(Item item) {
        item.sellIn = item.sellIn - 1;

        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }

        if (item.quality > 0 && item.sellIn < 0) {
            item.quality = item.quality - 1;
        }
    }

    private void handleBackstagePassesUpdate(Item item) {
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

    private void handleAgedBrieUpdate(Item item) {
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

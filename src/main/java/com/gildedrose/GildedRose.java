package com.gildedrose;

class GildedRose {
    private UpdatableItemFactory itemFactory;

    Item[] items;

    public GildedRose(Item[] items, UpdatableItemFactory itemFactory) {
        this.itemFactory = itemFactory;
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        UpdatableItem updatableItem = itemFactory.getUpdatableItem(item);
        updatableItem.update();
    }
}

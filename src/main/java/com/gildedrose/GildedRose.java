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
        UpdatableItem updatableItem;
        switch (item.name) {
            case SULFURAS:
                updatableItem = new LegendaryItem(item);
                break;
            case AGED_BRIE:
                updatableItem = new AgedBrie(item);
                break;
            case BACKSTAGE_PASSES:
                updatableItem = new BackstagePass(item);
                break;
            default:
                updatableItem = new DefaultItem(item);
                break;
        }
        updatableItem.update();
    }

    interface UpdatableItem {
        void update();
    }

    abstract class UpdatableItemDecorator implements UpdatableItem {
        protected final Item item;

        public UpdatableItemDecorator(Item item){
            this.item = item;
        }
    }

    class DefaultItem extends UpdatableItemDecorator {

        public DefaultItem(Item item) {
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

    class LegendaryItem extends UpdatableItemDecorator {

        public LegendaryItem(Item item) {
            super(item);
        }

        @Override
        public void update() {
            //Legendary Items do not change
        }
    }

    class BackstagePass extends UpdatableItemDecorator {

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

    class AgedBrie extends UpdatableItemDecorator {

        public AgedBrie(Item item) {
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
}

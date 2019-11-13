package com.gildedrose;

import static org.junit.Assert.*;

import com.gildedrose.items.UpdatableItemFactoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class GildedRoseTest {

    private GildedRose app;

    private final int ITEM_FOO = 0;
    private final int ITEM_DEX_VEST = 1;
    private final int ITEM_AGED_BRIE = 2;
    private final int ITEM_ELIXIR_MONGOOSE = 3;
    private final int ITEM_SULFURAS = 4;
    private final int ITEM_SULFURAS_2 = 5;
    private final int ITEM_BACKSTAGE_PASS_15 = 6;
    private final int ITEM_BACKSTAGE_PASS_10 = 7;
    private final int ITEM_BACKSTAGE_PASS_5 = 8;
    private final int ITEM_MANA_CAKE = 9;
    private final int ITEM_BACKSTAGE_PASS_15_50 = 10;
    private final int ITEM_BACKSTAGE_PASS_10_49 = 11;
    private final int ITEM_BACKSTAGE_PASS_5_48 = 12;

    private final Item[] prototypes = new Item[]{
            new Item("foo", 0, 1),
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", 2, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20),
            new Item("Conjured Mana Cake", 3, 6),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 50),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48),
    };

    private void daysPast(int days) {
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
    }

    @Before
    public void before() {
        Item[] items = Arrays.stream(prototypes).toArray(Item[]::new);
        app = new GildedRose(items, new UpdatableItemFactoryImpl());
    }

    @Test
    public void decreaseQualityByOneOnEachNormalItemEachDay() {
        daysPast(2);
        assertEquals(18, app.items[ITEM_DEX_VEST].quality);
        assertEquals(4, app.items[ITEM_MANA_CAKE].quality);
        assertEquals(5, app.items[ITEM_ELIXIR_MONGOOSE].quality);
    }

    @Test
    public void decreaseQualityByTwoOnEachNormalItemEachDayAfterSellInIsReducedToZero() {
        daysPast(4);
        assertEquals(1, app.items[ITEM_MANA_CAKE].quality);
    }

    @Test
    public void qualityIsNeverNegative() {
        daysPast(20);
        assertEquals(0, app.items[ITEM_FOO].quality);
        assertEquals(0, app.items[ITEM_DEX_VEST].quality);
        assertEquals(0, app.items[ITEM_MANA_CAKE].quality);
    }

    @Test
    public void agedBrieIncreasesQualityWithAge() {
        daysPast(4);
        assertEquals(6, app.items[ITEM_AGED_BRIE].quality);
    }

    @Test
    public void qualityOfAnItemIsNeverMoreThen50() {
        daysPast(51);
        assertEquals(50, app.items[ITEM_AGED_BRIE].quality);
    }

    @Test
    public void sufurasNeverDecreasesQuality() {
        daysPast(2);
        assertEquals(80, app.items[ITEM_SULFURAS].quality);
    }

    @Test
    public void sufurasNeverDecreasesSellIn() {
        daysPast(2);
        assertEquals(2, app.items[ITEM_SULFURAS_2].sellIn);
    }

    @Test
    public void backstagePassIncreaseQuality() {
        daysPast(2);
        assertEquals(22, app.items[ITEM_BACKSTAGE_PASS_15].quality);
    }

    @Test
    public void backstagePassIncreaseQualityBy2When10DaysLeft() {
        daysPast(2);
        assertEquals(24, app.items[ITEM_BACKSTAGE_PASS_10].quality);
    }

    @Test
    public void backstagePassIncreaseQualityBy3When5DaysLeft() {
        daysPast(2);
        assertEquals(26, app.items[ITEM_BACKSTAGE_PASS_5].quality);
    }

    @Test
    public void backstagePassIncreaseQualityButStopAt50() {
        daysPast(2);
        assertEquals(50, app.items[ITEM_BACKSTAGE_PASS_15_50].quality);
    }

    @Test
    public void backstagePassIncreaseQualityBy2When10DaysLeftButStopAt50() {
        daysPast(2);
        assertEquals(50, app.items[ITEM_BACKSTAGE_PASS_10_49].quality);
    }

    @Test
    public void backstagePassIncreaseQualityBy3When5DaysLeftButStopAt50() {
        daysPast(2);
        assertEquals(50, app.items[ITEM_BACKSTAGE_PASS_5_48].quality);
    }

    @Test
    public void backstagePassDropToZeroWhenExpired() {
        daysPast(6);
        assertEquals(0, app.items[ITEM_BACKSTAGE_PASS_5].quality);
    }

    @Test
    public void itemToString(){
        assertEquals("foo, 0, 1", app.items[ITEM_FOO].toString());
        assertEquals("+5 Dexterity Vest, 10, 20", app.items[ITEM_DEX_VEST].toString());
        assertEquals("Elixir of the Mongoose, 5, 7", app.items[ITEM_ELIXIR_MONGOOSE].toString());
    }

}

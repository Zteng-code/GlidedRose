package com.gildedrose;

import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;

/**
 * @author zhishiteng
 */
public class ApprovalTest {

    @Test
    public void testApproval() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Approvals.verifyAll("", app.items);
    }

    @Test
    public void updateQuality() {
        CombinationApprovals.verifyAllCombinations(
            this::callUpdateQuality,
            new String[]{"a common item", "Sulfuras, Hand of Ragnaros",
                "Backstage passes to a TAFKAL80ETC concert", "Aged Brie","Conjured Mana Cake"},
            new Integer[]{-1, 0, 20, 12},
            new Integer[]{0, 1, 49, 51, 80}
        );
    }

    private String callUpdateQuality(String name, int sellIn, int quality) {
        Item[] items = new Item[]{new Item(name, sellIn, quality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return app.items[0].toString();
    }
}

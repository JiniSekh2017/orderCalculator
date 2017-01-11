package com.order.promotion;

import com.order.constants.ItemType;
import com.order.model.Item;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ComboItemPromotionTest {

    private static final MathContext MC = new MathContext(2, RoundingMode.HALF_UP);

    @Test
    public void applyPromotionTest_AllQuantityFillsUnderPromotion() throws Exception {
        ComboItemPromotion promo = new ComboItemPromotion(ItemType.APPLE, ItemType.ORANGE, 10, 10, BigDecimal.valueOf(100));
        List<Item> items = new ArrayList<>();
        items.add(new Item(ItemType.ORANGE, 10, BigDecimal.TEN));
        items.add(new Item(ItemType.APPLE, 10, BigDecimal.TEN));
        promo.applyPromotion(items);
        assertEquals("Fixed Price should be 50", items.get(0).getItemFixedPrice().round(MC), BigDecimal.valueOf(50).round(MC));
        assertEquals("Fixed Price should be 50", items.get(1).getItemFixedPrice().round(MC), BigDecimal.valueOf(50).round(MC));
        assertEquals("Orange quantity should be zero after promotion", items.get(0).getQuantity(), 0);
        assertEquals("Apple quantity should be zero after promotion", items.get(1).getQuantity(), 0);
    }

    @Test
    public void applyPromotionTest_PartialQuantityFillsUnderPromotion() throws Exception {
        ComboItemPromotion promo = new ComboItemPromotion(ItemType.APPLE, ItemType.ORANGE, 10, 10, BigDecimal.valueOf(100));
        List<Item> items = new ArrayList<>();
        items.add(new Item(ItemType.ORANGE, 10, BigDecimal.TEN));
        items.add(new Item(ItemType.APPLE, 15, BigDecimal.TEN));
        promo.applyPromotion(items);
        assertEquals("Fixed Price should be 50", items.get(0).getItemFixedPrice().round(MC), BigDecimal.valueOf(50).round(MC));
        assertEquals("Fixed Price should be 50", items.get(1).getItemFixedPrice().round(MC), BigDecimal.valueOf(50).round(MC));
        assertEquals("Orange quantity should be zero after promotion", items.get(0).getQuantity(), 0);
        assertEquals("Apple quantity should be 5 after promotion", items.get(1).getQuantity(), 5);
    }

    @Test
    public void applyPromotionTest_NoPromotionApplicable() throws Exception {
        ComboItemPromotion promo = new ComboItemPromotion(ItemType.APPLE, ItemType.ORANGE, 500, 500, BigDecimal.valueOf(100));
        List<Item> items = new ArrayList<>();
        items.add(new Item(ItemType.ORANGE, 10, BigDecimal.TEN));
        items.add(new Item(ItemType.APPLE, 10, BigDecimal.TEN));
        items.add(new Item(ItemType.GRAPE, 10, BigDecimal.TEN));
        promo.applyPromotion(items);
        assertEquals("Fixed Price should be 0", items.get(0).getItemFixedPrice().round(MC), BigDecimal.valueOf(0).round(MC));
        assertEquals("Fixed Price should be 0", items.get(1).getItemFixedPrice().round(MC), BigDecimal.valueOf(0).round(MC));
        assertEquals("Fixed Price should be 0", items.get(2).getItemFixedPrice().round(MC), BigDecimal.valueOf(0).round(MC));
        assertEquals("Orange quantity should be 10 after promotion", items.get(0).getQuantity(), 10);
        assertEquals("Apple quantity should be 10 after promotion", items.get(1).getQuantity(), 10);
        assertEquals("Grape quantity should be 10 after promotion", items.get(2).getQuantity(), 10);
    }

    @Test
    public void applyPromotionTest_NoPromotionApplicable_BecauseOfNoMatchingItems() throws Exception {
        ComboItemPromotion promo = new ComboItemPromotion(ItemType.APPLE, ItemType.GRAPE, 10, 10, BigDecimal.valueOf(100));
        List<Item> items = new ArrayList<>();
        items.add(new Item(ItemType.ORANGE, 10, BigDecimal.TEN));
        items.add(new Item(ItemType.APPLE, 10, BigDecimal.TEN));
        promo.applyPromotion(items);
        assertEquals("Fixed Price should be 0", items.get(0).getItemFixedPrice().round(MC), BigDecimal.valueOf(0).round(MC));
        assertEquals("Fixed Price should be 0", items.get(1).getItemFixedPrice().round(MC), BigDecimal.valueOf(0).round(MC));
        assertEquals("Orange quantity should be ten after promotion", items.get(0).getQuantity(), 10);
        assertEquals("Apple quantity should be ten after promotion", items.get(1).getQuantity(), 10);
    }

}
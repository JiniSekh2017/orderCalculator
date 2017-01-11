package com.order.total;

import com.order.constants.ItemType;
import com.order.model.Item;
import com.order.promotion.ComboItemPromotion;
import com.order.promotion.FixedQuantityPricePromotion;
import com.order.promotion.PercentageOffPromotion;
import com.order.promotion.Promotion;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderCalculatorTest {

    @Test
    public void calculateOrderTotal_PromotionForTwoItems() throws Exception {
        List<Promotion> promotionList = new ArrayList<>();
        promotionList.add(new PercentageOffPromotion(ItemType.APPLE, 50));
        promotionList.add(new FixedQuantityPricePromotion(ItemType.ORANGE, BigDecimal.valueOf(50), 10));
        promotionList.add(new ComboItemPromotion(ItemType.APPLE, ItemType.ORANGE, 10, 10, BigDecimal.valueOf(100)));
        OrderCalculator orderCalculator = new OrderCalculator(promotionList);
        List<Item> items = new ArrayList<>();
        items.add(new Item(ItemType.APPLE, 200, BigDecimal.valueOf(5)));
        items.add(new Item(ItemType.ORANGE, 200, BigDecimal.valueOf(6)));
        items.add(new Item(ItemType.GRAPE, 300, BigDecimal.valueOf(7)));
        BigDecimal orderTotal = orderCalculator.calculateOrderTotal(items);
        Assert.assertEquals("Order total is not matching", BigDecimal.valueOf(3600).doubleValue(), orderTotal.doubleValue(), 0);
    }
    
    @Test
    public void calculateOrderTotal_PromotionForThreeItems() throws Exception {
        List<Promotion> promotionList = new ArrayList<>();
        promotionList.add(new ComboItemPromotion(ItemType.APPLE, ItemType.ORANGE, 5, 5, BigDecimal.valueOf(100)));
        promotionList.add(new PercentageOffPromotion(ItemType.PEAR, 50));
        promotionList.add(new FixedQuantityPricePromotion(ItemType.ORANGE, BigDecimal.valueOf(50), 10));
        OrderCalculator orderCalculator = new OrderCalculator(promotionList);
        List<Item> items = new ArrayList<>();
        items.add(new Item(ItemType.APPLE, 20, BigDecimal.valueOf(20)));
        items.add(new Item(ItemType.ORANGE, 33, BigDecimal.valueOf(20)));
        items.add(new Item(ItemType.PEAR, 30, BigDecimal.valueOf(30)));
        BigDecimal orderTotal = orderCalculator.calculateOrderTotal(items);
        Assert.assertEquals("Order total is not matching", BigDecimal.valueOf(960).doubleValue(), orderTotal.doubleValue(), 0);
    }
    
    @Test
    public void calculateOrderTotal_SinglePromotionCombination() throws Exception {
        List<Promotion> promotionList = new ArrayList<>();
        promotionList.add(new FixedQuantityPricePromotion(ItemType.ORANGE, BigDecimal.valueOf(50), 10));
        OrderCalculator orderCalculator = new OrderCalculator(promotionList);
        List<Item> items = new ArrayList<>();
        items.add(new Item(ItemType.APPLE, 20, BigDecimal.valueOf(20)));
        items.add(new Item(ItemType.ORANGE, 33, BigDecimal.valueOf(20)));
        items.add(new Item(ItemType.PEAR, 30, BigDecimal.valueOf(30)));
        BigDecimal orderTotal = orderCalculator.calculateOrderTotal(items);
        Assert.assertEquals("Order total is not matching", BigDecimal.valueOf(1510).doubleValue(), orderTotal.doubleValue(), 0);
    }
    
    @Test
    public void calculateOrderTotal_NoPromotionApplicable() throws Exception {
        OrderCalculator orderCalculator = new OrderCalculator();
        List<Item> items = new ArrayList<>();
        items.add(new Item(ItemType.APPLE, 20, BigDecimal.valueOf(20)));
        items.add(new Item(ItemType.ORANGE, 33, BigDecimal.valueOf(20)));
        BigDecimal orderTotal = orderCalculator.calculateOrderTotal(items);
        Assert.assertEquals("Order total is not matching", BigDecimal.valueOf(1060).doubleValue(), orderTotal.doubleValue(), 0);
    }
}
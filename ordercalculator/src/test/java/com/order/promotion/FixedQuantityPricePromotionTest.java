package com.order.promotion;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.order.constants.ItemType;
import com.order.model.Item;

public class FixedQuantityPricePromotionTest {

	private static final MathContext MC = new MathContext(2, RoundingMode.HALF_UP);
	
	@Test
	public void testApplyPromotion_FixedQuantityPromotion() {
		FixedQuantityPricePromotion fixedQtyPromo = new FixedQuantityPricePromotion(ItemType.APPLE, BigDecimal.valueOf(100), 20);
		List<Item> items = new ArrayList<>();
		items.add(new Item(ItemType.APPLE, 105, BigDecimal.TEN));
		items.add(new Item(ItemType.ORANGE, 100, BigDecimal.TEN));
		fixedQtyPromo.applyPromotion(items);
		assertEquals("Fixed Price for apple should be 500", items.get(0).getItemFixedPrice().round(MC), BigDecimal.valueOf(500).round(MC));
	    assertEquals("Apple quantity should be 5 after promotion", items.get(0).getQuantity(), 5);
	    assertEquals("Fixed Price for orange should be 0 as no promotion was applied", items.get(1).getItemFixedPrice().round(MC), BigDecimal.valueOf(0).round(MC));
	    assertEquals("Orange quantity should be 100 as no promotion was applied", items.get(1).getQuantity(), 100);
	}
}

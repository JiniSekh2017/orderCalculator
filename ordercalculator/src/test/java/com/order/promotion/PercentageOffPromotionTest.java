package com.order.promotion;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.order.constants.ItemType;
import com.order.model.Item;

public class PercentageOffPromotionTest {
	private static final MathContext MC = new MathContext(2, RoundingMode.HALF_UP);
	@Test
	public void testApplyPromotion_FixedPercentOffOnItems() {
		PercentageOffPromotion percentageOff = new PercentageOffPromotion(ItemType.APPLE, 25);
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item(ItemType.APPLE, 300, BigDecimal.valueOf(30)));
		itemList.add(new Item(ItemType.PEAR, 100, BigDecimal.valueOf(20)));
		percentageOff.applyPromotion(itemList);
		assertEquals("The unit price of apple should 22.5 after applying 25 percent off", BigDecimal.valueOf(22.5).round(MC), itemList.get(0).getPerUnitPrice().round(MC));
	}
}

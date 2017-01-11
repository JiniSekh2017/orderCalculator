package com.order.promotion;

import com.order.constants.ItemType;
import com.order.model.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComboItemPromotion implements Promotion {

    private static final BigDecimal DIVISOR_TWO = BigDecimal.valueOf(2);

    private ItemType first;
    private ItemType second;
    private int firstQuantity;
    private int secondQuantity;

    private BigDecimal comboPrice;

    public ComboItemPromotion(ItemType first, ItemType second, int firstQuantity, int secondQuantity, BigDecimal comboPrice) {
        this.first = first;
        this.second = second;
        this.firstQuantity = firstQuantity;
        this.secondQuantity = secondQuantity;
        this.comboPrice = comboPrice;
    }

    public void applyPromotion(List<Item> itemList) {

        Map<ItemType, Item> itemMap = new HashMap<>();

        for (Item item : itemList) {
            itemMap.put(item.getItemType(), item);
        }

        Item firstItem = itemMap.get(first);
        Item secondItem = itemMap.get(second);

        if(firstItem != null && secondItem != null) {
            while(firstItem.getQuantity() >= firstQuantity && secondItem.getQuantity() >= secondQuantity) {

                firstItem.setQuantity(firstItem.getQuantity() - firstQuantity);
                secondItem.setQuantity(secondItem.getQuantity() - secondQuantity);

                BigDecimal priceDividedByTwo = comboPrice.divide(DIVISOR_TWO, 2, BigDecimal.ROUND_HALF_UP);

                firstItem.setItemFixedPrice(firstItem.getItemFixedPrice().add(priceDividedByTwo));
                secondItem.setItemFixedPrice(secondItem.getItemFixedPrice().add(priceDividedByTwo));
            }
        }

    }
}

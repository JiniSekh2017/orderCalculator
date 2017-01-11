package com.order.promotion;

import com.order.constants.ItemType;
import com.order.model.Item;

import java.math.BigDecimal;
import java.util.List;

public class PercentageOffPromotion implements Promotion {

    private ItemType itemType;
    private double percentageOff;

    public PercentageOffPromotion(ItemType itemType, double percentageOff) {
        this.itemType = itemType;
        this.percentageOff = percentageOff;
    }

    public void applyPromotion(List<Item> itemList) {
        for (Item item : itemList) {
            if(item.getItemType().equals(itemType)) {
                item.setPerUnitPrice(item.getPerUnitPrice().subtract(item.getPerUnitPrice().multiply(BigDecimal.valueOf(percentageOff).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP))));
            }
        }
    }

}

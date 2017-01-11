package com.order.promotion;

import com.order.constants.ItemType;
import com.order.model.Item;

import java.math.BigDecimal;
import java.util.List;

public class FixedQuantityPricePromotion implements Promotion {

    private ItemType itemType;
    private BigDecimal price;
    private int quantity;

    public FixedQuantityPricePromotion(ItemType itemType, BigDecimal price, int quantity) {
        this.itemType = itemType;
        this.price = price;
        this.quantity = quantity;
    }

    public void applyPromotion(List<Item> itemList) {
        for (Item item : itemList) {
            if(item.getItemType().equals(itemType)) {
                int timesToApply = item.getQuantity() / quantity;
                int remainingQuantity = item.getQuantity() % quantity;
                if(timesToApply > 0) {
                    item.setItemFixedPrice(item.getItemFixedPrice().add(price.multiply(BigDecimal.valueOf(timesToApply))));
                    item.setQuantity(remainingQuantity);
                }
            }
        }
    }
}

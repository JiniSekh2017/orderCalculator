package com.order.model;

import com.order.constants.ItemType;

import java.math.BigDecimal;

public class Item {

    private ItemType itemType;
    private int quantity;
    private BigDecimal perUnitPrice;
    private BigDecimal itemFixedPrice = BigDecimal.ZERO;

    public Item(ItemType itemType, int quantity, BigDecimal perUnitPrice) {
        this.itemType = itemType;
        this.quantity = quantity;
        this.perUnitPrice = perUnitPrice;
    }

    public BigDecimal getItemFixedPrice() {
        return itemFixedPrice;
    }

    public void setItemFixedPrice(BigDecimal itemFixedPrice) {
        this.itemFixedPrice = itemFixedPrice;
    }

    public BigDecimal getPerUnitPrice() {
        return perUnitPrice;
    }

    public void setPerUnitPrice(BigDecimal perUnitPrice) {
        this.perUnitPrice = perUnitPrice;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemType=" + itemType +
                ", quantity=" + quantity +
                ", perUnitPrice=" + perUnitPrice +
                ", itemFixedPrice=" + itemFixedPrice +
                '}';
    }
}

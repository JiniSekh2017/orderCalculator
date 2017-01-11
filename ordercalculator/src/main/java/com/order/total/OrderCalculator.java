package com.order.total;

import com.order.model.Item;
import com.order.promotion.Promotion;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

public class OrderCalculator {

    private List<Promotion> promotionList;
    private Logger logger = Logger.getLogger(OrderCalculator.class.getName());
    
    public OrderCalculator(){
    	
    }

    public OrderCalculator(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    public BigDecimal calculateOrderTotal(List<Item> itemList) {
        logger.info("Applying promotion on " + itemList.size() + " items");
        
        if(null != promotionList){
	        for (Promotion promotion : promotionList) {
	            promotion.applyPromotion(itemList);
	        }
        }
        
        BigDecimal orderTotal = BigDecimal.ZERO;
        for (Item item : itemList) {
            BigDecimal itemPrice = item.getPerUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())).add(item.getItemFixedPrice());
            logger.info("Item " + item.getItemType() + " price is - Unit Price: " + item.getPerUnitPrice() +
                    " * " + item.getQuantity() + " and Fixed Price: " + item.getItemFixedPrice() + ", result: " + itemPrice);
            orderTotal = orderTotal.add(itemPrice);
        }
        return orderTotal;
    }

}

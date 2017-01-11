/**
 * 
 */
package com.order.promotion;


import com.order.model.Item;

import java.util.List;

/**
 * @author JINI
 *
 */
public interface Promotion {
	
	void applyPromotion(List<Item> itemList);

}

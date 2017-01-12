# orderCalculator 
#Following are the assumptions made for this project :
- Order total is calculated using Bigdecimal as it provides maximum precision . The value is rounded up to 2 decimal points using 
  RoundingMode HALF_UP .
- There is priority for the promotions . Any promotion in any combination can be applied to the list of items. 
- The order line will be eligible for a second promotion if and only if there is remaining quantity matching the promotion rule.

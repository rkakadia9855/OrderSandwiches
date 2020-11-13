package application;

import java.util.ArrayList;

public class Order implements Customizable {

  public static int lineNumber; //reset for each new order;
  private ArrayList<OrderLine> orderlines;
  
  public Order() {
    lineNumber = 0;
    orderlines = new ArrayList<OrderLine>();
  }
  
  @Override
  public boolean add(Object obj) {
    orderlines.add((OrderLine) obj);
    return true;
  }

  @Override
  public boolean remove(Object obj) {
    int orderIndex = orderlines.indexOf((OrderLine) obj);
    if(orderIndex != -1) {
      orderlines.remove(orderIndex);
      return true;
    }
    return false;
  }

}

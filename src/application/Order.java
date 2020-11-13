package application;

import java.util.ArrayList;

public class Order implements Customizable {

  public static int lineNumber; //reset for each new order;
  private ArrayList<OrderLine> orderlines;
  
  @Override
  public boolean add(Object obj) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean remove(Object obj) {
    // TODO Auto-generated method stub
    return false;
  }

}

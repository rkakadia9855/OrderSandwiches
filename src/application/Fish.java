package application;

import java.util.ArrayList;

public class Fish extends Sandwich{
  
  public Fish() {
    extras = new ArrayList<Extra>();
  }

  @Override
  public boolean add(Object obj) {
    if(this.extras.add((Extra) obj))
      return true;
    else 
      return false;
  }

  @Override
  public boolean remove(Object obj) {
    int index = this.extras.indexOf((Extra) obj);
    if(this.extras.remove((Extra) obj))
      return true;
    else
      return false;
  }

  @Override
  public double price() {
    double totalPrice = 12.99 + (1.99 * this.extras.size());
    return totalPrice;
  }

  @Override
  public String toString() {
    String details = "Fish Sandwich; ";
    details += "Grilled Snapper, Cilantro, Lime, ";
    details += "Extra: ";
    if(this.extras.size() == 0)
      details += "none. ";
    for(int i = 0; i < this.extras.size(); i++) {
      details += this.extras.get(i).name() + ", ";
    }
    details += "Price $"+ String.format("%.2f", price())+"\n";
    return details;
  }

}

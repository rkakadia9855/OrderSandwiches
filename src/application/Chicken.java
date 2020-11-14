package application;

import java.util.ArrayList;

public class Chicken extends Sandwich{
  
  public Chicken() {
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
    double totalPrice = 8.99 + (1.99 * this.extras.size());
    return totalPrice;
  }

  @Override
  public String toString() {
    String details = "Chicken Sandwich\n";
    details += "Basic ingredients: \n* Fried Chicken, Spicy Sauce, Pickles.\n";
    details += "Extra ingredients: \n* ";
    if(this.extras.size() == 0)
      details += "none.\n";
    for(int i = 0; i < this.extras.size(); i++) {
      if(i != (this.extras.size()-1)) 
        details += this.extras.get(i).name() + ", ";
      else 
        details += this.extras.get(i).name() + ".\n";
    }
    details += "Price: $"+ String.format("%.2f", price()) + "\n\n";
    return details;
  }

}

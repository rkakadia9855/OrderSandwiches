/**
 * This class represents a Fish Sandwich
 * @author John Juarez, Rudra Kakadia
 */
package application;

import java.util.ArrayList;

public class Fish extends Sandwich{
  
  /**
   * Initializes the Fish object's extras arraylist
   */
  public Fish() {
    extras = new ArrayList<Extra>();
  }

  @Override
  /**
   * This function adds an extra ingredient to this sandwich
   * @param obj - the extra ingredient that needs to be added
   * @return true if extra was successfully added, false otherwise
   */
  public boolean add(Object obj) {
    if(this.extras.add((Extra) obj))
      return true;
    else 
      return false;
  }

  @Override
  /**
   * This function removes an extra ingredient from this sandwich
   * @param obj - the extra ingredient that needs to be removed
   * @return true if extra was successfully removed, false otherwise
   */
  public boolean remove(Object obj) {
    int index = this.extras.indexOf((Extra) obj);
    if(this.extras.remove((Extra) obj))
      return true;
    else
      return false;
  }

  @Override
  /**
   * This function returns the price of the sandwich. (extra ingredients included)
   * @return the price of the sandwich
   */
  public double price() {
    double totalPrice = 12.99 + (1.99 * this.extras.size());
    return totalPrice;
  }

  @Override
  /**
   * This returns the string of this sandwich
   * @return a string that contains sandwich type, basic ingredients, extra ingredients, and price
   */
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

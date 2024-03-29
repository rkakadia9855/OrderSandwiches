/**
 * This class represents the parent Sandwich class
 * @author John Juarez, Rudra Kakadia
 */
package application;

import java.util.ArrayList;

public abstract class Sandwich implements Customizable {
  static final int MAX_EXTRAS = 6;
  static final double PER_EXTRA = 1.99;
  protected ArrayList<Extra> extras;
  
  public abstract double price();
  public abstract String toString();
}

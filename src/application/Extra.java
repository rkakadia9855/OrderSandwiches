/**
 * This class represents an extra ingredient
 * @author John Juarez, Rudra Kakadia
 */
package application;

public class Extra {
  private String ingredient;
  
  /**
   * Initializes the name of the extra ingredient
   */
  public Extra(String ingredient) {
    this.ingredient = ingredient;
  }
  
  /**
   * This function returns the name of this extra ingredient
   * @return the name of this extra ingredient
   */
  public String name() {
    return this.ingredient;
  }
}

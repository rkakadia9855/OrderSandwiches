/**
 * the junit test case for Order class
 * @author John Juarez, Rudra Kakadia
 */

package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderTest {
	
	/**
	 *	tests getOrderTotalTest() method
	 */
	
	@Test
	public void getOrderTotalTest() {
		Order tester = new Order();
		
		Sandwich sub = new Chicken();
		Sandwich sub2 = new Beef();
		Sandwich sub3 = new Fish();
		
		OrderLine order1 = new OrderLine(0,sub,8.99);
		OrderLine order2 = new OrderLine(1,sub2,10.99);
		OrderLine order3 = new OrderLine(2,sub3,12.99);
		
		
		
		 tester.add(order1);
		 tester.add(order2);
		 tester.add(order3);
		 tester.add(order3);
		
		double total = tester.getOrderTotal();
		assertEquals(45.96,total);
		
		tester.remove(order3);
		tester.remove(order3);
		tester.remove(order2);
		tester.remove(order1);
		
		assertEquals(0,tester.getOrderTotal());
		
	}
	
	/**
	 *	tests add() method
	 */
	
	@Test
	 public void addtest() {
		Order tester = new Order();
		Sandwich sub = new Chicken();
		Sandwich sub2 = new Beef();
		Sandwich sub3 = new Fish();
		
		OrderLine order1 = new OrderLine(0,sub,8.99);
		OrderLine order2 = new OrderLine(1,sub2,10.99);
		OrderLine order3 = new OrderLine(2,sub3,12.99);
		
		boolean expected1 = tester.add(order1);
		boolean expected2 = tester.add(order2);
		boolean expected3 = tester.add(order3);
		boolean expected4 = tester.add(order3);
		
		
		assertEquals(true,expected1);
		assertEquals(true,expected2);
		assertEquals(true,expected3);
		assertEquals(true,expected4);	
		
	}
	
	/**
	 *	tests remove() method
	 */
	
	@Test
	public void removetest() {
		Order tester = new Order();
		Sandwich sub = new Chicken();
		Sandwich sub2 = new Beef();
		Sandwich sub3 = new Fish();
		
		OrderLine order1 = new OrderLine(0,sub,8.99);
		OrderLine order2 = new OrderLine(1,sub2,10.99);
		OrderLine order3 = new OrderLine(2,sub3,12.99);
		
		 tester.add(order1);
		 tester.add(order2);
		 tester.add(order3);
		 
		 boolean expected1 = tester.remove(order1);
		 boolean expected2 = tester.remove(order2);
		 boolean expected3 = tester.remove(order3);
		 
		 
		 assertEquals(true,expected1);
		 assertEquals(true,expected2);
		 assertEquals(true,expected3);
		 
		 boolean notExpected = tester.remove(order3);
		 assertEquals(false,notExpected);
		 
		 double total = tester.getOrderTotal();
		 assertEquals(0,total);
	}
	
	/**
	 *  Tests getOrderLine() method
	 */
	
	@Test
	public void getOrderLineTest() {
		Order tester = new Order();
		Sandwich sub = new Chicken();
		Sandwich sub2 = new Beef();
		Sandwich sub3 = new Fish();
		
		OrderLine order1 = new OrderLine(0,sub,8.99);
		OrderLine order2 = new OrderLine(1,sub2,10.99);
		OrderLine order3 = new OrderLine(2,sub3,12.99);
		
		 tester.add(order1);
		 tester.add(order2);
		 tester.add(order3);
		 
		 int index = order1.getLineNumber();
		 OrderLine copy = tester.getOrderLine(index);
		 
		 assertTrue(copy.equals(order1));
		 assertFalse(copy.equals(order3));

	}
	
	/**
	 *  Tests numOrders() method
	 */
	
	@Test
	public void numOrdersTest() {
		Order tester = new Order();
		Sandwich sub = new Chicken();
		Sandwich sub2 = new Beef();
		Sandwich sub3 = new Fish();
		
		OrderLine order1 = new OrderLine(0,sub,8.99);
		OrderLine order2 = new OrderLine(1,sub2,10.99);
		OrderLine order3 = new OrderLine(2,sub3,12.99);
		
		 tester.add(order1);
		 tester.add(order2);
		 tester.add(order3);
		 
		 assertEquals(3,tester.numOrders());
		 
		 tester.remove(order1);
		 tester.remove(order2);
		 tester.remove(order3);
		 
		 assertEquals(0,tester.numOrders());
		 
		 for(int i=0; i<20;i++) {
			 tester.add(order1);
		 }
		 
		 assertEquals(20,tester.numOrders());
		 
	}
	
	/**
	 *  Tests getSerialNumber() method
	 */
	
	@Test
	public void getSerialNumberTest() {
		Order tester = new Order();
		Sandwich sub = new Chicken();
		Sandwich sub2 = new Beef();
		
		OrderLine order1 = new OrderLine(0,sub,8.99);
		OrderLine order2 = new OrderLine(1,sub2,10.99);
		
		
		 tester.add(order1);
		 tester.add(order2);
		 
		 int index = order2.getLineNumber();
		 int copy = tester.getSerialNumber(index);
		
		 assertEquals(1,copy);
		 
	}
	
	/**
	 *  Tests getDetails() method
	 */
	
	@Test
	public void getDetailsTest() {
		Order tester = new Order();
		Sandwich sub = new Chicken();
		Sandwich sub2 = new Beef();
		
		OrderLine order1 = new OrderLine(0,sub,8.99);
		OrderLine order2 = new OrderLine(1,sub2,10.99);
		
		 tester.add(order1);
		 tester.add(order2);
		
		 int index = order1.getLineNumber();
	
		 String line = tester.getDetails(index);
		 String copy = order1.sandwichToString();
		 
		 
		 assertTrue(line.equals(copy));
		 assertFalse(line.equals(order2.sandwichToString()));
	}
	
	/**
	 *  Tests printOrderLine() method
	 */
	
	@Test
	public void printOrderLineTest() {
		Order tester = new Order();
		Sandwich sub3 = new Fish();
		
		 String empty = tester.printOrderLine();
		 String copy = "";
		 System.out.println(empty);
		 
		 assertEquals(copy,empty);
		
		
		OrderLine order3 = new OrderLine(2,sub3,12.99);
		 tester.add(order3);
		 
		 String order = tester.printOrderLine();
		 
		 assertTrue(order.equals(order3.sandwichToString()));
	}
	
}

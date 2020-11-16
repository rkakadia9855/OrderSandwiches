package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderTest {

	@Test
	 public void addtest() {
		Order tester = new Order();
		Sandwich sub = new Chicken();
		Sandwich sub2 = new Beef();
		Sandwich sub3 = new Fish();
		
		OrderLine order1 = new OrderLine(1,sub,8.99);
		OrderLine order2 = new OrderLine(2,sub2,10.99);
		OrderLine order3 = new OrderLine(3,sub3,12.99);
		
		boolean expected1 = tester.add(order1);
		boolean expected2 = tester.add(order2);
		boolean expected3 = tester.add(order3);
		boolean expected4 = tester.add(order3);
		
		double total = tester.getOrderTotal();
		
		assertEquals(true,expected1);
		assertEquals(true,expected2);
		assertEquals(true,expected3);
		assertEquals(true,expected4);
		
		assertEquals(45.96,total);
	}
	
	@Test
	public void removetest() {
		Order tester = new Order();
		Sandwich sub = new Chicken();
		Sandwich sub2 = new Beef();
		Sandwich sub3 = new Fish();
		
		OrderLine order1 = new OrderLine(1,sub,8.99);
		OrderLine order2 = new OrderLine(2,sub2,10.99);
		OrderLine order3 = new OrderLine(3,sub3,12.99);
		
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
}

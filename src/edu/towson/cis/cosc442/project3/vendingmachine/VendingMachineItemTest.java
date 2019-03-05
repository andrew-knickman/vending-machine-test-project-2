package edu.towson.cis.cosc442.project3.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineItemTest {

	/**Declaration of VendingMachineItem test object*/
	VendingMachineItem vmi;
	
	/**Initialize  VendingMachineItem test object*/
	@Before
	public void setUp() throws Exception {
		vmi = new VendingMachineItem("Test Item", 1.99);
	}
	
	/**Clean up VendingMachineItem test object after testing*/
	@After
	public void tearDown() throws Exception {
		vmi = null;
	}

	/**Test for the VendingMachineItem() constructor of the VendingMachineItem class.*/
	@Test
	public void testVendingMachineItem() {
		assertTrue(vmi instanceof VendingMachineItem);
	}
	
	/**Test for VendingMachineItem() with less-than-zero price*/
	@Test
	public void testVendingMachineItemNegativePrice(){
		VendingMachineItem nitem;
		boolean thrown = false;
		try{
			nitem = new VendingMachineItem("Invalid Item", -1.99);
		}
		catch(VendingMachineException e){
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	/**Test for the getName() method of the VendingMachineItem class.*/
	@Test
	public void testGetName() {
		assertTrue(vmi.getName().equals("Test Item"));
	}
	
	/**Test for the getPrice() method of the VendingMachineItem class.*/
	@Test
	public void testGetPrice() {
		assertEquals(1.99,vmi.getPrice(),0.001);
	}

}

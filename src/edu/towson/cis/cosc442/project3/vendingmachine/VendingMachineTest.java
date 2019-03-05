package edu.towson.cis.cosc442.project3.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

	VendingMachine vm;
	VendingMachineItem vmi;
	
	/**Constructs test VendingMachine and VendingMachineItem objects*/
	@Before
	public void setUp() throws Exception {
		vm = new VendingMachine();
		vmi = new VendingMachineItem("Test Item", 1.99);
	}
	
	/**Cleans up test objects*/
	@After
	public void tearDown() throws Exception {
		vm = null;
		vmi = null;
	}

	/**Tests if addItem method adds VendingMachineItem and adds it correctly to VendingMachine*/
	@Test
	public void testAddItem() {
		vm.addItem(vmi, "A");
		assertTrue(vm.getItem("A").getName().equals("Test Item"));
		assertEquals(1.99,vm.getItem("A").getPrice(),0.001);
		assertTrue(vm.getItem("A") instanceof VendingMachineItem);
	}
	
	/**Tests if getSlotIndex returns correct code value*/
	@Test
	public void testGetSlotIndex()
	{
		assertEquals(0.0, vm.getSlotIndex("A"), 0.001);
		assertEquals(1.0, vm.getSlotIndex("B"), 0.001);
		assertEquals(2.0, vm.getSlotIndex("C"), 0.001);
		assertEquals(3.0, vm.getSlotIndex("D"), 0.001);
	}
	
	/**Tests getSlotIndex for invalid code*/
	@Test
	public void testGetSlotIndexInvalidCode() throws VendingMachineException
	{
		boolean thrown = false;
		try
		{
			vm.getSlotIndex("Z");
		}
		catch(VendingMachineException e){
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	/**Tests if addItem methods adds VendingMachineItem in an occupied slot*/
	@Test
	public void testAddItemOverload(){
		boolean thrown = false;
		vm.addItem(vmi,  "A");
		VendingMachineItem ovld = new VendingMachineItem("Overload Item", 1.99);
		try{
			vm.addItem(ovld, "A");
		}
		catch(VendingMachineException e){
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	/**Tests if removeItem method removes correct VendingMachineItem removes it correctly from VendingMachine*/
	@Test
	public void testRemoveItem() {
		vm.addItem(vmi, "A");
		VendingMachineItem removed = vm.removeItem("A");
		assertTrue(removed.getName().equals("Test Item"));
		assertEquals(1.99,removed.getPrice(),0.001);
		assertTrue(removed instanceof VendingMachineItem);
	}
	
	/**Tests if removeItem removes an item that is unavailable*/
	@Test
	public void testRemoveItemUnavailable(){
		boolean thrown = false;
		vm.addItem(vmi, "A");
		vm.removeItem("A");
		try{
			vm.removeItem("A");
		}
		catch(VendingMachineException e){
			thrown = true;
		}
		assertTrue(thrown);
	}

	/**Tests if insertMoney method adds correct value to VendingMachine balance*/ 
	@Test
	public void testInsertMoney() {
		vm.insertMoney(1.00);
		assertEquals(1.00,vm.balance,0.001);
		vm.balance = 0.0;
		assertEquals(0.00,vm.balance,0.001);
	}
	
	/**Tests if insertMoney is capable of removing money*/
	@Test
	public void testInsertInvalidMoney(){
		boolean thrown = false;
		try{
			vm.insertMoney(-1.00);
		}
		catch(VendingMachineException e){
			thrown = true;
		}
		assertTrue(thrown);
	}
	/**Tests is getBalance method returns correct balance of VendingMachine*/
	@Test
	public void testGetBalance() {
		vm.insertMoney(1.00);
		assertEquals(1.00,vm.getBalance(),0.001);
		vm.balance = 0.0;
		assertEquals(0.00,vm.balance,0.001);
	}
	
	/**Tests makePurchase method for attempt of null item purchase*/
	@Test
	public void testMakePurchaseNullItem()
	{
		vm.addItem(null, "A");
		assertFalse(vm.makePurchase("A"));
	}
	
	/**Tests makePurchase method for insufficient balance*/
	@Test
	public void testMakePurchaseInsufficientFunds()
	{
		vm.balance = 1.00;
		vm.addItem(vmi, "A");
		assertFalse(vm.makePurchase("A"));
	}
	
	/**Tests if makePurchase is able to purchase valid item from VendingMachine*/
	@Test
	public void testMakePurchase() {
		vm.balance = 2.00;
		vm.addItem(vmi, "A");
		assertTrue(vm.makePurchase("A"));
		vm.balance = 0.0;
		assertEquals(0.00,vm.balance,0.001);
	}

	/**Tests if returnChange method returns correct change value*/
	@Test
	public void testReturnChange() {
		vm.balance = 0.01;
		assertEquals(0.01,vm.returnChange(),0.001);
		vm.balance = 0.00;
	}

}

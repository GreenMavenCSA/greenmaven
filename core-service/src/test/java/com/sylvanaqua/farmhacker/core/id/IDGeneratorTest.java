package com.sylvanaqua.farmhacker.core.id;

import static org.junit.Assert.*;
import java.util.HashSet;
import org.junit.Test;


public class IDGeneratorTest {

	@Test
	public void test() {
		
		InventoryIDGenerator generator = new InventoryIDGenerator("0");
		assertEquals("1", generator.getNextID());
		
		generator = new InventoryIDGenerator("12");
		assertEquals("13", generator.getNextID());
		
		generator = new InventoryIDGenerator("999D");
		assertEquals("99A0", generator.getNextID());
		
		generator = new InventoryIDGenerator("11D7");
		assertEquals("11D8", generator.getNextID());
		
		generator = new InventoryIDGenerator("1DDD");
		assertEquals("2000", generator.getNextID());
		
		generator = new InventoryIDGenerator("D");
		assertEquals("10", generator.getNextID());
		
		generator = new InventoryIDGenerator(null);
		assertEquals("0", generator.getNextID());
		
		generator = new InventoryIDGenerator("0000");
		assertEquals("0001", generator.getNextID());
		
		generator = new InventoryIDGenerator("000D");
		assertEquals("0010", generator.getNextID());
		
		generator = new InventoryIDGenerator("0");
		HashSet<String> set = new HashSet<String>();
		int numberOfIDsToGenerate = 1000000;
		
		for(int i = 0; i < numberOfIDsToGenerate; i++) {
			String nextID = generator.getNextID();
			assertTrue(set.add(nextID));
			generator = new InventoryIDGenerator(nextID);
		}
	}

}

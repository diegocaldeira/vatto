package com.aoks.utils.test;

public class GenericTester {

	public void testEqualsAndHashCode(Object one, Object two, Object three){
		new EqualsTester(one, two, three, null);
	}
	
}

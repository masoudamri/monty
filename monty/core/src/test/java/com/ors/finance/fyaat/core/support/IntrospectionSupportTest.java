package com.ors.finance.fyaat.core.support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.ors.finance.fyaat.core.support.IntrospectionSupport;

public class IntrospectionSupportTest {

	static interface Interface1 {
		public Object getProp1();

	}

	static interface Interface2 extends Interface1 {
		public Object getProp2();
	}

	static interface Interface3 extends Interface1 {
		public default Object getProp3() {
			return new Object();
		}
	}

	static interface Interface4 extends Interface2, Interface3 {
	}

	static interface Interface5 extends Interface2, Interface3 {
		@Override
		default Object getProp3() {
			return Interface3.super.getProp3();
		}
	}

	static interface Interface6 extends Interface5 {

	}

	@Test
	public void simpleTest() {
		Assertions.assertEquals(2,descriptors(Interface2.class));
	}

	@Test
	public void simpleTest1() {
		Assertions.assertEquals(2,descriptors(Interface3.class));
	}

	
	@Test
	public void simpleTest2() {
		Assertions.assertEquals(3,descriptors(Interface4.class));
	}

	@Test
	public void simpleTest3() {
		Assertions.assertEquals(4,descriptors(Interface5.class));
	}

	@Test
	public void simpleTest4() {
		Assertions.assertEquals(4,descriptors(Interface6.class));
	}

	@Test
	static long descriptors(Class<?> clazz) {
		System.out.println(clazz.getName()+":");
		return IntrospectionSupport.propertyDescriptors(clazz).map(Object::toString)
				.peek(pd -> System.out.println(String.format("%s, ", pd))).count();
	}

}

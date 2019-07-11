package com.ors.finance.fyaat.core.support;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.stream.Stream;

public class IntrospectionSupport {
	
	
    public static Stream<Class<?>> interfaces(final Class<?> c) {
        return Stream.concat(Stream.of(c),
                        Arrays.stream(c.getInterfaces()
                ).flatMap(IntrospectionSupport::interfaces)).filter(Class::isInterface).distinct();
    }

	public static Stream<PropertyDescriptor> propertyDescriptors(Class<?> introspectType) {
		return (introspectType.isInterface() ? interfaces(introspectType)
				: Stream.of(introspectType)).flatMap(i -> Arrays.stream(getBeanInfo(i).getPropertyDescriptors()));
	}

	private static BeanInfo getBeanInfo(Class<?> c) {
		try {
			return Introspector.getBeanInfo(c);
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
	}
}

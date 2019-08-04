package com.spring.errorhandling;

public class EntityNotFoundException extends Exception {

	public EntityNotFoundException(Class clazz, String paramName, String paramValue) {
		super("Entity " + clazz.getName() + " was not found for parameter {" + paramName + "} and parameter value {" + paramValue + "}");
	}
}

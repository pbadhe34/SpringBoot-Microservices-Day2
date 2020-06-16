package com.server.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {

	
	/*
	 * Field or method level annotation that marks a field or method 
	 * providing the identifier of the aggregate that a command targets. 

      If placed on a method, that method must contain no parameters.
      The return value will be used as theAggregate Identifier. 

	 */
    @TargetAggregateIdentifier
    public final T id;

    public BaseCommand(T id) {
        this.id = id;
    }
}

package com.example.axam.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Getter;


public abstract class BaseCommand<T> {
    @TargetAggregateIdentifier
   @Getter private T id;

    public BaseCommand(T id) {
        this.id = id;
    }

   
}

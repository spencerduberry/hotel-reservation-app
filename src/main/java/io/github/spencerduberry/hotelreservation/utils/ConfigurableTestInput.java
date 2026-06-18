package io.github.spencerduberry.hotelreservation.utils;

public interface ConfigurableTestInput {
    void enqueueInput(String value);
    void reset();
}
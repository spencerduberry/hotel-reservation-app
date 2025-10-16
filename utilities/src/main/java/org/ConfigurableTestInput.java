package org;

public interface ConfigurableTestInput {
    void enqueueInput(String value);
    void reset();
}
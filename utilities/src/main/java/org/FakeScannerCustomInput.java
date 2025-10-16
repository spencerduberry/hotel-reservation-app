package org;

import java.util.LinkedList;
import java.util.Queue;


public class FakeScannerCustomInput implements CustomInput, ConfigurableTestInput{
    private final Queue<String> inputQueue = new LinkedList<>();
    
    public String inputString(){
        String stringInput = "fakeString";
        return stringInput;
    }
    
    public int inputInt(){
        String nextInput = inputQueue.poll();
        if (nextInput == null) {
            throw new IllegalStateException("Test failed: System requested more input than was queued.");
        }
        return Integer.parseInt(nextInput);
    }
    @Override
    public void enqueueInput(String value) {
        this.inputQueue.offer(value);
    }
    @Override
    public void reset(){
        this.inputQueue.clear();
    }
    
}

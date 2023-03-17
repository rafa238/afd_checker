package com.afd.afdcheck;

import java.util.Map;
import java.util.TreeMap;


public class State {
    private String name;
    private Map<String, State> transitions;
    private boolean isAceptingState;
    
    public State(String name) {
        this.name = name;
        transitions = new TreeMap<>();
        this.isAceptingState = false;
    }
    
    public void addTransition(String simbol, State target) {
        transitions.put(simbol, target);
    }

    public State transition(String simbol) {
        return transitions.get(simbol);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsAceptingState() {
        return isAceptingState;
    }

    public void setIsAceptingState(boolean isAceptingState) {
        this.isAceptingState = isAceptingState;
    }
    
    
    @Override
    public String toString() {
        String str = "";
        if(this.isAceptingState) str += "*";
        str += name;
        return  str;
    }
    
    
}

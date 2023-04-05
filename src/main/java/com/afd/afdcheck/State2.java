package com.afd.afdcheck;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class State2 {
    private String name;
    //mapea Simbolo - estado
    private Map<String, Set<State2>> transitions;
    private boolean isAceptingState;
    
    public State2(String name) {
        this.name = name;
        transitions = new HashMap<>();
        this.isAceptingState = false;
    }
    
    public void addTransition(String simbol, State2 target) {
        if(target == null){
            Set<State2> set = new HashSet<>();
            transitions.put(simbol, set);
        } else if(transitions.containsKey(simbol)){
            Set<State2> set = transitions.get(simbol);
            set.add(target);
            transitions.put(simbol, set);
        } else {
            Set<State2> set = new HashSet<>();
            set.add(target);
            transitions.put(simbol, set);
        }
        
    }

    /*public State2 transition(String simbol) {
        return transitions.get(simbol);
    }*/

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

    public Map<String, Set<State2>> getTransitions() {
        return transitions;
    }
    
    @Override
    public String toString() {
        String str = "";
        if(this.isAceptingState) str += "*";
        str += name;
        return  str;
    }
}

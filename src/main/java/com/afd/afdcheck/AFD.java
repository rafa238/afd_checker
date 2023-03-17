package com.afd.afdcheck;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class AFD {
    private Set<State> states;
    private Set<String> alphabet;
    private Set<State> acceptedStates;
    private State initialState;

    public AFD() {
        states = new HashSet<>();
        alphabet = new HashSet<>();
        acceptedStates = new HashSet<>();
    }
    
    public Set<State> getStates() {
        return states;
    }
    
    public void addState(State[] states){
        this.states.addAll(Arrays.asList(states));
    }

    public Set<String> getAlphabet() {
        return alphabet;
    }

    public void addAlphabet(String[] alphabet){
        this.alphabet.addAll(Arrays.asList(alphabet));
    }
    
    public void addAcceptedState(String[] acceptedStates){
        if(this.states.isEmpty()) System.out.println("Agrega estados primero :(");
        for(String acState : acceptedStates){
            for(State state : this.states){
                if(acState.equals(state.getName())){
                    state.setIsAceptingState(true);
                    this.acceptedStates.add(state);
                }
            }
        }
    }
    
    public State getInitialState() {
        return initialState;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    @Override
    public String toString() {
        String str = "";
        str += ("Q: \n");
        for(State elem : this.states){
            str += (elem + " ");
        }
        str += "\n\n";
        str += ("Sigma: \n");
        for(String elem : this.alphabet){
            str += (elem + " ");
        }
        str += "\n\n";
        str += ("F: \n");
        for(State elem : this.acceptedStates){
            str += (elem.toString() + " ");
        }
        str+="\n";
        return str;
    }
}

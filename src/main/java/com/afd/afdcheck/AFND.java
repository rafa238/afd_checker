/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afd.afdcheck;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author PC
 */
public class AFND {
    private Set<State2> states;
    private Set<String> alphabet;
    private Set<State2> acceptedStates;
    private State2 initialState;

    public AFND() {
        states = new HashSet<>();
        alphabet = new HashSet<>();
        acceptedStates = new HashSet<>();
    }

    public void addState(State2[] states){
        this.states.addAll(Arrays.asList(states));
    }

    public void addAlphabet(String[] alphabet){
        this.alphabet.addAll(Arrays.asList(alphabet));
    }
    
    public void addAcceptedState(String[] acceptedStates){
        if(this.states.isEmpty()) System.out.println("Agrega estados primero :(");
        for(String acState : acceptedStates){
            State2 state = searchState(acState);
            state.setIsAceptingState(true);
            this.acceptedStates.add(state);
        }
    }
     
    public State2 searchState(String searchedId){
        for(State2 state : this.states){
            if(state.getName().equals(searchedId)){
                return state;
            }
        }
        throw new Error("No existe este estado!\n");
    }
    
    public State2 getInitialState() {
        return initialState;
    }

    public void setInitialState(State2 initialState) {
        this.initialState = initialState;
    }

    public Set<State2> getStates() {
        return states;
    }

    public Set<String> getAlphabet() {
        return alphabet;
    }

    public Set<State2> getAcceptedStates() {
        return acceptedStates;
    }

    @Override
    public String toString() {
        String str = "";
        str += ("Q: \n");
        for(State2 elem : this.states){
            str += (elem + " ");
        }
        str += "\n\n";
        str += ("Sigma: \n");
        for(String elem : this.alphabet){
            str += (elem + " ");
        }
        str += "\n\n";
        str += ("F: \n");
        for(State2 elem : this.acceptedStates){
            str += (elem.toString() + " ");
        }
        str+="\n";
        return str;
    }
}

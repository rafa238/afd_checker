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

    public void addState(State[] states){
        this.states.addAll(Arrays.asList(states));
    }

    public void addAlphabet(String[] alphabet){
        this.alphabet.addAll(Arrays.asList(alphabet));
    }
    
    public void addAcceptedState(String[] acceptedStates){
        if(this.states.isEmpty()) System.out.println("Agrega estados primero :(");
        for(String acState : acceptedStates){
            State state = searchState(acState);
            state.setIsAceptingState(true);
            this.acceptedStates.add(state);
        }
    }
    
    public boolean checkString(String strToTest){
        
        State auxState = initialState;
        System.out.println("Transiciones: \n");
        for(Character simbol : strToTest.toCharArray()){
            if(!this.alphabet.contains(simbol.toString())) throw new Error("No existe ese simbolo en el alfabeto del AFD");
            System.out.print(auxState.toString() + " ");
            auxState = auxState.transition(simbol.toString());
        }
        System.out.print("\n");
        return auxState.isIsAceptingState();
    }
    
    public State searchState(String searchedId){
        for(State state : this.states){
            if(state.getName().equals(searchedId)){
                return state;
            }
        }
        throw new Error("No existe este estado!\n");
    }
    
    public State getInitialState() {
        return initialState;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    public Set<State> getStates() {
        return states;
    }

    public Set<String> getAlphabet() {
        return alphabet;
    }

    public Set<State> getAcceptedStates() {
        return acceptedStates;
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

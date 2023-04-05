package com.afd.afdcheck;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/*
 *
 * Entrada:
 *   #Q #Sigma q0 #F
 *   estados(Q)
 *   alfabeto(Sigma)
 *   estados de aceptacion(F)
 *   trancisiones(Q) //una linea por estado
 *  
 */

public class AfnReader {
    private String path;
    
    public AfnReader(String relativePath) {
        this.path = System.getProperty("user.dir") + "/" + relativePath;
    }
    
    public AFND read(){
        AFND afnd = new AFND();
        try {
            //Leer archivo
            File file = new File(this.path);
            Scanner scanf = new Scanner(file);
            
            //Variables y estructuras necesarias para leer el archivo
            int Q = scanf.nextInt();
            int sigmaLength = scanf.nextInt();
            String initialState = scanf.next();
            int acceptedStatesSize = scanf.nextInt();
            
            State2[] states = new State2[Q];
            String[] acceptedStates = new String[acceptedStatesSize];
            String[] alphabet = new String[sigmaLength];
            Map<String, State2> name_state = new HashMap<>();
            
            //Obtener Estados
            for(int i=0; i<Q; i++){
                String stateName = scanf.next();
                State2 state = new State2(stateName);
                states[i] = state;
                name_state.put(stateName, state);
            }
            
            //Obtener alfabeto
            for(int i=0; i<sigmaLength; i++){
                String simbol = scanf.next();
                alphabet[i] = simbol;
            }
            
            //Obtener estados validos
            for(int i=0; i<acceptedStatesSize; i++){
                String acState = scanf.next();
                acceptedStates[i] = acState;
            }
            
            //obtener Transiciones (Q*Sigma)
            for(int i=0; i<Q; i++){
                //row -> states[i]
                State2 currentState = states[i];
                
                for(int j=0; j<sigmaLength; j++){
                    String simbol = alphabet[j];
                    int numTransitions = scanf.nextInt();
                    for(int z=0; z<numTransitions; z++){
                        // transition
                        String transitionState = scanf.next();
                        if(name_state.containsKey(transitionState)){
                            currentState.addTransition(simbol, name_state.get(transitionState));
                        } else {
                            currentState.addTransition(simbol, null);
                        }
                        
                    }
                }
            }
            //establecemos los estados y alfabeto al AFD
            afnd.addState(states);
            afnd.addAlphabet(alphabet);
            afnd.addAcceptedState(acceptedStates);
            afnd.setInitialState(name_state.get(initialState));
        } catch (Exception ex) {
            System.out.println("Error al leer el AFD archivo\n" + ex);
        }
        return afnd;
    }
    
    public void tranformAFNDToAFD(AFND afnd){
        AFD afd = new AFD();
        Queue<Set<State2>> q = new LinkedList<>();
        Set<Set<State2>> visited = new HashSet<>();
        
        State2 initialState = afnd.getInitialState();
        
        for(Entry<String, Set<State2>> t : initialState.getTransitions().entrySet()){
            //System.out.println(t.getKey()+ ": " + t.getValue());
            q.add(t.getValue());
        }
        /*for(Set<State2> s: q){
            System.out.print(s);
            visited.add(s);
        }
        System.out.println("");*/
        while(!q.isEmpty()){
            Set<State2> curr = q.poll();
            if(visited.contains(curr)) continue;
            visited.add(curr);
            System.out.print(curr + " -> ");
            for(String simbol : afnd.getAlphabet()){
                System.out.print(simbol + ": ");
                Set<State2> res = new HashSet<>();
                for(State2 unions : curr){             
                    Set<State2> currSimbolSet = unions.getTransitions().get(simbol);
                    res.addAll(currSimbolSet);
                }
                System.out.print(res);
                
                q.add(res);
            }
            System.out.println("");
            
        }
        /*for(State2 s : afnd.getStates()){
            System.out.println(s.getName());
            for(Entry<String, Set<State2>> t : s.getTransitions().entrySet()){
                System.out.println(t.getKey()+ ": " + t.getValue() );
            }
        }*/
    }
}

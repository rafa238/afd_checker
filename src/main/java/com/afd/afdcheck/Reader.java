package com.afd.afdcheck;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 *
 * Entrada:
 *   Q Sigma q0 F
 *   estados(Q)
 *   alfabeto(Sigma)
 *   estados de aceptacion(F)
 *   lenguaje(Q*Sigma)
 * 
 */
public class Reader {
    private String path;
    
    public Reader(String relativePath) {
        this.path = System.getProperty("user.dir") + "/" + relativePath;
    }
    
    public AFD readAutomata(){
        AFD automata = new AFD();
        try {
            //Leer archivo
            File file = new File(this.path);
            Scanner scanf = new Scanner(file);
            
            //Variables y estructuras necesarias para leer el archivo
            int Q = scanf.nextInt();
            int sigmaLength = scanf.nextInt();
            String initialState = scanf.next();
            int acceptedStatesSize = scanf.nextInt();
            
            State[] states = new State[Q];
            String[] acceptedStates = new String[acceptedStatesSize];
            String[] alphabet = new String[sigmaLength];
            Map<String, State> name_state = new HashMap<>();
            
            //Obtener Estados
            for(int i=0; i<Q; i++){
                String stateName = scanf.next();
                State state = new State(stateName);
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
                for(int j=0; j<sigmaLength; j++){
                    //column alphabet
                    String simbol = alphabet[j];
                    
                    // transition
                    String targetStateName = scanf.next();
                    states[i].addTransition(simbol, name_state.get(targetStateName));
                }
            }
            
            //establecemos los estados y alfabeto al AFD
            automata.addState(states);
            automata.addAlphabet(alphabet);
            automata.addAcceptedState(acceptedStates);
            automata.setInitialState(name_state.get(initialState));
        } catch (Exception ex) {
            System.out.println("Error al leer el archivo\n" + ex);
        }
        
        /*for(State s : automata.getStates()){
            System.out.println(s.getName() + ": ");
            for(String str : automata.getAlphabet()){
                System.out.print(str + ": " + s.transition(str) + " ");
            }
            System.out.println("");
        }*/
        //System.out.println(automata.toString());
        return automata;
    }
}

package com.afd.afdcheck;

public class ComprobarAFD {
    public static void main(String[] args) {
        Reader read = new Reader("automata.txt");
        
        read.readAutomata();
    }
    
}

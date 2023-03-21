package com.afd.afdcheck;

public class ComprobarAFD {
    public static void main(String[] args) {
        Reader AFDreader = new Reader("automata.txt");
        AFD afd = AFDreader.readAutomata();
        boolean ans = afd.checkString("00010");
        if(ans) {
            System.out.println("Entrada aceptada");
        } else {
            System.out.println("Entrada invalida");
        }
    }
}

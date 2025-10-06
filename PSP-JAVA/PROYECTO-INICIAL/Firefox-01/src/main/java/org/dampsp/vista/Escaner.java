package org.dampsp.vista;

import java.util.Scanner;

public class Escaner {
    public static String pedirDato(){
        Scanner escaner =  new Scanner(System.in);
        return escaner.nextLine();
    }
    public static int pedirInt(){
        Scanner escaner =  new Scanner(System.in);
        return escaner.nextInt();
    }
}

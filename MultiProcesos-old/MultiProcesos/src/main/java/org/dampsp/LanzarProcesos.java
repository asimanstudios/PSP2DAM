package org.dampsp;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class LanzarProcesos{
    public static void main(String[] args) {

    }
    public static void  lanzarFirefox() {
        int codigoSalida;

        try {
            // Construímos el proceso Firefox
            //ProcessBuilder pb = new ProcessBuilder("notepad");
            ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Mozilla Firefox\\firefox.exe");

            // Iniciar el proceso
            Process proceso = pb.start();

            // Esperar a que termine el proceso y obtener el código de salida
            codigoSalida = proceso.waitFor();
            System.out.println("Salida: " + codigoSalida);
            // podríamos forzar la destrucción del proceso
            proceso.destroy();

        }catch (IOException | InterruptedException e ) {
            e.printStackTrace();
        }
    }

    public String leerDeConsola(String mensaje) {
        Scanner sc = new Scanner(System.in);
        String lectura;

        System.out.println(mensaje);
        lectura = sc.nextLine();

        return lectura;
    }

    public String leerRutaDeEjecutable(){
        String rutaEjecutable;

        rutaEjecutable = leerDeConsola("Ingrese la ruta del ejecutable que desea ejecutar: ");

        return rutaEjecutable;
    }

    public String[] leerArgumentosDesdeConsola() {
        String[] argumentos;
        String cadenaArgumentos;

        cadenaArgumentos = leerDeConsola("Dime los argumentos separados por punto y coma: ");

        argumentos = cadenaArgumentos.split(";");

        return argumentos;
    }

    private static ArrayList<String> array2arrayList(String ejecutable, String[] argumentos){
        ArrayList<String> resultado = new ArrayList<>();

        resultado.add(ejecutable);

        for (int i = 0; i < argumentos.length; i++) {
            resultado.add(argumentos[i]);
        }

        return resultado;
    }

    public static void  lanzarAplicacionConArgumentos( String rutaEjecutable , String[] argumentos ) {
        int codigoSalida;
        ArrayList<String> comandoYargumentos;

        comandoYargumentos = array2arrayList(rutaEjecutable, argumentos);

        try {
            // Construímos el proceso Firefox
            //ProcessBuilder pb = new ProcessBuilder("notepad");
            ProcessBuilder pb = new ProcessBuilder(comandoYargumentos);

            // Iniciar el proceso
            Process proceso = pb.start();

            // Esperar a que termine el proceso y obtener el código de salida
            codigoSalida = proceso.waitFor();
            System.out.println("Salida: " + codigoSalida);
            // podríamos forzar la destrucción del proceso
            proceso.destroy();

        }catch (IOException | InterruptedException e ) {
            e.printStackTrace();
        }
    }

    public void  lanzarAplicacionConArgumentos( ) {
        int codigoSalida;
        String[] comandoYargumentos;

        comandoYargumentos = leerArgumentosDesdeConsola();

        try {
            // Construímos el proceso Firefox
            //ProcessBuilder pb = new ProcessBuilder("notepad");
            ProcessBuilder pb = new ProcessBuilder(comandoYargumentos);

            // Iniciar el proceso
            Process proceso = pb.start();

            // Esperar a que termine el proceso y obtener el código de salida
            codigoSalida = proceso.waitFor();
            System.out.println("Salida: " + codigoSalida);
            // podríamos forzar la destrucción del proceso
            proceso.destroy();

        }catch (IOException | InterruptedException e ) {
            e.printStackTrace();
        }
    }

}
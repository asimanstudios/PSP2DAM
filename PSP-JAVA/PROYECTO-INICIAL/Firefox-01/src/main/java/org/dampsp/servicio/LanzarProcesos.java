package org.dampsp.servicio;

import org.dampsp.vista.Consola;

import java.io.IOException;
import java.util.ArrayList;

public class LanzarProcesos {
    public static void lanzarProceso(String ruta){
        int codigoSalida;
        try{
            // Construimos el proceso. Del navegador.
            ProcessBuilder processBuilder = new ProcessBuilder(ruta);
            // Inicializar proceso
            Process proceso = processBuilder.start();
            // Esperar a que termine el proceso y obtener el código de salida.
            codigoSalida = proceso.waitFor();
            System.out.println(codigoSalida);
            // Forzar la destrucción del proceso
            proceso.destroy();
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
//lanzar un proceso con argumentos en el comando de ejecución
    public static void lanzarProcesoArgumentos(String ruta, String[] argumentos){
        int codigoSalida;
        try{
            // Construimos el proceso. Del navegador.
            ProcessBuilder processBuilder = new ProcessBuilder(ruta);
            // Inicializar proceso
            Process proceso = processBuilder.start();
            // Esperar a que termine el proceso y obtener el código de salida.
            codigoSalida = proceso.waitFor();
            System.out.println(codigoSalida);
            // Forzar la destrucción del proceso
            proceso.destroy();
        }catch (IOException | InterruptedException e){
            Consola.mostrarError(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void lanzarBrave(){
        int codigoSalida;
        try{
            // Construimos el proceso. Del navegador.
            ProcessBuilder processBuilder = new ProcessBuilder("C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe");
            // Inicializar proceso
            Process proceso = processBuilder.start();
            // Esperar a que termine el proceso y obtener el código de salida.
            codigoSalida = proceso.waitFor();
            System.out.println(codigoSalida);
            // Forzar la destrucción del proceso
            proceso.destroy();
        }catch (IOException | InterruptedException e){
            Consola.mostrarError(e.getMessage());
            e.printStackTrace();
        }
    }

    // lectura de argumentos para ejecutarlos con el comando
    public static ArrayList<String> leerArgumentos(){
        ArrayList<String> argumentos = new ArrayList<>();
        String cadenaArgumentos;
        cadenaArgumentos= Consola.leerConsola("Introduce los argumentos separados por punto y coma;");
        cadenaArgumentos.split(";");
        //argumentos.add(cadenaArgumentos.split(";"))

        return argumentos;
    }
}

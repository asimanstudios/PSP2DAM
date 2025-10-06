package org.dampsp.servicio;

import java.io.IOException;

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
            e.printStackTrace();
        }
    }
}

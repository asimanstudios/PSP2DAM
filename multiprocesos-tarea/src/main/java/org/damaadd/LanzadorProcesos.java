package org.damaadd;
import java.io.File;
import java.io.IOException;

public class LanzadorProcesos {

    public void ejecutarDir(String carpeta) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "dir", carpeta);
            processBuilder.redirectOutput(new File("datos/salida_ls.txt"));
            processBuilder.redirectErrorStream(true); // Redirigir errores a salida estándar

            Process proceso = processBuilder.start();
            int codigoSalida = proceso.waitFor();
            System.out.println("Tarea 1 - ejecutar dir. Código de salida: " + codigoSalida);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void ejecutarPingGoogle() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "ping", "-n", "6", "google.com");
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

            Process proceso = processBuilder.start();
            int codigoSalida = proceso.waitFor();
            System.out.println("Tarea 2 - ejecutar ping. Código de salida: " + codigoSalida);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void automatizarSecuencia() {
        try {
            System.out.println("Tarea 3 - ejecutar secuencia.");

            // Comando 1: echo "Inicio de la tarea"
            ProcessBuilder processBuilder1 = new ProcessBuilder("cmd", "/c", "echo Inicio de la tarea");
            processBuilder1.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            Process proceso1 = processBuilder1.start();
            proceso1.waitFor();

            // Comando 2: timeout /t 3 > nul
            ProcessBuilder processBuilder2 = new ProcessBuilder("cmd", "/c", "timeout /t 3 > null");
            processBuilder2.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            Process proceso2 = processBuilder2.start();
            proceso2.waitFor();

            // Comando 3: echo %date% %time%
            ProcessBuilder processBuilder3 = new ProcessBuilder("cmd", "/c", "echo %date% %time%");
            processBuilder3.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            Process proceso3 = processBuilder3.start();
            proceso3.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void ejecutarPingsParalelos() {
        try {
            ProcessBuilder processBuilder1 = new ProcessBuilder("cmd", "/c", "ping", "localhost");
            processBuilder1.redirectOutput(new File("datos/ping_localhost.txt"));
            processBuilder1.redirectErrorStream(true);

            ProcessBuilder processBuilder2 = new ProcessBuilder("cmd", "/c", "ping", "127.0.0.1");
            processBuilder2.redirectOutput(new File("datos/ping_127.txt"));
            processBuilder2.redirectErrorStream(true);

            Process proceso1 = processBuilder1.start();
            Process proceso2 = processBuilder2.start();

            int salida1 = proceso1.waitFor();
            int salida2 = proceso2.waitFor();

            System.out.println("Tarea 4 - ejecucion de pings paralelos. Salidas: " + salida1 + ", " + salida2);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void forzarError() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "comando_inexistente");
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

            Process proceso = processBuilder.start();
            int codigoSalida = proceso.waitFor();
            System.out.println("Tarea 5: Codigo invalido en base a comando inexistente:  " + codigoSalida);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error para control de errores: " + e.getMessage());
        }
    }
}

package org.dampsp;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Programa lanzador de procesos externos usando ProcessBuilder.
 * Cumple con la actividad de PSP: ejecución de comandos Windows, redirección de I/O, secuencial/paralelo y manejo de errores.
 */
public class LanzadorComandos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Lanzador de Procesos desde Java ===");

        try {
            // Tarea 1: Ejecutar 'dir' en una carpeta dada y guardar en salida_ls.txt
            System.out.println("\n1. Ejecutando 'dir' en carpeta especificada...");
            String carpeta = sc.nextLine(); // Lee la carpeta del usuario (e.g., "C:\\Users")
            if (carpeta.isEmpty()) carpeta = "."; // Por defecto, carpeta actual
            ejecutarDirConSalidaArchivo(carpeta, "salida_ls.txt");

            // Tarea 2: Ejecutar 'ping -n 6 google.com' y mostrar en consola
            System.out.println("\n2. Ejecutando 'ping -n 6 google.com'...");
            ejecutarPingGoogle();

            // Tarea 3: Automatizar ejecución secuencial de comandos
            System.out.println("\n3. Ejecutando comandos secuenciales...");
            automatizarSecuencial();

            // Tarea 4: Ejecutar procesos en paralelo (ping localhost y ping 127.0.0.1)
            System.out.println("\n4. Ejecutando pings en paralelo...");
            ejecutarPingsParalelo();

            // Tarea 5: Ejemplo de control de errores (intentar un comando inválido)
            System.out.println("\n5. Probando control de errores con comando inválido...");
            probarError("comando_invalido_que_no_existe");

        } catch (Exception e) {
            System.err.println("Error general en main: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sc.close();
        }

        System.out.println("\n=== Fin del programa ===");
    }

    private static void ejecutarDirConSalidaArchivo(String carpeta, String archivoSalida) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "dir", carpeta);

            File archivo = new File(archivoSalida);
            pb.redirectOutput(archivo);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            Process proceso = pb.start();
            int codigoSalida = proceso.waitFor();

            if (codigoSalida == 0) {
                System.out.println("Salida de 'dir' guardada en: " + archivo.getAbsolutePath());
            } else {
                System.err.println("Error en 'dir': Código de salida " + codigoSalida);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error ejecutando 'dir': " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static void ejecutarPingGoogle() {
        try {
            ProcessBuilder pb = new ProcessBuilder("ping", "-n", "6", "google.com");
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            Process proceso = pb.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
            }
            int codigoSalida = proceso.waitFor();
            if (codigoSalida != 0) {
                System.err.println("Error en ping: Código de salida " + codigoSalida);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error ejecutando ping: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void automatizarSecuencial() {
        String[] comandos = {
                "cmd.exe /c echo Inicio de la tarea",
                "cmd.exe /c timeout /t 3 > nul",
                "cmd.exe /c echo %date% %time%"
        };
        for (String cmd : comandos) {
            try {
                ProcessBuilder pb = new ProcessBuilder(cmd.split(" ")); // Split simple para argumentos
                pb.redirectError(ProcessBuilder.Redirect.INHERIT);
                Process proceso = pb.start();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        System.out.println(linea);
                    }
                }
                int codigoSalida = proceso.waitFor();
                if (codigoSalida != 0) {
                    System.err.println("Error en comando '" + cmd + "': Código " + codigoSalida);
                }
                Thread.sleep(500);
            } catch (IOException | InterruptedException e) {
                System.err.println("Error en comando '" + cmd + "': " + e.getMessage());
            }
        }
    }

    private static void ejecutarPingsParalelo() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Integer> future1 = executor.submit(() -> ejecutarPingYGuardar("localhost", "ping_localhost.txt"));
        Future<Integer> future2 = executor.submit(() -> ejecutarPingYGuardar("127.0.0.1", "ping_127.txt"));

        try {
            // Esperar a que ambos terminen
            int codigo1 = future1.get(10, TimeUnit.SECONDS); // Timeout para evitar hangs
            int codigo2 = future2.get(10, TimeUnit.SECONDS);

            System.out.println("Ping localhost terminó con código: " + codigo1);
            System.out.println("Ping 127.0.0.1 terminó con código: " + codigo2);

        } catch (Exception e) {
            System.err.println("Error en ejecución paralela: " + e.getMessage());
            future1.cancel(true);
            future2.cancel(true);
        } finally {
            executor.shutdown();
        }
    }

    private static int ejecutarPingYGuardar(String host, String archivoSalida) {
        try {
            ProcessBuilder pb = new ProcessBuilder("ping", "-n", "4", host); // 4 pings para brevedad
            File archivo = new File(archivoSalida);
            pb.redirectOutput(archivo);
            pb.redirectError(archivo); // stderr también al archivo

            Process proceso = pb.start();
            return proceso.waitFor();

        } catch (IOException | InterruptedException e) {
            System.err.println("Error en ping a " + host + ": " + e.getMessage());
            return -1;
        }
    }

    /**
     * Tarea 5 (Extra): Prueba control de errores ejecutando un comando inválido.
     * @param comandoInvalido Nombre del comando que fallará.
     */
    private static void probarError(String comandoInvalido) {
        try {
            ProcessBuilder pb = new ProcessBuilder(comandoInvalido);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);

            Process proceso = pb.start();
            int codigoSalida = proceso.waitFor();

            if (codigoSalida != 0) {
                System.err.println("¡Error detectado! El comando '" + comandoInvalido + "' falló con código: " + codigoSalida);
                System.err.println("Esto ocurre cuando el comando no existe o hay un problema en la ejecución.");
            } else {
                System.out.println("Comando ejecutado exitosamente (inesperado).");
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Excepción al intentar comando inválido: " + e.getMessage());
            // e.printStackTrace(); // Comentado para no saturar salida en pruebas
        }
    }

    // Métodos de tu código original adaptados (no usados en main, pero útiles para extensiones)

    /**
     * Convierte array de strings a ArrayList (de tu código base).
     */
    private static ArrayList<String> array2arrayList(String ejecutable, String[] argumentos) {
        ArrayList<String> resultado = new ArrayList<>();
        resultado.add(ejecutable);
        if (argumentos != null) {
            resultado.addAll(Arrays.asList(argumentos));
        }
        return resultado;
    }

    /**
     * Lee ruta de ejecutable desde consola (de tu código base).
     */
    private static String leerRutaDeEjecutable() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la ruta del ejecutable que desea ejecutar: ");
        return sc.nextLine();
    }

    /**
     * Lee argumentos desde consola (de tu código base, separados por ';').
     */
    private static String[] leerArgumentosDesdeConsola() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Dime los argumentos separados por punto y coma: ");
        String cadenaArgumentos = sc.nextLine();
        return cadenaArgumentos.split(";");
    }

    /**
     * Lanza una aplicación con argumentos (adaptado de tu código base, para uso general).
     */
    public static void lanzarAplicacionConArgumentos(String rutaEjecutable, String[] argumentos) {
        ArrayList<String> comandoYargumentos = array2arrayList(rutaEjecutable, argumentos);
        try {
            ProcessBuilder pb = new ProcessBuilder(comandoYargumentos);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);

            Process proceso = pb.start();
            int codigoSalida = proceso.waitFor();
            System.out.println("Aplicación terminada con código: " + codigoSalida);

            if (codigoSalida != 0) {
                System.err.println("¡Advertencia! La aplicación falló.");
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error lanzando aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

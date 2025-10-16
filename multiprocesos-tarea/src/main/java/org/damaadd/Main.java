package org.damaadd;

public class Main {
    public static void main(String[] args) {
        LanzadorProcesos lanzador = new LanzadorProcesos();

        // 1: Ejecutar dir en una carpeta y guardar en salida_ls.txt
        lanzador.ejecutarDir(".");

        // 2: Ejecutar ping -n 6 google.com y mostrar salida
        lanzador.ejecutarPingGoogle();

        // 3: Automatizar comandos
        lanzador.automatizarSecuencia();

        // 4: Procesos en paralelo
        lanzador.ejecutarPingsParalelos();

        // 5: Control de errores (ejemplo comando inválido)
        lanzador.forzarError();
    }
}
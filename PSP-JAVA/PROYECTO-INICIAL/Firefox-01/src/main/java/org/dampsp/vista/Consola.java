package org.dampsp.vista;

import org.dampsp.servicio.LanzarProcesos;

public class Consola {
    public static void menu(){
        System.out.println("==========================================");
        System.out.println("            🚀 MENÚ PRINCIPAL             ");
        System.out.println("==========================================");
        System.out.println("  [1] 🗣️ Abrir proceso");
        System.out.println("  [2] 🌐 Abrir Brave");
        System.out.println("  [3] ❌ Salir");
        System.out.println("==========================================");
        seleccionMenu();
    }

    public static void seleccionMenu(){
        int opcion = Escaner.pedirInt();
        switch (opcion) {
            case 1:
                LanzarProcesos.lanzarProceso(leerConsola("Introduce la ruta de tu ejecutable: "));
                break;

            case 2:
                mostrarInfo("Iniciando Brave: ");
                LanzarProcesos.lanzarBrave();
                break;

            case 3:
                System.out.println("👋 ¡Hasta luego!\n");
                break;

            default:
                System.out.println("⚠️  Opción no válida. Intente nuevamente.\n");
        }
    }

    public static void mostrarInfo(String mensaje){
        System.out.println("[¡] " + mensaje);
    }

    public static void mostrarError(String mensaje){
        System.out.println("[❗] " + mensaje);
    }

    public static String leerConsola(String mensaje){
        String dato;
        System.out.println(mensaje);
        dato = Escaner.pedirDato();
        return dato;
    }

}

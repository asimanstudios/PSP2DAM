package dam.ejercicios.matrices.controlador;

import dam.ejercicios.matrices.servicio.Servicios;

public class Controlador {
    public static void iniciar() {
        Servicios.inicializar();
        int[][] resultado = Servicios.Sumar(Servicios.matrizA, Servicios.matrizB);
        Servicios.imprimir(resultado);
    }
}

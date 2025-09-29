package dam.ejercicios.matrices.servicio;

public class Servicios {
    public static int[][] matrizA;
    public static int[][] matrizB;
    public static int[][] resultado;

    public static void inicializar() {
        matrizA = new int[][]{{2, 3}, {6, 7}};
        matrizB = new int[][]{{1, 8}, {4, 5}};
        resultado = new int[2][2];
    }

    public static int[][] Sumar(int[][] matrizA, int[][] matrizB) {
        int[][] resultado = new int[2][2];
        // RECORRER ARRAYS Y SUMAR LOS VALORES RECORRIDOS
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                resultado[i][j] = matrizA[i][j] + matrizB[i][j];
            }
        }
        return resultado;
    }

    public static void imprimir(int[][] resultado) {
        // RECORRER EL ARRAY SUMADO AL I RECORRE MIENTRAS SEA MENOR DE 2 DADO QUE SERIAN 2 FILAS DE 2 VALORES Y DESPUES SE RECORRE J QUE SERIA LA SEGUNDA FILA Y SE HACE UN PRINT DE DICHOS VALORES.
        System.out.println("Resultado de la suma de matrices:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(resultado[i][j] + " ");
            }
            System.out.println(); // este print se usa solo para generar un salto y que se vean mejor las 2 filas de 2 valores.
        }
    }
}

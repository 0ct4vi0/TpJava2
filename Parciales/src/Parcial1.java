import java.util.Random;
import java.util.Scanner;

public class Parcial1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            // Paso 1: Pedir la dimensión de la matriz
            int n = pedirDimension(scanner);

            // Paso 2: Crear y llenar la matriz
            int[][] matriz = crearYLlenarMatriz(n, scanner);

            // Paso 3: Mostrar la matriz resultante
            mostrarMatriz(matriz);

            // Paso 4: Obtener valores centrales
            int[] valoresCentrales = obtenerValoresCentrales(matriz);

            // Paso 5: Mostrar la matriz de valores centrales
            mostrarMatrizUnidimensional(valoresCentrales);

            // Paso 6: Ordenar la matriz de valores centrales
            ordenarMatrizBurbuja(valoresCentrales);

            // Paso 7: Mostrar la matriz ordenada
            mostrarMatrizUnidimensional(valoresCentrales);

            // Paso 8: Calcular promedio de matrices
            double promedio = calcularPromedioDeMatrices(matriz, valoresCentrales);

            // Paso 9: Mostrar el promedio y preguntar si desea continuar
            System.out.println("El promedio de ambas matrices es: " + promedio);
            continuar = preguntarContinuar(scanner);
        }

        scanner.close();
    }

    // Paso 1: Pedir la dimensión de la matriz
    public static int pedirDimension(Scanner scanner) {
        int n;
        do {
            System.out.print("Ingrese la dimensión de la matriz (número impar entre 3 y 15): ");
            n = scanner.nextInt();
        } while (n < 3 || n > 15 || n % 2 == 0);
        return n;
    }

    // Paso 2: Crear y llenar la matriz
    public static int[][] crearYLlenarMatriz(int n, Scanner scanner) {
        int[][] matriz = new int[n][n];
        Random random = new Random();

        // Llenar las filas excepto la última
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = random.nextInt(90) + 10;
            }
        }

        // Llenar la última fila manualmente
        for (int j = 0; j < n; j++) {
            int valor;
            do {
                System.out.print("Ingrese un valor para la posición [" + (n - 1) + "][" + j + "] (entre 10 y 99): ");
                valor = scanner.nextInt();
            } while (valor < 10 || valor > 99);
            matriz[n - 1][j] = valor;
        }

        return matriz;
    }

    // Paso 3: Mostrar la matriz resultante
    public static void mostrarMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int valor : fila) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }

    // Paso 4: Obtener valores centrales
    public static int[] obtenerValoresCentrales(int[][] matriz) {
        int n = matriz.length;
        int centro = n / 2;
        int[] valoresCentrales = new int[9];
        int index = 0;

        for (int i = centro - 1; i <= centro + 1; i++) {
            for (int j = centro - 1; j <= centro + 1; j++) {
                valoresCentrales[index++] = matriz[i][j];
            }
        }

        return valoresCentrales;
    }

    // Paso 5: Mostrar la matriz de valores centrales
    public static void mostrarMatrizUnidimensional(int[] matriz) {
        for (int valor : matriz) {
            System.out.print(valor + " ");
        }
        System.out.println();
    }

    // Paso 6: Ordenar la matriz de valores centrales
    public static void ordenarMatrizBurbuja(int[] matriz) {
        int n = matriz.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (matriz[j] > matriz[j + 1]) {
                    int temp = matriz[j];
                    matriz[j] = matriz[j + 1];
                    matriz[j + 1] = temp;
                }
            }
        }
    }

    // Paso 8: Calcular promedio de matrices
    public static double calcularPromedioDeMatrices(int[][] matriz, int[] matrizValoresCentral) {
        int sumaBidimensional = 0;
        int cantidadElementosBidimensional = matriz.length * matriz.length;
        for (int[] fila : matriz) {
            for (int valor : fila) {
                sumaBidimensional += valor;
            }
        }
        double promedioBidimensional = (double) sumaBidimensional / cantidadElementosBidimensional;

        int sumaUnidimensional = 0;
        int cantidadElementosUnidimensional = matrizValoresCentral.length;
        for (int valor : matrizValoresCentral) {
            sumaUnidimensional += valor;
        }
        double promedioUnidimensional = (double) sumaUnidimensional / cantidadElementosUnidimensional;

        System.out.println("La suma total de la matriz bidimensional es: " + sumaBidimensional);
        System.out.println("La cantidad de elementos son: " + cantidadElementosBidimensional);
        System.out.println("El promedio de la matriz bidimensional es: " + promedioBidimensional);
        System.out.println("La suma total de la matriz unidimensional es: " + sumaUnidimensional);
        System.out.println("La cantidad de elementos son: " + cantidadElementosUnidimensional);
        System.out.println("El promedio de la matriz unidimensional es: " + promedioUnidimensional);

        return (promedioBidimensional + promedioUnidimensional) / 2;
    }

    // Paso 9: Preguntar si desea continuar
    public static boolean preguntarContinuar(Scanner scanner) {
        System.out.print("Desea iniciar nuevamente la ejecución del programa? (SI/NO): ");
        String respuesta = scanner.next();
        return respuesta.equalsIgnoreCase("SI");
    }
}

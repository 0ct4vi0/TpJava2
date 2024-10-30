import java.util.Scanner;
//Parcial2
public class GeneraPC {
    private static String[][] componentesPc = {
            {"AAA", "Placa Madre", "20000", "S"},
            {"BBB", "Procesador", "25000", "S"},
            {"CCC", "Memoria RAM", "5000", "S"},
            {"DDD", "Placa de Red", "3000", "N"},
            {"EEE", "Disco Rigido SSD", "22000", "S"},
            {"FFF", "Placa de Video", "42000", "N"},
            {"GGG", "Monitor Led 21", "32000", "N"},
            {"HHH", "Monitor Led 25", "41000", "N"},
            {"JJJ", "Kit Teclado - Mouse", "9000", "N"},
            {"KKK", "Gabinete", "6500", "S"},
            {"LLL", "Fuente Alimentación", "6500", "S"},
            {"MMM", "Placa de Sonido", "16500", "N"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Computadora computadora = new Computadora();

        // a) Solicitar marca, modelo y código de barras
        System.out.print("Ingrese la marca de la computadora: ");
        computadora.setMarca(scanner.nextLine());

        System.out.print("Ingrese el modelo de la computadora: ");
        computadora.setModelo(scanner.nextLine());

        long codigoBarras;
        do {
            System.out.print("Ingrese el código de barras (entre 7 y 15 caracteres): ");
            codigoBarras = Long.parseLong(scanner.nextLine());
        } while (String.valueOf(codigoBarras).length() < 7 || String.valueOf(codigoBarras).length() > 15);
        computadora.setCodigoBarras(codigoBarras);

        // b) Solicitar cantidad de componentes
        int cantidadComponentes;
        do {
            System.out.print("Ingrese la cantidad de componentes (entre 5 y 12): ");
            cantidadComponentes = Integer.parseInt(scanner.nextLine());
        } while (cantidadComponentes < 5 || cantidadComponentes > 12);

        // c) Inicializar el atributo componentes
        computadora.inicializarComponentes(cantidadComponentes);

        // d) Solicitar los componentes
        System.out.println("Componentes de la Computadora");
        for (int i = 0; i < cantidadComponentes; i++) {
            String codigo;
            do {
                System.out.print("Ingrese el código del componente: ");
                codigo = scanner.nextLine();
                if (!existeComponente(codigo)) {
                    System.out.println("El código ingresado es incorrecto");
                } else if (componenteYaAgregado(computadora.getComponentes(), codigo)) {
                    System.out.println("El componente ya fue agregado anteriormente");
                } else {
                    String[] componente = obtenerComponente(codigo);
                    computadora.agregarComponente(componente, i);
                    break;
                }
            } while (true);
        }

        // e) Verificar componentes obligatorios
        boolean todosObligatoriosAgregados = verificarComponentesObligatorios(computadora.getComponentes());
        if (!todosObligatoriosAgregados) {
            computadora.setPorcentajeAumento(20);
            System.out.println("Atención, 1 o más de los componentes obligatorios no fueron agregados, por este motivo se cobrara un recargo del 20%");
        }

        // f) Calcular monto final
        double precioTotal = calcularPrecioTotal(computadora.getComponentes());
        computadora.setPrecioTotal(precioTotal);
        double recargo = precioTotal * (computadora.getPorcentajeAumento() / 100);
        double montoFinal = precioTotal + recargo;
        computadora.setMontoFinal(montoFinal);

        // g) Mostrar resultado final
        System.out.println("La computadora especificada es:");
        System.out.println("Marca: " + computadora.getMarca());
        System.out.println("Modelo: " + computadora.getModelo());
        System.out.println("Código de Barras: " + computadora.getCodigoBarras());
        System.out.println("Año: 2023");
        System.out.println("Código Ítem Denominación Precio");
        for (String[] componente : computadora.getComponentes()) {
            if (componente != null) {
                System.out.println(componente[0] + " " + componente[1] + " " + componente[2]);
            }
        }
        System.out.println("Total Componentes: " + precioTotal);
        System.out.println("Recargo: " + recargo);
        System.out.println("Monto Final: " + montoFinal);
    }

    private static boolean existeComponente(String codigo) {
        for (String[] componente : componentesPc) {
            if (componente[0].equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    private static String[] obtenerComponente(String codigo) {
        for (String[] componente : componentesPc) {
            if (componente[0].equals(codigo)) {
                return componente;
            }
        }
        return null;
    }

    private static boolean componenteYaAgregado(String[][] componentes, String codigo) {
        for (String[] componente : componentes) {
            if (componente != null && componente[0].equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    private static boolean verificarComponentesObligatorios(String[][] componentes) {
        String[] codigosObligatorios = {"AAA", "BBB", "CCC", "EEE", "KKK", "LLL"};
        for (String codigo : codigosObligatorios) {
            boolean encontrado = false;
            for (String[] componente : componentes) {
                if (componente != null && componente[0].equals(codigo)) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                return false;
            }
        }
        return true;
    }

    private static double calcularPrecioTotal(String[][] componentes) {
        double total = 0;
        for (String[] componente : componentes) {
            if (componente != null) {
                total += Double.parseDouble(componente[2]);
            }
        }
        return total;
    }
}

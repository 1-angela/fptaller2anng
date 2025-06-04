import java.util.Scanner;
import java.util.Random;

public class fptaller2anng {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Random rand = new Random();

            // Variables
            String id, tipoBoleto = "", ruta = "", accion, horaReserva = "", horaCancelacion = "";
            double precioBase, pesoEquipaje, precioFinal, multa = 0.20;
            int asientosDisponibles = rand.nextInt(251) + 50; // Entre 50 y 300
            double pesoMaximoEquipaje = 23.0;
            String horaLimite = "19:29";
            boolean aplicaMulta = false;

            // Entrada de datos del pasajero
            System.out.println("Ingrese los datos del pasajero (Nombre, Cédula, Edad, Nacionalidad):");
            id = sc.nextLine();

            // Mostrar asientos disponibles
            System.out.println("Actualmente hay " + asientosDisponibles + " asientos disponibles.");

            // Tipo de boleto
            System.out.println("Seleccione el tipo de boleto (1, 2 o 3):");
            int tipo = sc.nextInt();
            sc.nextLine(); // limpiar buffer
            if (tipo == 1) {
                tipoBoleto = "1";
                ruta = "Ruta 1";
            } else if (tipo == 2) {
                tipoBoleto = "2";
                ruta = "Ruta 2";
            } else if (tipo == 3) {
                tipoBoleto = "3";
                ruta = "Ruta 3";
            }

            // Precio base del boleto
            System.out.println("Ingrese el precio base del boleto en euros:");
            precioBase = sc.nextDouble();
            sc.nextLine(); // limpiar buffer

            // Límite de peso de equipaje
            System.out.println("El límite de peso de equipaje permitido es de " + pesoMaximoEquipaje + " kg.");
            System.out.println("Ingrese el peso del equipaje en kg:");
            pesoEquipaje = sc.nextDouble();
            sc.nextLine(); // limpiar buffer

            if (pesoEquipaje > pesoMaximoEquipaje) {
                System.out.println("ERROR: Equipaje excede el límite de 23 kg.");
                return;
            }

            // Mostrar hora límite
            System.out.println("La hora límite para evitar multas es " + horaLimite + ".");

            // Acción del usuario
            System.out.println("Seleccione la acción (reserva / cambio / cancelación):");
            accion = sc.nextLine().toLowerCase();

            // Hora actual
            System.out.println("Ingrese la hora actual (HH:MM):");
            String horaActual = sc.nextLine();

            if (accion.equals("reserva")) {
                System.out.println("Ingrese la hora de la reserva (HH:MM):");
                horaReserva = sc.nextLine();
            } else if (accion.equals("cancelación")) {
                System.out.println("Ingrese la hora de la cancelación (HH:MM):");
                horaCancelacion = sc.nextLine();
            }

            // Conversión de horas a minutos para comparar
            int minutosActual = convertirAHorasEnMinutos(horaActual);
            int minutosLimite = convertirAHorasEnMinutos(horaLimite);

            // Aplicar multa si aplica
            if ((accion.equals("reserva") || accion.equals("cambio")) && minutosActual > minutosLimite) {
                aplicaMulta = true;
            }

            // Cálculo de precio
            if (aplicaMulta) {
                precioFinal = precioBase + (precioBase * multa);
            } else {
                precioFinal = precioBase;
            }

            // Actualizar asientos
            if (accion.equals("reserva") || (accion.equals("cambio") && minutosActual > minutosLimite)) {
                asientosDisponibles -= 1;
            } else if (accion.equals("cancelación")) {
                asientosDisponibles += 1;
            }

            // Mensaje de confirmación
            System.out.println("Operación realizada correctamente.");

            // Factura
            System.out.println("---- FACTURA ----");
            System.out.println("PASAJERO: " + id);
            System.out.println("TIPO DE BOLETO: " + tipoBoleto);
            System.out.println("RUTA: " + ruta);
            System.out.println("EQUIPAJE: " + pesoEquipaje + " kg");
            System.out.println("ACCIÓN: " + accion);
            if (accion.equals("reserva")) {
                System.out.println("HORA RESERVA: " + horaReserva);
            } else if (accion.equals("cancelación")) {
                System.out.println("HORA CANCELACIÓN: " + horaCancelacion);
            }
            System.out.println("PRECIO FINAL: " + precioFinal + " euros");
            System.out.println("MULTA APLICADA: " + (aplicaMulta ? "SÍ" : "NO"));
            System.out.println("ASIENTOS DISPONIBLES: " + asientosDisponibles);
        }
    }

    public static int convertirAHorasEnMinutos(String hora) {
        String[] partes = hora.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        return horas * 60 + minutos;
    }
}
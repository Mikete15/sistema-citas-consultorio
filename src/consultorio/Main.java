package consultorio;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SistemaCitas sistema = new SistemaCitas();

        // Administrador temporal para comenzar las pruebas del sistema
        Administrador administrador =
                new Administrador("admin", "admin123");

        sistema.agregarAdministrador(administrador);

        System.out.println("=================================");
        System.out.println(" SISTEMA DE CITAS DEL CONSULTORIO ");
        System.out.println("=================================");

        System.out.print("Identificador: ");
        String id = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        if (sistema.validarAdministrador(id, contrasena)) {

            System.out.println();
            System.out.println("Acceso concedido.");
            System.out.println();
            System.out.println("1. Dar de alta doctor");
            System.out.println("2. Dar de alta paciente");
            System.out.println("3. Crear cita");
            System.out.println("4. Salir");

        } else {

            System.out.println();
            System.out.println("Acceso denegado. Identificador o contraseña incorrectos.");
        }

        scanner.close();
    }
}

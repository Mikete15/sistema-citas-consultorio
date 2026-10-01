package consultorio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SistemaCitas sistema = new SistemaCitas();
        GestorArchivos gestorArchivos = new GestorArchivos();

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

            boolean continuar = true;

            while (continuar) {

                System.out.println();
                System.out.println("========== MENÚ PRINCIPAL ==========");
                System.out.println("1. Dar de alta doctor");
                System.out.println("2. Dar de alta paciente");
                System.out.println("3. Crear cita");
                System.out.println("4. Salir");
                System.out.print("Selecciona una opción: ");

                String opcion = scanner.nextLine();

                switch (opcion) {

                    case "1":
                        registrarDoctor(scanner, sistema, gestorArchivos);
                        break;

                    case "2":
                        registrarPaciente(scanner, sistema, gestorArchivos);
                        break;

                    case "3":
                        crearCita(scanner, sistema, gestorArchivos);
                        break;

                    case "4":
                        System.out.println("Saliendo del sistema...");
                        continuar = false;
                        break;

                    default:
                        System.out.println("Opción inválida.");
                        break;
                }
            }

        } else {

            System.out.println();
            System.out.println(
                    "Acceso denegado. Identificador o contraseña incorrectos."
            );
        }

        scanner.close();
    }

    private static void registrarDoctor(
            Scanner scanner,
            SistemaCitas sistema,
            GestorArchivos gestorArchivos) {

        System.out.println();
        System.out.println("========== ALTA DE DOCTOR ==========");

        System.out.print("Identificador del doctor: ");
        String idDoctor = scanner.nextLine().trim();

        if (idDoctor.isEmpty()) {
            System.out.println("El identificador no puede estar vacío.");
            return;
        }

        if (sistema.buscarDoctor(idDoctor) != null) {
            System.out.println("Ya existe un doctor con ese identificador.");
            return;
        }

        System.out.print("Nombre completo: ");
        String nombreCompleto = scanner.nextLine().trim();

        if (nombreCompleto.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine().trim();

        if (especialidad.isEmpty()) {
            System.out.println("La especialidad no puede estar vacía.");
            return;
        }

        Doctor doctor =
                new Doctor(idDoctor, nombreCompleto, especialidad);

        sistema.agregarDoctor(doctor);

        gestorArchivos.guardar(
                "db/doctores.csv",
                doctor
        );

        System.out.println();
        System.out.println("Doctor registrado correctamente.");
    }

    private static void registrarPaciente(
            Scanner scanner,
            SistemaCitas sistema,
            GestorArchivos gestorArchivos) {

        System.out.println();
        System.out.println("========== ALTA DE PACIENTE ==========");

        System.out.print("Identificador del paciente: ");
        String idPaciente = scanner.nextLine().trim();

        if (idPaciente.isEmpty()) {
            System.out.println("El identificador no puede estar vacío.");
            return;
        }

        if (sistema.buscarPaciente(idPaciente) != null) {
            System.out.println("Ya existe un paciente con ese identificador.");
            return;
        }

        System.out.print("Nombre completo: ");
        String nombreCompleto = scanner.nextLine().trim();

        if (nombreCompleto.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        Paciente paciente =
                new Paciente(idPaciente, nombreCompleto);

        sistema.agregarPaciente(paciente);

        gestorArchivos.guardar(
                "db/pacientes.csv",
                paciente
        );

        System.out.println();
        System.out.println("Paciente registrado correctamente.");
    }

    private static void crearCita(
            Scanner scanner,
            SistemaCitas sistema,
            GestorArchivos gestorArchivos) {

        System.out.println();
        System.out.println("========== CREAR CITA ==========");

        System.out.print("Identificador de la cita: ");
        String idCita = scanner.nextLine().trim();

        if (idCita.isEmpty()) {
            System.out.println("El identificador no puede estar vacío.");
            return;
        }

        if (sistema.buscarCita(idCita) != null) {
            System.out.println("Ya existe una cita con ese identificador.");
            return;
        }

        System.out.print("Fecha y hora (dd/MM/yyyy HH:mm): ");
        String fechaHoraTexto = scanner.nextLine().trim();

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime fechaHora;

        try {
            fechaHora = LocalDateTime.parse(fechaHoraTexto, formato);
        } catch (DateTimeParseException e) {
            System.out.println(
                    "Formato de fecha y hora inválido. Utiliza dd/MM/yyyy HH:mm."
            );
            return;
        }

        System.out.print("Motivo de la cita: ");
        String motivo = scanner.nextLine().trim();

        if (motivo.isEmpty()) {
            System.out.println("El motivo no puede estar vacío.");
            return;
        }

        System.out.print("Identificador del doctor: ");
        String idDoctor = scanner.nextLine().trim();

        Doctor doctor = sistema.buscarDoctor(idDoctor);

        if (doctor == null) {
            System.out.println("Doctor no encontrado.");
            return;
        }

        System.out.print("Identificador del paciente: ");
        String idPaciente = scanner.nextLine().trim();

        Paciente paciente = sistema.buscarPaciente(idPaciente);

        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        Cita cita =
                new Cita(idCita, fechaHora, motivo, doctor, paciente);

        sistema.agregarCita(cita);

        gestorArchivos.guardar(
                "db/citas.csv",
                cita
        );

        System.out.println();
        System.out.println("Cita registrada correctamente.");
    }
}
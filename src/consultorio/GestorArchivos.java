package consultorio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class GestorArchivos {

    public void prepararArchivos() {

        try {

            Path carpetaDb = Paths.get("db");

            if (Files.notExists(carpetaDb)) {
                Files.createDirectories(carpetaDb);
            }

            crearArchivoSiNoExiste("db/doctores.csv");
            crearArchivoSiNoExiste("db/pacientes.csv");
            crearArchivoSiNoExiste("db/citas.csv");

        } catch (IOException e) {

            System.out.println(
                    "Error al preparar los archivos: " + e.getMessage()
            );
        }
    }

    private void crearArchivoSiNoExiste(String nombreArchivo)
            throws IOException {

        Path archivo = Paths.get(nombreArchivo);

        if (Files.notExists(archivo)) {
            Files.createFile(archivo);
        }
    }

    public void guardar(String nombreArchivo, Persistible objeto) {

        try (FileWriter archivo = new FileWriter(nombreArchivo, true);
             PrintWriter escritor = new PrintWriter(archivo)) {

            escritor.println(objeto.toCSV());

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar la información: " + e.getMessage()
            );
        }
    }

    public void cargarDatos(SistemaCitas sistema) {

        prepararArchivos();

        cargarDoctores(sistema);
        cargarPacientes(sistema);
        cargarCitas(sistema);
    }

    private void cargarDoctores(SistemaCitas sistema) {

        try (BufferedReader lector =
                     new BufferedReader(
                             new FileReader("db/doctores.csv"))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",", -1);

                if (datos.length == 3) {

                    String id = datos[0];
                    String nombreCompleto = datos[1];
                    String especialidad = datos[2];

                    if (sistema.buscarDoctor(id) == null) {

                        Doctor doctor =
                                new Doctor(
                                        id,
                                        nombreCompleto,
                                        especialidad
                                );

                        sistema.agregarDoctor(doctor);
                    }
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al cargar doctores: " + e.getMessage()
            );
        }
    }

    private void cargarPacientes(SistemaCitas sistema) {

        try (BufferedReader lector =
                     new BufferedReader(
                             new FileReader("db/pacientes.csv"))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",", -1);

                if (datos.length == 2) {

                    String id = datos[0];
                    String nombreCompleto = datos[1];

                    if (sistema.buscarPaciente(id) == null) {

                        Paciente paciente =
                                new Paciente(
                                        id,
                                        nombreCompleto
                                );

                        sistema.agregarPaciente(paciente);
                    }
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al cargar pacientes: " + e.getMessage()
            );
        }
    }

    private void cargarCitas(SistemaCitas sistema) {

        try (BufferedReader lector =
                     new BufferedReader(
                             new FileReader("db/citas.csv"))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",", -1);

                if (datos.length == 5) {

                    String id = datos[0];
                    String fechaHoraTexto = datos[1];
                    String motivo = datos[2];
                    String idDoctor = datos[3];
                    String idPaciente = datos[4];

                    Doctor doctor =
                            sistema.buscarDoctor(idDoctor);

                    Paciente paciente =
                            sistema.buscarPaciente(idPaciente);

                    if (doctor != null
                            && paciente != null
                            && sistema.buscarCita(id) == null) {

                        try {

                            LocalDateTime fechaHora =
                                    LocalDateTime.parse(
                                            fechaHoraTexto
                                    );

                            Cita cita =
                                    new Cita(
                                            id,
                                            fechaHora,
                                            motivo,
                                            doctor,
                                            paciente
                                    );

                            sistema.agregarCita(cita);

                        } catch (DateTimeParseException e) {

                            System.out.println(
                                    "Fecha inválida encontrada en citas.csv."
                            );
                        }
                    }
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al cargar citas: " + e.getMessage()
            );
        }
    }
}
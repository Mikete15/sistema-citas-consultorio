package consultorio;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GestorArchivos {

    public void guardar(String nombreArchivo, Persistible objeto) {

        try (FileWriter archivo = new FileWriter(nombreArchivo, true);
             PrintWriter escritor = new PrintWriter(archivo)) {

            escritor.println(objeto.toCSV());

        } catch (IOException e) {
            System.out.println("Error al guardar la información: " + e.getMessage());
        }
    }
}
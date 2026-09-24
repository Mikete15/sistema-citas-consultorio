package consultorio;

public class Paciente extends Persona implements Persistible {

    public Paciente(String id, String nombreCompleto) {
        super(id, nombreCompleto);
    }

    @Override
    public String toCSV() {
        return id + "," + nombreCompleto;
    }
}
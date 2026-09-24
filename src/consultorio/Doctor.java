package consultorio;

public class Doctor extends Persona implements Persistible {

    private String especialidad;

    public Doctor(String id, String nombreCompleto, String especialidad) {
        super(id, nombreCompleto);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toCSV() {
        return id + "," + nombreCompleto + "," + especialidad;
    }
}
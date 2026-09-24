package consultorio;

public class Administrador implements Persistible {

    private String id;
    private String contrasena;

    public Administrador(String id, String contrasena) {
        this.id = id;
        this.contrasena = contrasena;
    }

    public String getId() {
        return id;
    }

    public boolean validarAcceso(String id, String contrasena) {
        return this.id.equals(id) && this.contrasena.equals(contrasena);
    }

    @Override
    public String toCSV() {
        return id + "," + contrasena;
    }
}
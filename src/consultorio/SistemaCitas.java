package consultorio;

import java.util.ArrayList;
import java.util.List;

public class SistemaCitas {

    private List<Doctor> doctores;
    private List<Paciente> pacientes;
    private List<Cita> citas;
    private List<Administrador> administradores;

    public SistemaCitas() {
        doctores = new ArrayList<>();
        pacientes = new ArrayList<>();
        citas = new ArrayList<>();
        administradores = new ArrayList<>();
    }

    public void agregarDoctor(Doctor doctor) {
        doctores.add(doctor);
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public void agregarCita(Cita cita) {
        citas.add(cita);
    }

    public void agregarAdministrador(Administrador administrador) {
        administradores.add(administrador);
    }

    public boolean validarAdministrador(String id, String contrasena) {
        for (Administrador administrador : administradores) {
            if (administrador.validarAcceso(id, contrasena)) {
                return true;
            }
        }

        return false;
    }

    public Doctor buscarDoctor(String id) {
        for (Doctor doctor : doctores) {
            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }

        return null;
    }

    public Paciente buscarPaciente(String id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }

        return null;
    }

    public Cita buscarCita(String id) {
        for (Cita cita : citas) {
            if (cita.getId().equals(id)) {
                return cita;
            }
        }

        return null;
    }
}
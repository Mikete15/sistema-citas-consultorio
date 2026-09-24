package consultorio;

import java.time.LocalDateTime;

public class Cita implements Persistible {

    private String id;
    private LocalDateTime fechaHora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(String id, LocalDateTime fechaHora, String motivo,
                Doctor doctor, Paciente paciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toCSV() {
        return id + "," + fechaHora + "," + motivo + ","
                + doctor.getId() + "," + paciente.getId();
    }
}
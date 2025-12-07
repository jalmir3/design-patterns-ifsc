package ifsc.edu.designpatterns.creational;

import ifsc.edu.designpatterns.domain.Consulta;
import ifsc.edu.designpatterns.domain.Medico;
import ifsc.edu.designpatterns.domain.Paciente;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HospitalManager {
    private static HospitalManager instancia;
    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<Consulta> consultas;

    private HospitalManager() {
        this.pacientes = new ArrayList<>();
        this.medicos = new ArrayList<>();
        this.consultas = new ArrayList<>();
    }

    public static synchronized HospitalManager getInstance() {
        if (instancia == null) {
            instancia = new HospitalManager();
        }
        return instancia;
    }

    public void adicionarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        log.info("Paciente adicionado: {}", paciente.getNome());
    }

    public void adicionarMedico(Medico medico) {
        medicos.add(medico);
        log.info("Médico adicionado: {}", medico.getNome());
    }

    public List<Paciente> listarPacientes() {
        return new ArrayList<>(pacientes);
    }

    public List<Medico> listarMedicos() {
        return new ArrayList<>(medicos);
    }

    public int getTotalPacientes() {
        return pacientes.size();
    }

    public int getTotalMedicos() {
        return medicos.size();
    }

    @Override
    public String toString() {
        return "HospitalManager{" +
                "totalPacientes=" + pacientes.size() +
                ", totalMedicos=" + medicos.size() +
                ", totalConsultas=" + consultas.size() +
                '}';
    }
}


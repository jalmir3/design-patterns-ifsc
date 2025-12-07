package ifsc.edu.designpatterns.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Consulta {
    private String id;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime dataHora;
    private String status;
    private String descricao;
}


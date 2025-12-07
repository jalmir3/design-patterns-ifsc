package ifsc.edu.designpatterns.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Medico {
    private String nome;
    private String crm;
    private String especialidade;
}


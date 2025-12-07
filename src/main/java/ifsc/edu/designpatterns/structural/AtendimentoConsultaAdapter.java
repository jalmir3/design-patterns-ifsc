package ifsc.edu.designpatterns.structural;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtendimentoConsultaAdapter implements TipoAtendimentoAdapter {

    @Override
    public void realizarAtendimento(String pacienteNome, String descricao) {
        log.info("Realizando CONSULTA para o paciente: {}", pacienteNome);
        log.info("Descrição: {}", descricao);
        log.info("Consulta registrada no sistema");
    }
}


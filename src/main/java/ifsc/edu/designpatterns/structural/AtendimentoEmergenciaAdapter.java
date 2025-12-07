package ifsc.edu.designpatterns.structural;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtendimentoEmergenciaAdapter implements TipoAtendimentoAdapter {

    @Override
    public void realizarAtendimento(String pacienteNome, String descricao) {
        log.info("Realizando atendimento de emergencia para o paciente: {}", pacienteNome);
        log.info("Descrição: {}", descricao);
        log.info("Paciente encaminhado para sala de emergência");
    }
}


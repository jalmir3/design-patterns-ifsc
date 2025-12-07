package ifsc.edu.designpatterns.behavioral;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotificadorEmail implements AtendimentoObserver {

    @Override
    public void notificar(TipoAtendimento tipoAtendimento, String pacienteNome, String mensagem) {
        log.info("[EMAIL] Notificação enviada ao paciente {}", pacienteNome);
        log.info("Tipo: {}", tipoAtendimento.getDescricao());
        log.info("Mensagem: {}", mensagem);
    }
}


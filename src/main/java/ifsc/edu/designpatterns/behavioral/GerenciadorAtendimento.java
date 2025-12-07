package ifsc.edu.designpatterns.behavioral;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GerenciadorAtendimento {

    private List<AtendimentoObserver> observadores;

    public GerenciadorAtendimento() {
        this.observadores = new ArrayList<>();
    }

    public void registrarObservador(AtendimentoObserver observer) {
        observadores.add(observer);
        log.info("Observador registrado: {}", observer.getClass().getSimpleName());
    }

    public void notificarObservadores(TipoAtendimento tipoAtendimento, String pacienteNome, String mensagem) {
        log.info("\nNotificando observadores...");
        observadores.forEach(o -> o.notificar(tipoAtendimento, pacienteNome, mensagem));
    }

    public int getTotalObservadores() {
        return observadores.size();
    }
}


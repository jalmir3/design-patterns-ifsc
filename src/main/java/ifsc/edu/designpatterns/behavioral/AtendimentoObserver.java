package ifsc.edu.designpatterns.behavioral;

public interface AtendimentoObserver {

    void notificar(TipoAtendimento tipoAtendimento, String pacienteNome, String mensagem);
}

